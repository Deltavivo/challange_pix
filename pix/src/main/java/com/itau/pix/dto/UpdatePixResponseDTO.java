package com.itau.pix.dto;

import com.itau.pix.enums.AccountType;
import com.itau.pix.enums.KeyType;

import java.util.Date;
import java.util.UUID;

public class UpdatePixResponseDTO {
    UUID id;
    KeyType keyType;
    String keyValue;
    AccountType accountType;
    Integer agency;
    Integer account;
    String accountHolderName;
    String accountHolderSurname;
    Date dateTimeKeyIncluded;
}
