package com.itau.pix.controller;

import com.itau.pix.dto.*;
import com.itau.pix.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.itau.pix.service.PixService;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private PixService pixService;

    @PostMapping
    public ResponseEntity<CreatePixResponseDTO> createPix(@RequestBody CreatePixRequestDTO pixDTO){
        return new ResponseEntity<>(pixService.createPix(pixDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatePixResponseDTO> updatePix(@PathVariable String id, @RequestBody UpdatePixRequestDTO pixDTO){
        return new ResponseEntity<>(pixService.updatePix(id,pixDTO), HttpStatus.OK);
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity<DeletePixResponseDTO> inactivePix(@PathVariable String id){
        return new ResponseEntity<>(pixService.deletePix(id),HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<SearchPixResponseDTO> findByID(@PathVariable String id){
        return new ResponseEntity<>(pixService.findById(id),HttpStatus.OK);
    }

//    @GetMapping("/findByKeyType/{keyType}")
//    public ResponseEntity<List<SearchPixResponseDTO>> findByKeyType(@PathVariable String keyType){
//        return new ResponseEntity<List<SearchPixResponseDTO>>(pixService.findByKeyType(keyType),HttpStatus.OK);
//
//        try{
//
//        }catch(Exception ex){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/findByAgencyAndAccount/{agencyAndAccount}")
//    public ResponseEntity<SearchPixResponseDTO> findByAgencyAndAccount(@PathVariable String agencyAndAccount){
//        return new ResponseEntity<SearchPixResponseDTO>(pixService.findByAgencyAndAccount(agencyAndAccount),HttpStatus.OK);
//    }
//
//    @GetMapping("/findByAccountHolderName/{accountHolderName}")
//    public ResponseEntity<SearchPixResponseDTO> findByAccountHolderName(@PathVariable String accountHolderName ){
//        return new ResponseEntity<SearchPixResponseDTO>(pixService.findByAccountHolderName(accountHolderName),HttpStatus.OK);
//    }
//
//    @GetMapping("/findByKeyInclusionDate/{keyInclusionDate}")
//    public ResponseEntity<SearchPixResponseDTO> findByKeyInclusionDate(@PathVariable String keyInclusionDate){
//        return new ResponseEntity<SearchPixResponseDTO>(pixService.findByKeyInclusionDate(keyInclusionDate),HttpStatus.OK);
//    }

//    @GetMapping("/findByKeyInactivationDate/{keyInactivationDate}")
//    public ResponseEntity<List<SearchPixResponseDTO>> findByKeyInactivationDate(@PathVariable String keyInactivationDate){
//        return new ResponseEntity<List<SearchPixResponseDTO>>(pixService.findByKeyInactivationDate(keyInactivationDate),HttpStatus.OK);
//    }

}
