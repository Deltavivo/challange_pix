package com.itau.pix.strategy.impl;

import com.itau.pix.dto.CreatePixRequestDTO;
import com.itau.pix.enums.KeyType;
import com.itau.pix.exceptions.UnsupportedPixException;
import com.itau.pix.strategy.PixStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RandomValidationImpl implements PixStrategy {

    private static final String RANDOM_PATTERN = "\\w{36}";
    private static final Pattern randomPattern = Pattern.compile(RANDOM_PATTERN);

    @Override
    public void execute(CreatePixRequestDTO pixDTO) {
        System.out.printf("Validacao da chave Random.");
        if(pixDTO.getKeyType() == KeyType.ALEATORIO) {
            try{
                Matcher cnpjMatcher = randomPattern.matcher(pixDTO.getKeyValue());
            } catch(RuntimeException e) {
                throw new UnsupportedPixException("Chave aleatoria nao e valida.");
            }
        }
    }
    @Override
    public KeyType getTypeValidation() {
        return KeyType.ALEATORIO;
    }
}
