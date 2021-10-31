package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.library.dao.AuthorDao;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.Optional;

import static java.lang.System.lineSeparator;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;
    private final MessageSourceUtils messageSource;

    @Override
    public Author getByName(String name) {
        //Если автора не нашли, то создадим его
        return authorDao.getByName(name).orElseGet(() -> authorDao.create(name));
    }

    @Override
    public void create(String name) {
        Author author = authorDao.create(name);
        System.out.println(messageSource.getMessage("author.create.successful", new Object[]{author}));
    }

    @Override
    public void printAllAuthors() {
        System.out.println(messageSource.getMessage("author.listofauthors") + lineSeparator());

        for (Author author : authorDao.getAll()) {
            System.out.println("№: " + author.getId() + " " + author.getName());
        }
    }

    @Override
    public void deleteByName(String name) {
        Optional<Author> author = authorDao.getByName(name);

        if (author.isPresent()) {
            authorDao.deleteById(author.get().getId());
            System.out.println(messageSource.getMessage("author.delete.successful", new Object[]{name}));
        } else {
            System.out.println(messageSource.getMessage("author.notFound"));
        }
    }
}
