package com.itau.pix.dto;

import com.itau.pix.enums.AccountType;
import com.itau.pix.enums.KeyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(max = 77) @NotEmpty @NotNull @NotBlank
    private String keyValue;

    @NotEmpty @NotNull
    private AccountType accountType;

    @Size(max = 4) @NotEmpty @NotNull @NotBlank
    private String agency;

    @Size(max = 8) @NotEmpty @NotNull @NotBlank
    private String account;

    @Size(max = 30) @NotEmpty @NotNull @NotBlank
    private String accountHolderName;

    private String accountHolderSurname;

    private Date dateTimeKeyIncluded;

    private Date dateTimeKeyInactivation;
}
