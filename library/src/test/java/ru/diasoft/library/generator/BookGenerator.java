package ru.diasoft.library.generator;

import ru.diasoft.library.rest.dto.BookDto;

import java.util.ArrayList;
import java.util.List;

public class BookGenerator {
    /*Книги созданные в БД*/
    public static List<BookDto> getBookDtoList() {
        List<BookDto> bookDtoList = new ArrayList<>();

        List<String> comments = new ArrayList<>();
        comments.add("Книга про волшебника");
        comments.add("Очень интерсеная");
        comments.add("Советую почитать");

        bookDtoList.add(getBookDto(1, "Волшебник изумрудного города", "Волков А.М.", "Сказка", comments));
        bookDtoList.add(getBookID2());
        bookDtoList.add(getBookDto(3, "Сказка о золотом петушке", "Пушкин А.С.", "Сказка", new ArrayList<>()));
        bookDtoList.add(getBookDto(4, "Скитания", "Волков А.М.", "Роман", new ArrayList<>()));
        bookDtoList.add(getBookDto(5, "Зодчие", "Волков А.М.", "Роман", new ArrayList<>()));
        bookDtoList.add(getBookDto(6, "Незнайка на луне", "Носов Н.Н.", "Роман", new ArrayList<>()));
        bookDtoList.add(getBookDto(7, "Няне", "Пушкин А.С.", "Стихотворение", new ArrayList<>()));
        bookDtoList.add(getBookDto(8, "Мечты", "Волков А.М.", "Стихотворение", new ArrayList<>()));
        bookDtoList.add(getBookDto(9, "Разведчики", "Волков А.М.", "Стихотворение", new ArrayList<>()));
        return bookDtoList;
    }

    public static BookDto getBookID2() {
        List<String> comments = new ArrayList<>();
        comments.add("Книга о жадкой старухе");

        return getBookDto(2, "Сказка о рыбаке и рыбке", "Пушкин А.С.", "Сказка", comments);
    }

    public static BookDto getNewBook() {
        return getBookDto(10l, "Новая книга", "Пушкин А.С.", "Сказка", new ArrayList<>());
    }

    private static BookDto getBookDto(long id, String title, String authorName, String genreName, List<String> comments) {
        return BookDto.builder()
                .id(id)
                .title(title)
                .authorName(authorName)
                .genreName(genreName)
                .comments(comments)
                .build();
    }
}
