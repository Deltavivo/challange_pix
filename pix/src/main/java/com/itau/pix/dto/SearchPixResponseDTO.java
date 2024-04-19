package com.itau.pix.dto;

import com.itau.pix.enums.AccountType;
import com.itau.pix.enums.KeyType;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchPixResponseDTO {
    private UUID id;
    private KeyType keyType;
    private String keyValue;
    private AccountType accountType;
    private String agency;
    private String account;
    private String accountHolderName;
    private String accountHolderSurname;
    private Date dateTimeKeyIncluded;
    private Date dateTimeKeyInactivation;
}
