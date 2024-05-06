package com.itau.pix.strategy.impl;

import com.itau.pix.dto.CreatePixRequestDTO;
import com.itau.pix.enums.KeyType;
import com.itau.pix.exceptions.UnsupportedPixException;
import com.itau.pix.strategy.PixStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidationImpl implements PixStrategy {

    private static final String EMAIL_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    @Override
    public void execute(CreatePixRequestDTO pixDTO) {
        System.out.printf("Validacao do Email");
        if(pixDTO.getKeyType() == KeyType.EMAIL) {
            try{
                Matcher emailMatcher = emailPattern.matcher(pixDTO.getKeyValue());
            } catch(RuntimeException e) {
                throw new UnsupportedPixException("Email nao e valido.");
            }
        }
    }
    @Override
    public KeyType getTypeValidation() {
        return KeyType.EMAIL;
    }
}
