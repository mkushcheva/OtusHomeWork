package ru.diasoft.library.generator;

import ru.diasoft.library.rest.dto.AuthorDto;

import java.util.ArrayList;
import java.util.List;

public class AuthorGenerator {
    /*Авторы созданные в БД*/
    public static final Long AUTHOR_ID1 = 1L;
    public static final String AUTHOR_NAME1 = "Пушкин А.С.";

    public static final Long AUTHOR_ID2 = 2L;
    public static final String AUTHOR_NAME2 = "Волков А.М.";

    public static final Long AUTHOR_ID3 = 3L;
    public static final String AUTHOR_NAME3 = "Носов Н.Н.";

    public static final Long AUTHOR_IDNEW = 4L;
    public static final String AUTHOR_NAMENEW = "Новый Автор";

    public static List<AuthorDto> getAuthorDtoList() {
        List<AuthorDto> authorDtoList = new ArrayList<>();
        authorDtoList.add(getAuthorDto(AUTHOR_ID1, AUTHOR_NAME1));
        authorDtoList.add(getAuthorID2());
        authorDtoList.add(getAuthorDto(AUTHOR_ID3, AUTHOR_NAME3));
        return authorDtoList;
    }

    public static AuthorDto getAuthorID2() {
        return getAuthorDto(AUTHOR_ID2, AUTHOR_NAME2);
    }

    public static AuthorDto getNewAuthor() {
        return getAuthorDto(AUTHOR_IDNEW, AUTHOR_NAMENEW);
    }

    private static AuthorDto getAuthorDto(long id, String name) {
        return AuthorDto.builder()
                .id(id)
                .name(name)
                .build();
    }
}
