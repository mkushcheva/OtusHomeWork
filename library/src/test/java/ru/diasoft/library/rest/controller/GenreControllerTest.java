package ru.diasoft.library.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.library.generator.GenreGenerator;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(
        username = "admin",
        authorities = {"ROLE_ADMIN"}
)
@DisplayName("Контроллер жанров должен:")
class GenreControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("вернуть все жанры")
    void shouldGetAllGenresTest() throws Exception {
        mvc.perform(get("/genre"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(GenreGenerator.getGenreDtoList())));
    }

    @Test
    @DisplayName("вернуть жанр с id")
    void shouldGetAllGenreByID2Test() throws Exception {
        mvc.perform(get("/genre/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(GenreGenerator.getGenreID2())));
    }

    @Test
    @DisplayName("сохранить новый жанр и удалить его")
    void shouldSaveNewGenreAndDeleteNewGenre() throws Exception {
        String expectedResult = mapper.writeValueAsString(GenreGenerator.getNewGenre());

        mvc.perform(post("/genre").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));

        mvc.perform(delete("/genre/4"))
                .andExpect(status().isOk());
    }
}