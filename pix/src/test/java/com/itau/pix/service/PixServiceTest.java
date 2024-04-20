package com.itau.pix.service;

import com.itau.pix.converter.PixEntityToSearchPixResponseDTO;
import com.itau.pix.dto.*;
import com.itau.pix.entities.PixEntity;
import com.itau.pix.enums.AccountType;
import com.itau.pix.enums.KeyType;
import com.itau.pix.exceptions.UnsupportedPixException;
import com.itau.pix.repository.PixRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PixServiceTest {

    @InjectMocks
    PixService service;

    @Mock
    private PixRepository repository;

    @Mock
    private PixEntityToSearchPixResponseDTO toApi;

    private final UUID uuid = UUID.randomUUID();

    @Test
    void createPixSuccess() {

        Mockito.when(repository.findByKeyTypeAndKeyValue(any(),any())).thenReturn(null);

        CreatePixRequestDTO request = CreatePixRequestDTO.builder()
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CPF)
                .keyValue("63848372943")
                .build();

        PixEntity pix = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CPF)
                .keyValue("63848372943")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.save(any())).thenReturn(pix);
        CreatePixResponseDTO response = service.createPix(request);

        //Sucess save in database new pix
        assertEquals(uuid, response.getId());
    }

    @Test
    void createPixInvalid(){

        CreatePixRequestDTO request = CreatePixRequestDTO.builder()
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CPF)
                .keyValue("63848372943")
                .build();

        PixEntity pix = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CPF)
                .keyValue("63848372943")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.findByKeyTypeAndKeyValue(any(),any())).thenReturn(pix);
        //Fail save in database new pix
        UnsupportedPixException thrown = assertThrows(
                UnsupportedPixException.class,
                () -> service.createPix(request),"");

        assertTrue(thrown.getMessage().contains("Key PIX already exists!"));
    }

    @Test
    void findByIdSucess() {

        PixEntity pix = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.POUPANCA)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar Moreira")
                .keyType(KeyType.CPF)
                .keyValue("63848372943")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.findById(any())).thenReturn(Optional.ofNullable(pix));
        SearchPixResponseDTO response = service.findById(uuid.toString());

        //Sucess save in database new pix
        assertEquals(AccountType.POUPANCA, response.getAccountType());
        assertEquals("Aguiar Moreira",response.getAccountHolderSurname());
    }

    @Test
    void findByIdInvalid() {
//        PixEntity pix = PixEntity.builder()
//                .id(uuid)
//                .accountType(AccountType.POUPANCA)
//                .agency("1234")
//                .account("54321")
//                .accountHolderName("Julio")
//                .accountHolderSurname("Aguiar Moreira")
//                .keyType(KeyType.CPF)
//                .keyValue("63848372943")
//                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
//                .dateTimeKeyInactivation(null)
//                .build();
//
//        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());
//        SearchPixResponseDTO response = service.findById(uuid.toString());
//
//        //Fail save in database new pix
//        UnsupportedPixException thrown = assertThrows(
//                UnsupportedPixException.class,
//                () -> service.findById(uuid.toString()),"");
//
//        assertTrue(thrown.getMessage().contains("Registro nao encontrado."));
    }

    @Test
    void updatePix() {
//        Mockito.when(repository.findByKeyTypeAndKeyValue(any(),any())).thenReturn(null);
//
//        UpdatePixRequestDTO request = UpdatePixRequestDTO.builder()
//                .accountType(AccountType.CORRENTE)
//                .agency("1234")
//                .account("54321")
//                .accountHolderName("Julio")
//                .accountHolderSurname("Aguiar")
//                .build();
//
//
//        PixEntity pix = PixEntity.builder()
//                .id(uuid)
//                .accountType(AccountType.POUPANCA)
//                .agency("1234")
//                .account("54321")
//                .accountHolderName("Julio")
//                .accountHolderSurname("Aguiar Moreira")
//                .keyType(KeyType.CPF)
//                .keyValue("63848372943")
//                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
//                .dateTimeKeyInactivation(null)
//                .build();
//
//        Mockito.when(repository.findById(any())).thenReturn(Optional.ofNullable(pix));
//        Mockito.when(repository.save(any())).thenReturn(Optional.ofNullable(pix));
//        UpdatePixResponseDTO response = service.updatePix(uuid.toString(),request);
//
//        //Sucess save in database new pix
//        assertEquals(AccountType.POUPANCA, response.getAccountType());
//        assertEquals("Aguiar Moreira",response.getAccountHolderSurname());
    }

    @Test
    void inactivePixSucess() {

        PixEntity pix = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CPF)
                .keyValue("63848372943")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.findById(any())).thenReturn(Optional.ofNullable(pix));
        Mockito.when(repository.save(any())).thenReturn(pix);
        InactivePixResponseDTO response = service.inactivePix(uuid.toString());

        assertEquals(uuid, response.getId());
        assertNotNull(response.getDateTimeKeyInactivation());

    }

    @Test
    void findByKeyType() {

//        List<PixEntity> responseList = new ArrayList<>();
//
//        UUID uuid2 = UUID.randomUUID();
//
//        PixEntity pix1 = PixEntity.builder()
//                .id(uuid)
//                .accountType(AccountType.CORRENTE)
//                .agency("1234")
//                .account("54321")
//                .accountHolderName("Patricia")
//                .accountHolderSurname("Junqueira")
//                .keyType(KeyType.CPF)
//                .keyValue("63848372943")
//                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
//                .dateTimeKeyInactivation(null)
//                .build();
//
//        PixEntity pix2 = PixEntity.builder()
//                .id(uuid2)
//                .accountType(AccountType.CORRENTE)
//                .agency("1234")
//                .account("54321")
//                .accountHolderName("Tulio")
//                .accountHolderSurname("Sodre")
//                .keyType(KeyType.CPF)
//                .keyValue("83027502423")
//                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
//                .dateTimeKeyInactivation(null)
//                .build();
//
//        responseList.add(pix1);
//        responseList.add(pix2);
//
//        Mockito.when(repository.findByKeyType(any())).thenReturn(responseList);
//        List<SearchPixResponseDTO> response = service.findByKeyType(KeyType.CPF);
//
//        assertEquals(uuid, response.get(0).getId());
//        assertEquals(uuid2, response.get(1).getId());
//        assertEquals(response.get(0).getKeyType(),response.get(1).getKeyType());
//        assertNotEquals(response.get(0).getKeyValue(), response.get(1).getKeyValue());

    }

    @Test
    void findByAgencyAndAccount() {
    }

    @Test
    void findByAccountHolderName() {
    }

    @Test
    void findByInclusionDate() {
    }

    @Test
    void findByInactivationDate() {
    }
}