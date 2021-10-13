package ru.otus.spring.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSourceUtils {
    private final MessageSource messageSource;

    public String getMessage(String code, @Nullable Object[] args) {
       return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
