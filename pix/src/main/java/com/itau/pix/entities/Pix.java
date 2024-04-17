package com.itau.pix.entities;

import com.itau.pix.enums.KeyType;
import com.itau.pix.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Entity
@Builder
@Table(name="PixRegistry")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private KeyType keyType;
    private String keyValue;
    private AccountType accountType;
    private Integer agency;
    private Integer account;
    private String accountHolderName;
    private String accountHolderSurname;
    private Date dateTimeKeyIncluded;
    private Date dateTimeKeyInactivation;

}
