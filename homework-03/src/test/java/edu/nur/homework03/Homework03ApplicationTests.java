package edu.nur.homework03;

import edu.nur.homework03.config.AppProps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(properties = {"quiz.runner.enabled=false"})
@EnableConfigurationProperties(value = AppProps.class)
@TestPropertySource("classpath:application.yml")
class Homework03ApplicationTests {

	@Test
	void contextLoads() {
	}

}
