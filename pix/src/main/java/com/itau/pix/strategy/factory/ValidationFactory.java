package com.itau.pix.strategy.factory;

import com.itau.pix.enums.KeyType;
import com.itau.pix.exceptions.UnsupportedPixException;
import com.itau.pix.strategy.PixStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ValidationFactory {

    private final Map<KeyType, PixStrategy> validationType = new HashMap<>();

    public ValidationFactory(Set<PixStrategy> validationSet){
        validationSet.forEach(v -> validationType.put(v.getTypeValidation(),v));
    }

    public PixStrategy getValidation(KeyType keyType) throws Exception{
        PixStrategy pixStrategy = validationType.get(keyType);

        if(pixStrategy == null){
            throw new UnsupportedPixException("Falha ao buscar o tipo de chave.");
        }

        return pixStrategy;
    }
}
