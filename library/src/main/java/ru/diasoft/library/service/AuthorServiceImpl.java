package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.exception.NotFoundException;
import ru.diasoft.library.repository.AuthorRepository;
import ru.diasoft.library.rest.dto.AuthorDto;
import ru.diasoft.library.rest.mapper.AuthorMapper;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;
    private final MessageSourceUtils messageSource;

    @Override
    public Author getByName(String name) {
        return authorRepository.findByName(name).orElse(null);
    }

    @Override
    @Transactional
    public Author create(String name) {
        return authorRepository.save(new Author(0, name));
    }

    @Override
    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDto> getAllAuthorDto() {
        return authorRepository.findAll().stream()
                .map(mapper::authorDomainToAuthorDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorDtoByID(long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("author.notFound")));
        return mapper.authorDomainToAuthorDto(author);
    }

    @Override
    public AuthorDto createNewAuthorDto(AuthorDto dto) {
        Author author = mapper.authorDtoToAuthorDomain(dto);
        return mapper.authorDomainToAuthorDto(authorRepository.save(author));
    }
}
