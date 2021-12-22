package ru.diasoft.library.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.library.generator.BookGenerator;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Контроллер книг должен:")
class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("вернуть все книги")
    void shouldGetAllBooksTest() throws Exception {
        mvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(BookGenerator.getBookDtoList())));
    }

    @Test
    @DisplayName("вернуть книгу с id=2")
    void shouldGetBookByID2Test() throws Exception {
        mvc.perform(get("/book/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(BookGenerator.getBookID2())));
    }

    @Test
    @DisplayName("сохранить новую книгу и удалить ее")
    void shouldSaveNewBookAndDeleteNewBook() throws Exception {
        String expectedResult = mapper.writeValueAsString(BookGenerator.getNewBook());

        mvc.perform(post("/book").contentType(APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));

        mvc.perform(delete("/book/10"))
                .andExpect(status().isOk());
    }
}