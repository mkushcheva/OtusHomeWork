package ru.diasoft.library.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.diasoft.library.generator.AuthorGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Контроллер авторов должен:")
class AuthorControllerTest {
    private static final String READER_CREDENTIALS = "cmVhZGVyOnBhc3M=";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private String getToken() throws Exception {
        MvcResult result = mvc.perform(post("/token")
                .header("Authorization", "Basic " + READER_CREDENTIALS))
                .andExpect(status().isOk())
                .andReturn();

        return result.getResponse().getContentAsString();
    }

    @Test
    @DisplayName("вернуть всех авторов")
    void shouldGetAllAuthorsTest() throws Exception {
        mvc.perform(get("/author")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(AuthorGenerator.getAuthorDtoList())));
    }

    @Test
    @DisplayName("вернуть автора 2=Волков А.М")
    void shouldGetAllAuthorByID2Test() throws Exception {
        mvc.perform(get("/author/2")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(AuthorGenerator.getAuthorID2())));
    }
}