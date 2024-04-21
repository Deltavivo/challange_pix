package com.itau.pix.service;

import com.itau.pix.converter.PixEntityToSearchPixResponseDTO;
import com.itau.pix.dto.*;
import com.itau.pix.entities.PixEntity;
import com.itau.pix.enums.KeyType;
import com.itau.pix.exceptions.UnsupportedPixException;
import com.itau.pix.repository.PixRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static java.util.stream.Collectors.toCollection;

@RequiredArgsConstructor
@Service
public class PixService {

    private final SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final Logger log= LoggerFactory.getLogger(PixService.class);

    private final PixRepository repository;

    private final PixEntityToSearchPixResponseDTO toApi;

    public CreatePixResponseDTO createPix(CreatePixRequestDTO pixDTO) {

        //TODO:verify if the key exist
        if(existPix(pixDTO.getKeyType(),pixDTO.getKeyValue())){
            log.error("Key PIX already exists! KeyValue: " + pixDTO.getKeyValue());
            throw new UnsupportedPixException("Chave PIX já existe!");
        }

        if(!isValid(pixDTO.getKeyType(), pixDTO.getKeyValue())){
            log.error("Key PIX is not valid! KeyValue: " + pixDTO.getKeyValue());
            throw new UnsupportedPixException("Chave PIX não é valida!");
        }

        PixEntity pix = PixEntity.builder()
                .keyType(pixDTO.getKeyType())
                .keyValue(pixDTO.getKeyValue())
                .accountType(pixDTO.getAccountType())
                .account(pixDTO.getAccount())
                .agency(pixDTO.getAgency())
                .accountHolderName(pixDTO.getAccountHolderName())
                .accountHolderSurname(pixDTO.getAccountHolderSurname())
                .dateTimeKeyIncluded(getDate()).build();

        //save in database
        PixEntity pixSaved = repository.save(pix);
        log.info("New PIX create with success! ID: " + pix.getId());

        return CreatePixResponseDTO.builder().id(pixSaved.getId()).build();

    }

    public SearchPixResponseDTO findById(String id){

        Optional<PixEntity> pixData = repository.findById(UUID.fromString(id));

        if(pixData.isPresent()){
            log.info("PIX found with success! ID: " + pixData.get().getId());

            return SearchPixResponseDTO.builder()
                    .id(pixData.get().getId())
                    .accountType(pixData.get().getAccountType())
                    .keyType(pixData.get().getKeyType())
                    .keyValue(pixData.get().getKeyValue())
                    .account(pixData.get().getAccount())
                    .agency(pixData.get().getAgency())
                    .accountHolderName(pixData.get().getAccountHolderName())
                    .accountHolderSurname(pixData.get().getAccountHolderSurname())
                    .dateTimeKeyIncluded(pixData.get().getDateTimeKeyIncluded())
                    .dateTimeKeyInactivation(pixData.get().getDateTimeKeyInactivation()).build();

        }

        log.error("Registry not found. ID: " + id);
        throw new UnsupportedPixException("Registro nao encontrado.");
    }

    public UpdatePixResponseDTO updatePix(String id, UpdatePixRequestDTO pixDTO) {
        //verify if the key exist

        Optional<PixEntity> pixData = repository.findById(UUID.fromString(id));

        if(pixData.isEmpty()){
            log.info("PIX not found. ID: " + id);
            throw new UnsupportedPixException("Registro PIX não encontrado.");
        }

        //verify if the key is inactive
        if(!(pixData.get().getDateTimeKeyInactivation() == null)){
            log.info("PIX is inactive! ID: " + id);
            throw new UnsupportedPixException("Registro PIX inativo.");
        }
        //cant update id, type or value of key
        if(pixData.isPresent()){
            log.info("PIX found with success! ID: " + pixData.get().getId());

            PixEntity updatePix = PixEntity.builder()
                    .keyType(pixData.get().getKeyType())
                    .keyValue(pixData.get().getKeyValue())
                    .accountType(pixDTO.getAccountType())
                    .account(pixDTO.getAccount())
                    .agency(pixDTO.getAgency())
                    .accountHolderName(pixDTO.getAccountHolderName())
                    .accountHolderSurname(pixDTO.getAccountHolderSurname())
                    .dateTimeKeyIncluded(Calendar.getInstance().getTime()).build();

            repository.save(updatePix);
            log.info("PIX updated with success! ID: " + updatePix.getId());

            return UpdatePixResponseDTO.builder()
                    .id(updatePix.getId())
                    .keyType(updatePix.getKeyType())
                    .keyValue(updatePix.getKeyValue())
                    .accountType(updatePix.getAccountType())
                    .agency(updatePix.getAgency())
                    .account(updatePix.getAccount())
                    .accountHolderName(updatePix.getAccountHolderName())
                    .accountHolderSurname(updatePix.getAccountHolderSurname())
                    .dateTimeKeyIncluded(updatePix.getDateTimeKeyIncluded()).build();

        }
        log.error("Registry not found. ID: " + id);
        throw new UnsupportedPixException("Registro nao encontrado.");
    }

