package com.itau.pix.strategy.impl;

import com.itau.pix.dto.CreatePixRequestDTO;
import com.itau.pix.enums.KeyType;
import com.itau.pix.exceptions.UnsupportedPixException;
import com.itau.pix.strategy.PixStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPFValidationImpl implements PixStrategy {

    private static final String CPF_PATTERN = "\\d{11}$";
    private static final Pattern cpfPattern = Pattern.compile(CPF_PATTERN);

    @Override
    public void execute(CreatePixRequestDTO pixDTO) {
        System.out.printf("Validacao do CPF.");
        if(pixDTO.getKeyType() == KeyType.CPF) {
            try{
                Matcher cpfMatcher = cpfPattern.matcher(pixDTO.getKeyValue());
            } catch(RuntimeException e) {
                throw new UnsupportedPixException("CPF nao e valido.");
            }
        }
    }
    @Override
    public KeyType getTypeValidation() {
        return KeyType.CPF;
    }
}
