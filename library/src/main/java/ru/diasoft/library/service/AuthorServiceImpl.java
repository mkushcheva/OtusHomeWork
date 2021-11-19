package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.repository.AuthorRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final WriterService writerService;

    @Override
    public Author getByName(String name) {
        return authorRepository.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public Author create(String name) {
        Author author = authorRepository.create(new Author(0, name));
        writerService.printMessage("author.create.successful", new Object[]{author});
        return author;
    }

    @Override
    @Transactional(readOnly = true)
    public void printAllAuthors() {
        writerService.printAllAuthors(authorRepository.getAll());
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<Author> author = authorRepository.getByName(name);

        if (author.isPresent()) {
            authorRepository.deleteById(author.get().getId());
            writerService.printMessage("author.delete.successful", new Object[]{name});
        } else {
            writerService.printMessage("author.notFound");
        }
    }
}
