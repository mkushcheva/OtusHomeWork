package ru.diasoft.library.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import ru.diasoft.library.service.LibraryUserDetailsService;

import java.security.interfaces.RSAPublicKey;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final LibraryUserDetailsService userDetailsService;

    @Value("${jwt.public.key}")
    private RSAPublicKey key;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringAntMatchers("/token"))
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                )
                .authorizeRequests(authz -> authz
                        .antMatchers(HttpMethod.POST, "/token").permitAll()
                        .antMatchers(HttpMethod.GET, "/book/**").hasAnyAuthority("SCOPE_ROLE_READER", "SCOPE_ROLE_ADMIN")
                        .antMatchers(HttpMethod.GET, "/author/**").hasAnyAuthority("SCOPE_ROLE_READER", "SCOPE_ROLE_ADMIN")
                        .antMatchers(HttpMethod.GET, "/genre/**").hasAnyAuthority("SCOPE_ROLE_READER", "SCOPE_ROLE_ADMIN")
                        .antMatchers(HttpMethod.POST, "/book").hasAuthority("SCOPE_ROLE_ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/book/**").hasAuthority("SCOPE_ROLE_ADMIN")
                        .antMatchers(HttpMethod.PUT, "/book/**").hasAuthority("SCOPE_ROLE_ADMIN")
                        .antMatchers("/**").denyAll()
                );
    }
}
