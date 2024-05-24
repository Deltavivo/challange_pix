package com.itau.pix.converter;

import com.itau.pix.entities.PixEntity;
import com.itau.pix.enums.AccountType;
import com.itau.pix.enums.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PixEntityToSearchPixResponseDTOTest {

    private PixEntityToSearchPixResponseDTO converter;

    @BeforeEach
    void  setUp(){
        converter = new PixEntityToSearchPixResponseDTO();
    }

    @Test
    void apply(){
        final UUID id = UUID.randomUUID();
        final KeyType keyType = KeyType.ALEATORIO;
        final String keyValue = "153456789012345678901234567890123456";
        final AccountType accountType = AccountType.CORRENTE;
        final String account = "2341";
        final String agency = "09238";
        final String accountHolderName = "Jose";
        final String accountHolderSurname = "Sodre Ramos";
        final Date dateTimeKeyIncluded = new Date();
        final Date dateTimeKeyInactivation = new Date();

        var pixEntity = PixEntity.builder()
                .id(id)
                .keyType(keyType)
                .keyValue(keyValue)
                .accountType(accountType)
                .account(account)
                .agency(agency)
                .accountHolderName(accountHolderName)
                .accountHolderSurname(accountHolderSurname)
                .dateTimeKeyIncluded(dateTimeKeyIncluded)
                .dateTimeKeyInactivation(dateTimeKeyInactivation)
                .build();

        var result = converter.apply(pixEntity);

        assertEquals(id, result.getId());
        assertEquals(keyType, result.getKeyType());
        assertEquals(keyValue, result.getKeyValue());
        assertEquals(accountType, result.getAccountType());
        assertEquals(account, result.getAccount());
        assertEquals(agency, result.getAgency());
        assertEquals(accountHolderName , result.getAccountHolderName());
        assertEquals(accountHolderSurname , result.getAccountHolderSurname());
        assertEquals(dateTimeKeyIncluded, result.getDateTimeKeyIncluded());
        assertEquals(dateTimeKeyInactivation, result.getDateTimeKeyInactivation());
    }
}
