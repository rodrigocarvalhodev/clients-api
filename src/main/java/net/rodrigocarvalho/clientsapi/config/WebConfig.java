package net.rodrigocarvalho.clientsapi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig {

    private final List<String> ALLOWED = Arrays.asList("http://localhost:4200");
    private final List<String> ALLOWED_METHODS = Arrays.asList("*");


    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean() {
        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(ALLOWED);
        corsConfiguration.setAllowedHeaders(ALLOWED_METHODS);
        corsConfiguration.setAllowedMethods(ALLOWED_METHODS);
        corsConfiguration.setAllowCredentials(true);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        var corsFilter = new CorsFilter(source);
        var filter = new FilterRegistrationBean<>(corsFilter);
        filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filter;
    }
}
