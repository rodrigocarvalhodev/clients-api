package net.rodrigocarvalho.clientsapi.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternationalConfig {

    @Bean
    public MessageSource messageSource() {
        var reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasename("classpath:messages");
        reloadableResourceBundleMessageSource.setDefaultEncoding("ISO-8859-1");
        reloadableResourceBundleMessageSource.setDefaultLocale(Locale.getDefault());
        return reloadableResourceBundleMessageSource;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        var localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }
}
