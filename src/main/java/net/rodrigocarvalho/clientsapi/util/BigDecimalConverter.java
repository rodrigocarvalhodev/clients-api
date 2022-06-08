package net.rodrigocarvalho.clientsapi.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConverter {

    public BigDecimal convert(String value) {
        if (value == null) return null;
        value = value.replace(".", "").replace(',', '.');
        return new BigDecimal(value);
    }
}
