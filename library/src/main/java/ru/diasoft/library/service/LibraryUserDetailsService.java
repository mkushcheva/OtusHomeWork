package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.diasoft.library.domain.Users;
import ru.diasoft.library.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LibraryUserDetailsService implements UserDetailsService {
    private final UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = usersRepository.findByLogin(s)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь " + s + " не зарегистрирован"));

        return new User(user.getLogin(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
    }
}
