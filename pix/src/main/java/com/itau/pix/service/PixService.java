package com.itau.pix.service;

import com.itau.pix.converter.PixEntityToSearchPixResponseDTO;
import com.itau.pix.dto.*;
import com.itau.pix.entities.PixEntity;
import com.itau.pix.enums.KeyType;
import com.itau.pix.exceptions.UnsupportedPixException;
import com.itau.pix.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toCollection;


@Service
public class PixService {

    @Autowired
    private PixRepository repository;

    private final PixEntityToSearchPixResponseDTO toApi;

    public PixService(PixEntityToSearchPixResponseDTO toApi) {
        this.toApi = toApi;
    }

    public CreatePixResponseDTO createPix(CreatePixRequestDTO pixDTO) {

        //TODO:verify if the key exist

        PixEntity pix = PixEntity.builder()
                .keyType(pixDTO.getKeyType())
                .keyValue(pixDTO.getKeyValue())
                .accountType(pixDTO.getAccountType())
                .account(pixDTO.getAccount())
                .agency(pixDTO.getAgency())
                .accountHolderName(pixDTO.getAccountHolderName())
                .accountHolderSurname(pixDTO.getAccountHolderSurname())
                .dateTimeKeyIncluded(Calendar.getInstance().getTime()).build();

        //save in database
        PixEntity pixSaved = repository.save(pix);

        //TODO:verify if is valid
        return CreatePixResponseDTO.builder().id(pixSaved.getId()).build();

        //TODO:verify if is CPF or CNPJ and the limit to create

    }

    public SearchPixResponseDTO findById(String id){

        Optional<PixEntity> pixData = repository.findById(UUID.fromString(id));

        if(pixData.isPresent()){
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

        throw new UnsupportedPixException("Registro nao encontrado.");

    }

    public UpdatePixResponseDTO updatePix(String id, UpdatePixRequestDTO pixDTO) {
        //verify if the key exist
        Optional<PixEntity> pixData = repository.findById(UUID.fromString(id));

        //verify if the key is inactive
        if(!(pixData.get().getDateTimeKeyInactivation() == null)){
            throw new UnsupportedPixException("Registro PIX inativo.");
        }
        //cant update id, type or value of key
        if(pixData.isPresent()){
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
        throw new UnsupportedPixException("Registro nao encontrado.");
    }

    public DeletePixResponseDTO deletePix(String id){

        //verify if exist Pix
        Optional<PixEntity> pixData = repository.findById(UUID.fromString(id));

        if(pixData.isPresent()){

            //verify if pix was inactive
            if(!(pixData.get().getDateTimeKeyInactivation() == null)){
                throw new UnsupportedPixException("Registro PIX inativo.");
            }

            //update inactivate data
            PixEntity updatedPix = pixData.get();
            updatedPix.setDateTimeKeyInactivation(Calendar.getInstance().getTime());

            //save in database
            repository.save(updatedPix);

            //create response dto
            return DeletePixResponseDTO.builder()
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
        throw new UnsupportedPixException("Registro nao encontrado.");
    }

    public List<SearchPixResponseDTO> findByKeyType(KeyType keyType){

        return repository.findByKeyType(keyType)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));

    }

    public List<SearchPixResponseDTO> findByAgencyAndAccount(String agency, String account){

        return repository.findByAgencyAndAccount(agency, account)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));
    }

    public List<SearchPixResponseDTO> findByAccountHolderName(String accountHolderName){

        return repository.findByAccountHolderName(accountHolderName)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));
    }

    public List<SearchPixResponseDTO> findByInclusionDate(Date dateTimeKeyIncluded){

        return repository.findByDateTimeKeyIncluded(dateTimeKeyIncluded)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));
    }

    public List<SearchPixResponseDTO> findByInactivationDate(Date dateTimeKeyInactivation){

        return repository.findByDateTimeKeyInactivation(dateTimeKeyInactivation)
                .stream()
                .map(toApi)
                .collect(toCollection(ArrayList::new));
    }


    private static final String CELLPHONE_PATTERN = "(?:(?:\\+|00)55\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})?(\\d{4}))$";
    private static final String EMAIL_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String CPF_PATTERN = "/^\\d{11}$";
    private static final String CNPJ_PATTERN = "/^\\d{14}$";
    private static final String RANDOM_PATTERN = "/^[a-z][a-zA-Z0-9]{36}";

    private static final Pattern celPattern = Pattern.compile(CELLPHONE_PATTERN);
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern cpfPattern = Pattern.compile(CPF_PATTERN);
    private static final Pattern cnpjPattern = Pattern.compile(CNPJ_PATTERN);
    private static final Pattern randomPattern = Pattern.compile(RANDOM_PATTERN);

    private static boolean isCellphoneValid(String value){
        Matcher celMatcher = celPattern.matcher(value);
        return (celMatcher.matches());
    }

    private static boolean isEmailValid(String value){
        Matcher emailMatcher = emailPattern.matcher(value);
        return (emailMatcher.matches());
    }

    private static boolean isCPFValid(String value){
        Matcher cpfMatcher = cpfPattern.matcher(value);
        return (cpfMatcher.matches());
    }

    private static boolean isCNPJValid(String value){
        Matcher cnpjMatcher = cnpjPattern.matcher(value);
        return (cnpjMatcher.matches());
    }

    private static boolean isKeyValid(String value){
        Matcher randomMatcher = randomPattern.matcher(value);
        return (randomMatcher.matches());
    }

//    private static boolean isValid(String value, String type ) {
//
//        switch (type) {
//            case "CELULAR":
//                Matcher celMatcher = celPattern.matcher(value);
//                return (celMatcher.matches());
//
//            case "EMAIL":
//                Matcher emailMatcher = emailPattern.matcher(value);
//                return (emailMatcher.matches());
//
//            case "CPF":
//                Matcher cpfMatcher = cpfPattern.matcher(value);
//                return (cpfMatcher.matches());
//
//            case "CNPJ":
//                Matcher cnpjMatcher = cnpjPattern.matcher(value);
//                return (cnpjMatcher.matches());
//
//            case "ALEATORIO":
//                Matcher randomMatcher = randomPattern.matcher(value);
//                return (randomMatcher.matches());
//
//            default:
//                return false;
//
//        }
//    }

}
