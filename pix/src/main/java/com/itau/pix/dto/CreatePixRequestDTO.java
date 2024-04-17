package com.itau.pix.dto;

import com.itau.pix.enums.KeyType;
import com.itau.pix.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePixRequestDTO {

    private KeyType keyType;
    private String keyValue;
    private AccountType accountType;
    private Integer agency;
    private Integer account;
    private String accountHolderName;
    private String accountHolderSurname;

}
