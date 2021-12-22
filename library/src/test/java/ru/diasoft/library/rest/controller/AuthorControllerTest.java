package ru.diasoft.library.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.library.generator.AuthorGenerator;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Контроллер авторов должен:")
class AuthorControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("вернуть всех авторов")
    void shouldGetAllAuthorsTest() throws Exception {
        mvc.perform(get("/author"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(AuthorGenerator.getAuthorDtoList())));
    }

    @Test
    @DisplayName("вернуть автора 2=Волков А.М. test")
    void shouldGetAllAuthorByID2Test() throws Exception {
        mvc.perform(get("/author/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(AuthorGenerator.getAuthorID2())));
    }

    @Test
    @DisplayName("сохранить нового автора и удалить его")
    void shouldSaveNewAuthorAndDeleteNewAuthor() throws Exception {
        String expectedResult = mapper.writeValueAsString(AuthorGenerator.getNewAuthor());

        mvc.perform(post("/author").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));

        mvc.perform(delete("/author/4"))
                .andExpect(status().isOk());
    }
}