package com.itau.pix.controller;

import com.itau.pix.dto.*;
import com.itau.pix.enums.KeyType;
import com.itau.pix.repository.PixRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itau.pix.service.PixService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private PixService pixService;

    @PostMapping
    public ResponseEntity<CreatePixResponseDTO> createPix(@RequestBody @Valid CreatePixRequestDTO pixDTO){
        return new ResponseEntity<>(pixService.createPix(pixDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatePixResponseDTO> updatePix(@PathVariable String id, @RequestBody @Valid UpdatePixRequestDTO pixDTO){
        return new ResponseEntity<>(pixService.updatePix(id,pixDTO), HttpStatus.OK);
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity<InactivePixResponseDTO> inactivePix(@PathVariable String id){
        return new ResponseEntity<>(pixService.inactivePix(id),HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<SearchPixResponseDTO> findByID(@PathVariable String id){
        return new ResponseEntity<>(pixService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/findByKeyType/{keyType}")
    public ResponseEntity<List<SearchPixResponseDTO>> findByKeyType(@PathVariable KeyType keyType){
        return new ResponseEntity<List<SearchPixResponseDTO>>(pixService.findByKeyType(keyType),HttpStatus.OK);
    }

    @GetMapping("/findByAgencyAndAccount/{agency}/{account}")
    public ResponseEntity<List<SearchPixResponseDTO>> findByAgencyAndAccount(@PathVariable String agency,@PathVariable String account){
        return new ResponseEntity<List<SearchPixResponseDTO>>(pixService.findByAgencyAndAccount(agency, account),HttpStatus.OK);
    }

    @GetMapping("/findByAccountHolderName/{accountHolderName}")
    public ResponseEntity<List<SearchPixResponseDTO>> findByAccountHolderName(@PathVariable String accountHolderName ){
        return new ResponseEntity<List<SearchPixResponseDTO>>(pixService.findByAccountHolderName(accountHolderName),HttpStatus.OK);
    }

    @GetMapping("/findByKeyInclusionDate/{keyInclusionDate}")
    public ResponseEntity<List<SearchPixResponseDTO>> findByInclusionDate(@PathVariable String inclusionDate){
        return new ResponseEntity<List<SearchPixResponseDTO>>(pixService.findByInclusionDate(Date.valueOf(inclusionDate)),HttpStatus.OK);
    }

    @GetMapping("/findByKeyInactivationDate/{keyInactivationDate}")
    public ResponseEntity<List<SearchPixResponseDTO>> findByInactivationDate(@PathVariable String inactivationDate){
        return new ResponseEntity<List<SearchPixResponseDTO>>(pixService.findByInactivationDate(Date.valueOf(inactivationDate)),HttpStatus.OK);
    }

}
