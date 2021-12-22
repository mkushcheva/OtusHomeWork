package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.repository.AuthorRepository;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author getByName(String name) {
        return authorRepository.findByName(name).orElse(null);
    }

    @Override
    @Transactional
    public Author create(String name) {
        return authorRepository.save(new Author(0, name));
    }
}
