package edu.nur.homework04;

import edu.nur.homework04.config.AppProps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
class Homework04ApplicationTests {

	@Test
	void contextLoads() {
	}

}
