package com.itau.pix.controller;

import com.itau.pix.dto.*;
import com.itau.pix.enums.AccountType;
import com.itau.pix.enums.KeyType;
import com.itau.pix.service.PixService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PixControllerTest {

    @InjectMocks
    PixController controller;

    @Mock
    PixService service;

    private final UUID uuid = UUID.randomUUID();

    @Test
    void createPixSucess() {

        CreatePixRequestDTO request = CreatePixRequestDTO.builder()
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CPF)
                .keyValue("63848372943")
                .build();

        CreatePixResponseDTO response = CreatePixResponseDTO.builder()
                        .id(UUID.randomUUID())
                        .build();

        Mockito.when(service.createPix(any())).thenReturn(response);


        ResponseEntity<CreatePixResponseDTO> rep = controller.createPix(request);

        assertEquals(HttpStatus.CREATED, rep.getStatusCode() );
        assertEquals(response, rep.getBody());
    }

    @Test
    void updatePix() {

        UpdatePixRequestDTO request = UpdatePixRequestDTO.builder()
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .build();

        UpdatePixResponseDTO response = UpdatePixResponseDTO.builder()
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .build();

        Mockito.when(service.updatePix(any(),any())).thenReturn(response);


        ResponseEntity<UpdatePixResponseDTO> rep = controller.updatePix(uuid.toString(),request);

        assertEquals(HttpStatus.OK, rep.getStatusCode());
        assertEquals(response, rep.getBody());
    }

    @Test
    void inactivePix() {

        InactivePixResponseDTO response = InactivePixResponseDTO.builder()
                .id(uuid)
                .build();

        Mockito.when(service.inactivePix(any())).thenReturn(response);

        ResponseEntity<InactivePixResponseDTO> rep = controller.inactivePix(uuid.toString());

        assertEquals(HttpStatus.OK, rep.getStatusCode());
        assertEquals(response, rep.getBody());
    }


    @Test
    void findByID() {

        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .keyType(KeyType.CPF)
                .keyValue("23485393856")
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .build();

        Mockito.when(service.findById(any())).thenReturn(response);

        ResponseEntity<SearchPixResponseDTO> rep = controller.findByID(uuid.toString());

        assertEquals(HttpStatus.OK, rep.getStatusCode());
        assertEquals(response, rep.getBody());
    }

    @Test
    void findByKeyType() {

        List<SearchPixResponseDTO> responseList = new ArrayList<>();

        SearchPixResponseDTO response1 = SearchPixResponseDTO.builder()
                .id(uuid)
                .keyType(KeyType.EMAIL)
                .keyValue("rogeio.assuncao@gmail.com")
                .accountType(AccountType.CORRENTE)
                .agency("3252")
                .account("765443")
                .accountHolderName("Rogerio")
                .accountHolderSurname("Assuncao")
                .build();

        SearchPixResponseDTO response2 = SearchPixResponseDTO.builder()
                .id(uuid)
                .keyType(KeyType.EMAIL)
                .keyValue("michele.barretos@ig.com.br")
                .accountType(AccountType.CORRENTE)
                .agency("5727")
                .account("17234")
                .accountHolderName("Michele")
                .accountHolderSurname("Barretos")
                .build();

        responseList.add(response1);
        responseList.add(response2);

        Mockito.when(service.findByKeyType(any())).thenReturn(responseList);

        ResponseEntity<List<SearchPixResponseDTO>> rep = controller.findByKeyType(KeyType.EMAIL);

        assertEquals(HttpStatus.OK, rep.getStatusCode());
        assertEquals(responseList, rep.getBody());
    }

    @Test
    void findByAgencyAndAccount() {

        List<SearchPixResponseDTO> responseList = new ArrayList<>();

        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .keyType(KeyType.CPF)
                .keyValue("23485393856")
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .build();

        responseList.add(response);

        Mockito.when(service.findByAgencyAndAccount(any(),any())).thenReturn(responseList);

        ResponseEntity<List<SearchPixResponseDTO>> rep = controller.findByAgencyAndAccount("1234","54321");

        assertEquals(HttpStatus.OK, rep.getStatusCode());
        assertEquals(responseList, rep.getBody());
    }

    @Test
    void findByAccountHolderName() {

        List<SearchPixResponseDTO> responseList = new ArrayList<>();

        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .keyType(KeyType.CPF)
                .keyValue("23485393856")
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .build();

        responseList.add(response);

        Mockito.when(service.findByAccountHolderName(any())).thenReturn(responseList);

        ResponseEntity<List<SearchPixResponseDTO>> rep = controller.findByAccountHolderName("Julio");

        assertEquals(HttpStatus.OK, rep.getStatusCode());
        assertEquals(responseList, rep.getBody());
    }

    @Test
    void findByKeyInclusionDate() {

        List<SearchPixResponseDTO> responseList = new ArrayList<>();

        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .keyType(KeyType.CPF)
                .keyValue("23485393856")
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .build();

        responseList.add(response);

        Mockito.when(service.findByInclusionDate(any())).thenReturn(responseList);

        ResponseEntity<List<SearchPixResponseDTO>> rep = controller.findByInclusionDate("2024-04-20");

        assertEquals(HttpStatus.OK, rep.getStatusCode());
        assertEquals(responseList, rep.getBody());
    }

    @Test
    void findByKeyInactivationDate() {

        List<SearchPixResponseDTO> responseList = new ArrayList<>();

        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .keyType(KeyType.CPF)
                .keyValue("23485393856")
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .build();

        responseList.add(response);

        Mockito.when(service.findByInactivationDate(any())).thenReturn(responseList);

        ResponseEntity<List<SearchPixResponseDTO>> rep = controller.findByInactivationDate("2024-04-20");

        assertEquals(HttpStatus.OK, rep.getStatusCode());
        assertEquals(responseList, rep.getBody());
    }

}