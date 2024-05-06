package com.itau.pix.strategy.impl;

import com.itau.pix.dto.CreatePixRequestDTO;
import com.itau.pix.enums.KeyType;
import com.itau.pix.exceptions.UnsupportedPixException;
import com.itau.pix.strategy.PixStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CNPJValidationImpl implements PixStrategy {

    private static final String CNPJ_PATTERN = "\\d{14}$";
    private static final Pattern cnpjPattern = Pattern.compile(CNPJ_PATTERN);

    @Override
    public void execute(CreatePixRequestDTO pixDTO) {
        System.out.printf("Validacao do CNPJ");
        if(pixDTO.getKeyType() == KeyType.CNPJ) {
            try{
                Matcher cnpjMatcher = cnpjPattern.matcher(pixDTO.getKeyValue());
            } catch(RuntimeException e) {
                throw new UnsupportedPixException("CNPJ nao e valido.");
            }
        }
    }

    @Override
    public KeyType getTypeValidation() {
        return KeyType.CNPJ;
    }
}
