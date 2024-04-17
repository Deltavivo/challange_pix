package com.itau.pix.controller;

import com.itau.pix.dto.CreatePixDTO;
import com.itau.pix.dto.DeletePixResponseDTO;
import com.itau.pix.dto.SearchPixResponseDTO;
import com.itau.pix.dto.UpdatePixResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itau.pix.service.PixService;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @PostMapping
    public ResponseEntity registryKey(@RequestBody CreatePixDTO createPixDTO){
        pixService.createPix(createPixDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UpdatePixResponseDTO> updateKey(@PathVariable Long id){
        return new ResponseEntity<>(pixService.updateKey(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletePixResponseDTO> deleteKey(@PathVariable Long id){
        return new ResponseEntity<DeletePixResponseDTO>(pixService.deleteKey(id),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchPixResponseDTO> findByID(@PathVariable String id){
        return new ResponseEntity<SearchPixResponseDTO>(pixService.findByID(id),HttpStatus.OK);
    }

    @GetMapping("/{keyType}")
    public ResponseEntity<SearchPixResponseDTO> findByKeyType(@PathVariable String keyType){
        return new ResponseEntity<SearchPixResponseDTO>(pixService.findByKeyType(keyType),HttpStatus.OK);
    }

    @GetMapping("/{agencyAndAccount}")
    public ResponseEntity<SearchPixResponseDTO> findByAgencyAndAccount(@PathVariable String agencyAndAccount){
        return new ResponseEntity<SearchPixResponseDTO>(pixService.findByAgencyAndAccount(agencyAndAccount),HttpStatus.OK);
    }

    @GetMapping("/{accountHolderName}")
    public ResponseEntity<SearchPixResponseDTO> findByAccountHolderName(@PathVariable String accountHolderName ){
        return new ResponseEntity<SearchPixResponseDTO>(pixService.findByAccountHolderName(accountHolderName),HttpStatus.OK);
    }

    @GetMapping("/{keyInclusionDate}")
    public ResponseEntity<SearchPixResponseDTO> findByKeyInclusionDate(@PathVariable String keyInclusionDate){
        return new ResponseEntity<SearchPixResponseDTO>(pixService.findByKeyInclusionDate(keyInclusionDate),HttpStatus.OK);
    }

    @GetMapping("/{keyInactivationDate}")
    public ResponseEntity<SearchPixResponseDTO> findByKeyInactivationDate(@PathVariable String keyInactivationDate){
        return new ResponseEntity<SearchPixResponseDTO>(pixService.findByKeyInactivationDate(keyInactivationDate),HttpStatus.OK);
    }

}
