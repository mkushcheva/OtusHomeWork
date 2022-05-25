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
import ru.diasoft.library.generator.BookGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Контроллер книг должен:")
class BookControllerTest {
    private static final String READER_CREDENTIALS = "cmVhZGVyOnBhc3M=";
    public static final String ADMIN_CREDENTIALS = "YWRtaW46cGFzcw==";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private String getToken(String credentials) throws Exception {
        MvcResult result = mvc.perform(post("/token")
                .header("Authorization", "Basic " + credentials))
                .andExpect(status().isOk())
                .andReturn();

        return result.getResponse().getContentAsString();
    }

    @Test
    @DisplayName("вернуть все книги")
    void shouldGetAllBooksTest() throws Exception {
        mvc.perform(get("/book")
                .header("Authorization", "Bearer " + getToken(READER_CREDENTIALS))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(BookGenerator.getBookDtoList())));
    }

    @Test
    @DisplayName("вернуть книгу с id=2")
    void shouldGetBookByID2Test() throws Exception {
        mvc.perform(get("/book/2")
                .header("Authorization", "Bearer " + getToken(READER_CREDENTIALS))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(BookGenerator.getBookID2())));
    }

    @Test
    @DisplayName("сохранить новую книгу и удалить ее")
    void shouldSaveNewBookAndDeleteNewBook() throws Exception {
        String expectedResult = mapper.writeValueAsString(BookGenerator.getNewBook());

        mvc.perform(post("/book")
                .header("Authorization", "Bearer " + getToken(ADMIN_CREDENTIALS))
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));

        mvc.perform(delete("/book/10")
                .header("Authorization", "Bearer " + getToken(ADMIN_CREDENTIALS)))
                .andExpect(status().isOk());
    }
}