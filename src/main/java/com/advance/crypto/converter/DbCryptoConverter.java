package com.advance.crypto.converter;

import com.advance.crypto.utils.Aes256Utils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Convert
public class DbCryptoConverter implements AttributeConverter<String, String> {

    private static Aes256Utils aes256Utils;

    @Autowired
    public void Aes256Converter(Aes256Utils aes256Utils) {
        this.aes256Utils = aes256Utils;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return aes256Utils.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return aes256Utils.decrypt(dbData);
    }
}
