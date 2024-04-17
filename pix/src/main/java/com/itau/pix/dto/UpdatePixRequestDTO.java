package com.itau.pix.dto;

import com.itau.pix.enums.AccountType;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePixRequestDTO {
    private AccountType accountType;
    private Integer agency;
    private Integer account;
    private String accountHolderName;
    private String accountHolderSurname;
}
