package com.itau.pix.dto;

import com.itau.pix.enums.KeyType;
import com.itau.pix.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Builder
@Getter
@Setter
public class CreatePixRequestDTO {

    private KeyType keyType;

    @Size(max = 77) @NotEmpty @NotNull @NotBlank
    private String keyValue;

    private AccountType accountType;

    @Size(max = 4) @NotEmpty @NotNull @NotBlank
    private String agency;

    @Size(max = 8) @NotEmpty @NotNull @NotBlank
    private String account;

    @Size(max = 30) @NotEmpty @NotNull @NotBlank
    private String accountHolderName;

    @Size(max = 45)
    private String accountHolderSurname;

}
