package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.repository.AuthorRepository;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.Optional;

import static java.lang.System.lineSeparator;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final MessageSourceUtils messageSource;

    @Override
    public Author getByName(String name) {
        return authorRepository.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public Author create(String name) {
        Author author = authorRepository.create(new Author(0, name));
        System.out.println(messageSource.getMessage("author.create.successful", new Object[]{author}));
        return author;
    }

    @Override
    @Transactional(readOnly = true)
    public void printAllAuthors() {
        System.out.println(messageSource.getMessage("author.listofauthors") + lineSeparator());

        for (Author author : authorRepository.getAll()) {
            System.out.println("№: " + author.getId() + " " + author.getName());
        }
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<Author> author = authorRepository.getByName(name);

        if (author.isPresent()) {
            authorRepository.deleteById(author.get().getId());
            System.out.println(messageSource.getMessage("author.delete.successful", new Object[]{name}));
        } else {
            System.out.println(messageSource.getMessage("author.notFound"));
        }
    }
}
