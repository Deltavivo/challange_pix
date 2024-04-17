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
public class CreatePixResponseDTO {

    private UUID id;
}
