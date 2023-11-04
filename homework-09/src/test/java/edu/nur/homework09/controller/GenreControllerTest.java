package edu.nur.homework09.controller;

import edu.nur.homework09.model.Genre;
import edu.nur.homework09.service.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService genreService;

    @Test
    void listGenres() throws Exception {
        List<Genre> expectedGenres = List.of(new Genre(1, "Genre1"), new Genre(2, "Genre2"));
        given(genreService.findAll()).willReturn(expectedGenres);

        mvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(view().name("genre_list"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attribute("genres", expectedGenres));

    }

    @Test
    void editPage() throws Exception {
        Genre genre = new Genre(1, "Genre1");
        given(genreService.findById(1)).willReturn(Optional.of(genre));

        mvc.perform(get("/genre/edit").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(view().name("genre_edit"))
                .andExpect(model().attributeExists("genre"))
                .andExpect(model().attribute("genre", genre));
    }

    @Test
    void saveGenre() throws Exception {
        Genre genre = new Genre(1, "Genre1");
        given(genreService.save(genre)).willReturn(genre);

        mvc.perform(post("/genre/edit")
                .param("id-input", "1")
                .param("genre-title-input", "Genre1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(view().name("genre_edit"))
                .andDo(print());
    }

    @Test
    void deleteGenre() throws Exception {
        mvc.perform(post("/genre/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection());
        verify(genreService, times(1)).deleteById(1L);
    }

}