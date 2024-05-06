package com.itau.pix.strategy;

import com.itau.pix.dto.CreatePixRequestDTO;
import com.itau.pix.enums.KeyType;

public interface PixStrategy {

    void execute(CreatePixRequestDTO pixDTO);
    KeyType getTypeValidation();
}
