package edu.nur.homework09.controller;

import edu.nur.homework09.model.Author;
import edu.nur.homework09.service.AuthorService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;


    @Test
    void listAuthors() throws Exception {
        List<Author> expectedAuthors = List.of(new Author(1, "Name1", "LastName1"),
                new Author(2, "Name1", "LastName1"));
        given(authorService.findAll()).willReturn(expectedAuthors);

        mvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(view().name("author_list"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attribute("authors", expectedAuthors));

    }

    @Test
    void editPage() throws Exception {
        Author author = new Author(1, "Name1", "LastName1");
        given(authorService.findById(1)).willReturn(Optional.of(author));

        mvc.perform(get("/author/edit").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(view().name("author_edit"))
                .andExpect(model().attributeExists("author"))
                .andExpect(model().attribute("author", author));
    }

    @Test
    void saveAuthor() throws Exception {
        Author author = new Author(1, "FirstName1", "LastName1");
        given(authorService.save(author)).willReturn(author);

        mvc.perform(post("/author/edit")
                        .param("id-input", "1")
                        .param("author-firstName-input", "FirstName1")
                        .param("author-lastName-input", "LastName1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(view().name("author_edit"))
                .andDo(print());
    }

    @Test
    void deleteAuthor() throws Exception {
        mvc.perform(post("/author/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection());
        verify(authorService, times(1)).deleteById(1L);
    }
}