    public InactivePixResponseDTO inactivePix(String id){

        //verify if exist Pix
        Optional<PixEntity> pixData = repository.findById(UUID.fromString(id));

        if(pixData.isPresent()){

            log.info("PIX found with success! ID: " + pixData.get().getId());

            //verify if pix was inactive
            if(!(pixData.get().getDateTimeKeyInactivation() == null)){
                log.error("PIX registration is already inactive. ID: " + pixData.get().getId());
                throw new UnsupportedPixException("Registro PIX já está inativo.");
            }

            //update inactivate data
            PixEntity updatedPix = pixData.get();
            updatedPix.setDateTimeKeyInactivation(Calendar.getInstance().getTime());

            //save in database
            repository.save(updatedPix);
            log.info("PIX saved with success! ID: " + updatedPix.getId());

            //create response dto
            return InactivePixResponseDTO.builder()
                    .id(pixData.get().getId())
                    .accountType(pixData.get().getAccountType())
                    .keyType(pixData.get().getKeyType())
                    .keyValue(pixData.get().getKeyValue())
                    .account(pixData.get().getAccount())
                    .agency(pixData.get().getAgency())
                    .accountHolderName(pixData.get().getAccountHolderName())
                    .accountHolderSurname(pixData.get().getAccountHolderSurname())
                    .dateTimeKeyIncluded(pixData.get().getDateTimeKeyIncluded())
                    .dateTimeKeyInactivation(Calendar.getInstance().getTime()).build();

        }
        log.error("Registry not found. ID: " + pixData.get().getId());
        throw new UnsupportedPixException("Registro nao encontrado.");
    }

    public List<SearchPixResponseDTO> findByKeyType(KeyType keyType){

        log.info("Search by KeyType: " + keyType);
        return repository.findByKeyType(keyType)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));

    }

    public List<SearchPixResponseDTO> findByAgencyAndAccount(String agency, String account){

        log.info("Search by Agency:" + agency +" and Account: " + account);
        return repository.findByAgencyAndAccount(agency, account)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));
    }

    public List<SearchPixResponseDTO> findByAccountHolderName(String accountHolderName){

        log.info("Search by holder name: " + accountHolderName);
        return repository.findByAccountHolderName(accountHolderName)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));
    }

    public List<SearchPixResponseDTO> findByInclusionDate(String dateTimeKeyIncluded) {

        Date date;

        try {
            date = textFormat.parse(dateTimeKeyIncluded);
        } catch (ParseException ex) {
            throw new UnsupportedPixException("A data informada é inválida. Utilize o padrão yyyy-MM-dd.");
        }

        log.info("Search by inclusion date: " + dateTimeKeyIncluded);
        return repository.findByDateTimeKeyIncluded(date)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));
    }

    public List<SearchPixResponseDTO> findByInactivationDate(String dateTimeKeyInactivation){

        Date date;

        try {
            date = textFormat.parse(dateTimeKeyInactivation);
        } catch (ParseException ex) {
            throw new UnsupportedPixException("A data informada é inválida. Utilize o padrão yyyy-MM-dd.");
        }

        log.info("Search by inactivation date: " + dateTimeKeyInactivation);
        return repository.findByDateTimeKeyInactivation(date)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));
    }

    private static final String CELLPHONE_PATTERN = "(?:(?:\\+|00)55\\s?)?(\\d{3})?(?:((?:9\\d|[2-9])\\d{7}))$";
    private static final String EMAIL_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String CPF_PATTERN = "\\d{11}$";
    private static final String CNPJ_PATTERN = "\\d{14}$";
    private static final String RANDOM_PATTERN = "\\w{36}";

    private static final Pattern celPattern = Pattern.compile(CELLPHONE_PATTERN);
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern cpfPattern = Pattern.compile(CPF_PATTERN);
    private static final Pattern cnpjPattern = Pattern.compile(CNPJ_PATTERN);
    private static final Pattern randomPattern = Pattern.compile(RANDOM_PATTERN);

    private static boolean isValid(KeyType type, String value) {

        switch (type) {
            case CELULAR:
                Matcher celMatcher = celPattern.matcher(value);
                return (celMatcher.matches());

            case EMAIL:
                Matcher emailMatcher = emailPattern.matcher(value);
                return (emailMatcher.matches());

            case CPF:
                Matcher cpfMatcher = cpfPattern.matcher(value);
                return (cpfMatcher.matches());

            case CNPJ:
                Matcher cnpjMatcher = cnpjPattern.matcher(value);
                return (cnpjMatcher.matches());

            case ALEATORIO:
                Matcher randomMatcher = randomPattern.matcher(value);
                return (randomMatcher.matches());

            default:
                return false;

        }
    }

    private boolean existPix(KeyType type, String value){
        //validate if can save this new key
        Optional<PixEntity> pixExists = Optional.ofNullable(repository.findByKeyTypeAndKeyValue(type, value));
        if(pixExists.isPresent()){
            return true;
        }
        return false;
    }

    private Date getDate(){

        Date date = new Date();

        try {
            date = textFormat.parse(new Date().toString());
        } catch (ParseException ex) {
            
        }
        return date;
    }


}
