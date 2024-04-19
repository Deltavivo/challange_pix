package com.itau.pix.converter;

import com.itau.pix.dto.SearchPixResponseDTO;
import com.itau.pix.entities.PixEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class PixEntityToSearchPixResponseDTO implements Function<PixEntity, SearchPixResponseDTO> {

    @Override
    public SearchPixResponseDTO apply(PixEntity entity){
        return SearchPixResponseDTO.builder()
                .id(entity.getId())
                .keyType(entity.getKeyType())
                .keyValue(entity.getKeyValue())
                .accountType(entity.getAccountType())
                .agency(entity.getAgency())
                .account(entity.getAccount())
                .accountHolderName(entity.getAccountHolderName())
                .accountHolderSurname(entity.getAccountHolderSurname())
                .dateTimeKeyIncluded(entity.getDateTimeKeyIncluded())
                .dateTimeKeyInactivation(entity.getDateTimeKeyInactivation()).build();
    }
}
