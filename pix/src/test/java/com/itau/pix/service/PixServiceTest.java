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

    private final Date date = Calendar.getInstance().getTime();

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
    void createPixSuccessEMAIL() {

        Mockito.when(repository.findByKeyTypeAndKeyValue(any(),any())).thenReturn(null);

        CreatePixRequestDTO request = CreatePixRequestDTO.builder()
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.EMAIL)
                .keyValue("julio.aguiar@gmail.com")
                .build();

        PixEntity pix = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.EMAIL)
                .keyValue("julio.aguiar@gmail.com")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.save(any())).thenReturn(pix);
        CreatePixResponseDTO response = service.createPix(request);

        //Sucess save in database new pix
        assertEquals(uuid, response.getId());
    }

    @Test
    void createPixSuccessCELULAR() {

        Mockito.when(repository.findByKeyTypeAndKeyValue(any(),any())).thenReturn(null);

        CreatePixRequestDTO request = CreatePixRequestDTO.builder()
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CELULAR)
                .keyValue("+5511923485272")
                .build();

        PixEntity pix = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CELULAR)
                .keyValue("+5511923485272")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.save(any())).thenReturn(pix);
        CreatePixResponseDTO response = service.createPix(request);

        //Sucess save in database new pix
        assertEquals(uuid, response.getId());
    }

    @Test
    void createPixSuccessCNPJ() {

        Mockito.when(repository.findByKeyTypeAndKeyValue(any(),any())).thenReturn(null);

        CreatePixRequestDTO request = CreatePixRequestDTO.builder()
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CNPJ)
                .keyValue("92387492347923")
                .build();

        PixEntity pix = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.CNPJ)
                .keyValue("92387492347923")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.save(any())).thenReturn(pix);
        CreatePixResponseDTO response = service.createPix(request);

        //Sucess save in database new pix
        assertEquals(uuid, response.getId());
    }

    @Test
    void createPixSuccessALEATORIO() {

        Mockito.when(repository.findByKeyTypeAndKeyValue(any(),any())).thenReturn(null);

        CreatePixRequestDTO request = CreatePixRequestDTO.builder()
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.ALEATORIO)
                .keyValue("155456789012345678901234567890123456")
                .build();

        PixEntity pix = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar")
                .keyType(KeyType.ALEATORIO)
                .keyValue("155456789012345678901234567890123456")
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

        UpdatePixRequestDTO request = UpdatePixRequestDTO.builder()
                .accountType(AccountType.POUPANCA)
                .agency("1234")
                .account("54321")
                .accountHolderName("Julio")
                .accountHolderSurname("Aguiar Moreira")
                .build();

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
        Mockito.when(repository.save(any())).thenReturn(pix);
        UpdatePixResponseDTO response = service.updatePix(uuid.toString(),request);

        //Sucess save in database new pix
        assertEquals(AccountType.POUPANCA, response.getAccountType());
        assertEquals("Aguiar Moreira",response.getAccountHolderSurname());
    }

    @Test
    void inactivePix() {

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
    void findByKeyTypeCPF() {

        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.CPF)
                .keyValue("83027502423")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.findByKeyType(any())).thenReturn(getPixEntity());
        Mockito.when(toApi.apply(any())).thenReturn(response);
        List<SearchPixResponseDTO> responseList = service.findByKeyType(KeyType.CPF);

        assertEquals(uuid, responseList.get(0).getId());

    }

    @Test
    void findByKeyTypeEMAIL() {

        List<PixEntity> dataList = new ArrayList<>();

        PixEntity pix1 = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.EMAIL)
                .keyValue("tulio.sodre@hotmail.com")
                .dateTimeKeyIncluded(date)
                .dateTimeKeyInactivation(null)
                .build();

        dataList.add(pix1);
        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.EMAIL)
                .keyValue("tulio.sodre@hotmail.com")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.findByKeyType(any())).thenReturn(dataList);
        Mockito.when(toApi.apply(any())).thenReturn(response);
        List<SearchPixResponseDTO> responseList = service.findByKeyType(KeyType.EMAIL);

        assertEquals(uuid, responseList.get(0).getId());
        assertEquals(KeyType.EMAIL,responseList.get(0).getKeyType());
        assertEquals("tulio.sodre@hotmail.com", responseList.get(0).getKeyValue());
    }

    @Test
    void findByKeyTypeALEATORIO() {

        List<PixEntity> dataList = new ArrayList<>();

        PixEntity pix1 = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.ALEATORIO)
                .keyValue("155456789012345678901234567890123456")
                .dateTimeKeyIncluded(date)
                .dateTimeKeyInactivation(null)
                .build();

        dataList.add(pix1);
        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.ALEATORIO)
                .keyValue("155456789012345678901234567890123456")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.findByKeyType(any())).thenReturn(dataList);
        Mockito.when(toApi.apply(any())).thenReturn(response);
        List<SearchPixResponseDTO> responseList = service.findByKeyType(KeyType.ALEATORIO);

        assertEquals(uuid, responseList.get(0).getId());
        assertEquals(KeyType.ALEATORIO,responseList.get(0).getKeyType());
        assertEquals("155456789012345678901234567890123456", responseList.get(0).getKeyValue());
    }

    @Test
    void findByKeyTypeCNPJ() {

        List<PixEntity> dataList = new ArrayList<>();

        PixEntity pix1 = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.CNPJ)
                .keyValue("15545678901234")
                .dateTimeKeyIncluded(date)
                .dateTimeKeyInactivation(null)
                .build();

        dataList.add(pix1);
        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.CNPJ)
                .keyValue("15545678901234")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.findByKeyType(any())).thenReturn(dataList);
        Mockito.when(toApi.apply(any())).thenReturn(response);
        List<SearchPixResponseDTO> responseList = service.findByKeyType(KeyType.CNPJ);

        assertEquals(uuid, responseList.get(0).getId());
        assertEquals(KeyType.CNPJ,responseList.get(0).getKeyType());
        assertEquals("15545678901234", responseList.get(0).getKeyValue());
    }

    @Test
    void findByKeyTypeCELULAR() {

        List<PixEntity> dataList = new ArrayList<>();

        PixEntity pix1 = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.CELULAR)
                .keyValue("+5511943248727")
                .dateTimeKeyIncluded(date)
                .dateTimeKeyInactivation(null)
                .build();

        dataList.add(pix1);
        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.CELULAR)
                .keyValue("+5511943248727")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(null)
                .build();

        Mockito.when(repository.findByKeyType(any())).thenReturn(dataList);
        Mockito.when(toApi.apply(any())).thenReturn(response);
        List<SearchPixResponseDTO> responseList = service.findByKeyType(KeyType.CELULAR);

        assertEquals(uuid, responseList.get(0).getId());
        assertEquals(KeyType.CELULAR,responseList.get(0).getKeyType());
        assertEquals("+5511943248727", responseList.get(0).getKeyValue());
    }

    @Test
    void findByAgencyAndAccount() {

        Mockito.when(repository.findByAgencyAndAccount(any(),any())).thenReturn(getPixEntity());
        Mockito.when(toApi.apply(any())).thenReturn(getSearchPixResponseDTO());
        List<SearchPixResponseDTO> responseList = service.findByAgencyAndAccount("1234","54321");

        assertEquals(uuid, responseList.get(0).getId());
        assertEquals("1234",responseList.get(0).getAgency());
        assertEquals("54321",responseList.get(0).getAccount());
    }

    @Test
    void findByAccountHolderName() {
        Mockito.when(repository.findByAccountHolderName(any())).thenReturn(getPixEntity());
        Mockito.when(toApi.apply(any())).thenReturn(getSearchPixResponseDTO());
        List<SearchPixResponseDTO> responseList = service.findByAccountHolderName("Patricia");

        assertEquals(uuid, responseList.get(0).getId());
        assertEquals("1234",responseList.get(0).getAgency());
        assertEquals("54321",responseList.get(0).getAccount());
    }

    @Test
    void findByInclusionDate() {

        Mockito.when(repository.findByDateTimeKeyIncluded(any())).thenReturn(getPixEntity());
        Mockito.when(toApi.apply(any())).thenReturn(getSearchPixResponseDTO());
        List<SearchPixResponseDTO> responseList = service.findByInclusionDate(date);

        assertEquals(uuid, responseList.get(0).getId());
        assertEquals(date,responseList.get(0).getDateTimeKeyIncluded());
    }

    @Test
    void findByInactivationDate() {

        List<PixEntity> dataList = new ArrayList<>();

        PixEntity pix1 = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.CPF)
                .keyValue("83027502423")
                .dateTimeKeyIncluded(date)
                .dateTimeKeyInactivation(date)
                .build();

        dataList.add(pix1);

        SearchPixResponseDTO response = SearchPixResponseDTO.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.CPF)
                .keyValue("83027502423")
                .dateTimeKeyIncluded(Calendar.getInstance().getTime())
                .dateTimeKeyInactivation(date)
                .build();


        Mockito.when(repository.findByDateTimeKeyInactivation(any())).thenReturn(dataList);
        Mockito.when(toApi.apply(any())).thenReturn(response);
        List<SearchPixResponseDTO> responseList = service.findByInactivationDate(date);

        assertEquals(uuid, responseList.get(0).getId());
        assertEquals(date,responseList.get(0).getDateTimeKeyInactivation());
    }

    public List<PixEntity> getPixEntity(){
        List<PixEntity> dataList = new ArrayList<>();

        PixEntity pix1 = PixEntity.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.CPF)
                .keyValue("83027502423")
                .dateTimeKeyIncluded(date)
                .dateTimeKeyInactivation(null)
                .build();

        dataList.add(pix1);
        return dataList;
    }

    private SearchPixResponseDTO getSearchPixResponseDTO(){

        return SearchPixResponseDTO.builder()
                .id(uuid)
                .accountType(AccountType.CORRENTE)
                .agency("1234")
                .account("54321")
                .accountHolderName("Tulio")
                .accountHolderSurname("Sodre")
                .keyType(KeyType.CPF)
                .keyValue("83027502423")
                .dateTimeKeyIncluded(date)
                .dateTimeKeyInactivation(null)
                .build();
    }
}