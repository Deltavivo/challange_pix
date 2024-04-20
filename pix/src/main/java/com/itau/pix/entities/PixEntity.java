package com.itau.pix.entities;

import com.itau.pix.enums.KeyType;
import com.itau.pix.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="record")
@EqualsAndHashCode(of = "id")
@Entity
public class PixEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 9)
    private KeyType keyType;

    @Column(length = 99)
    private String keyValue;

    @Column(length = 10)
    private AccountType accountType;

    @Column(length = 4)
    private String agency;

    @Column(length = 8)
    private String account;

    @Column(length = 30)
    private String accountHolderName;

    @Column(length = 45)
    private String accountHolderSurname;

    private Date dateTimeKeyIncluded;

    private Date dateTimeKeyInactivation;

}
