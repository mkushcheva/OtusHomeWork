package ru.diasoft.library.generator;

import ru.diasoft.library.rest.dto.GenreDto;

import java.util.ArrayList;
import java.util.List;

public class GenreGenerator {

    /*Жанры созданные в БД*/
    public static final Long GENRE_ID1 = 1L;
    public static final String GENRE_NAME1 = "Сказка";

    public static final Long GENRE_ID2 = 2L;
    public static final String GENRE_NAME2 = "Роман";

    public static final Long GENRE_ID3 = 3L;
    public static final String GENRE_NAME3 = "Стихотворение";

    public static final Long GENRE_IDNEW = 4L;
    public static final String GENRE_NAMENEW = "NEW";

    public static List<GenreDto> getGenreDtoList() {
        List<GenreDto> genreDtoList = new ArrayList<>();
        genreDtoList.add(getGenreDto(GENRE_ID1, GENRE_NAME1));
        genreDtoList.add(getGenreID2());
        genreDtoList.add(getGenreDto(GENRE_ID3, GENRE_NAME3));
        return genreDtoList;
    }

    public static GenreDto getGenreID2() {
        return getGenreDto(GENRE_ID2, GENRE_NAME2);
    }

    public static GenreDto getNewGenre() {
        return getGenreDto(GENRE_IDNEW, GENRE_NAMENEW);
    }

    private static GenreDto getGenreDto(long id, String name) {
        return GenreDto.builder()
                .id(id)
                .name(name)
                .build();
    }

}
