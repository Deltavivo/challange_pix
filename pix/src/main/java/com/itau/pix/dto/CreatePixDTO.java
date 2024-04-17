package com.itau.pix.dto;

import com.itau.pix.enums.KeyType;
import com.itau.pix.enums.AccountType;

public class CreatePixDTO {

    KeyType keyType;
    String keyValue;
    AccountType accountType;
    Integer agency;
    Integer account;
    String accountHolderName;
    String accountHolderSurname;

}
