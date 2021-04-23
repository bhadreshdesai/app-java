package bdd.demo.appjava.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageTranslator {
    private static MessageSource messageSource;

    /**
     * Autowired with MessageSource to initialise the messageSource static field.
     * The messageSource static field is then used by the static functions
     *
     * @param messageSource
     */
    @Autowired
    MessageTranslator(MessageSource messageSource) {
        MessageTranslator.messageSource = messageSource;
    }

    public static String I18N(String msgCode, @Nullable Object[] args, Locale locale) {
        return messageSource.getMessage(msgCode, args, locale);
    }

    public static String I18N(String msgCode, @Nullable Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return I18N(msgCode, args, locale);
    }

    public static String I18N(String msgCode) {
        return I18N(msgCode, null);
    }

}