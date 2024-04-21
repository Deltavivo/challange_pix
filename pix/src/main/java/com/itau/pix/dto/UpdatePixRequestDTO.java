package com.itau.pix.dto;

import com.itau.pix.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePixRequestDTO {

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
