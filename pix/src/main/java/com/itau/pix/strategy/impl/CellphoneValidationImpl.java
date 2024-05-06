package com.itau.pix.strategy.impl;

import com.itau.pix.dto.CreatePixRequestDTO;
import com.itau.pix.enums.KeyType;
import com.itau.pix.exceptions.UnexpectedTypeException;
import com.itau.pix.exceptions.UnsupportedPixException;
import com.itau.pix.strategy.PixStrategy;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CellphoneValidationImpl implements PixStrategy {

    private static final String CELLPHONE_PATTERN = "(?:(?:\\+|00)55\\s?)?(\\d{3})?(?:((?:9\\d|[2-9])\\d{7}))$";
    private static final Pattern celPattern = Pattern.compile(CELLPHONE_PATTERN);

    @Override
    public void execute(CreatePixRequestDTO pixDTO) {
        System.out.printf("Validacao do celular");

        if(pixDTO.getKeyType() == KeyType.CELULAR) {
            try{
                Matcher celMatcher = celPattern.matcher(pixDTO.getKeyValue());
            } catch(RuntimeException e) {
                throw new UnsupportedPixException("Celular nao e valido.");
            }
        }
    }

    @Override
    public KeyType getTypeValidation() { return KeyType.CELULAR; }
}
