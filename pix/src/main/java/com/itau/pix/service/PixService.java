package com.itau.pix.service;

import com.itau.pix.dto.CreatePixDTO;
import com.itau.pix.dto.DeletePixResponseDTO;
import com.itau.pix.dto.SearchPixResponseDTO;
import com.itau.pix.dto.UpdatePixResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PixService {
    public void createPix(CreatePixDTO createPixDTO) {

    }

    public UpdatePixResponseDTO updateKey(Long id) {
        return new UpdatePixResponseDTO();
    }

    public DeletePixResponseDTO deleteKey(Long id) {
        return new DeletePixResponseDTO();
    }

    public SearchPixResponseDTO findByID(String id) {
        return new SearchPixResponseDTO();
    }

    public SearchPixResponseDTO findByKeyType(String keyType) {
        return new SearchPixResponseDTO();
    }

    public SearchPixResponseDTO findByAgencyAndAccount(String agencyAndAccount) {
        return new SearchPixResponseDTO();
    }

    public SearchPixResponseDTO findByAccountHolderName(String accountHolderName) {
        return new SearchPixResponseDTO();
    }

    public SearchPixResponseDTO findByKeyInclusionDate(String keyInclusionDate) {
        return new SearchPixResponseDTO();
    }

    public SearchPixResponseDTO findByKeyInactivationDate(String keyInactivationDate) {
        return new SearchPixResponseDTO();
    }
}
