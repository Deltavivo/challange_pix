package com.itau.pix.repository;


import com.itau.pix.entities.PixEntity;
import com.itau.pix.enums.KeyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface PixRepository extends JpaRepository<PixEntity, UUID> {

    List<PixEntity> findByKeyType(KeyType keyType);
    List<PixEntity> findByAgencyAndAccount(String agency, String account);
    List<PixEntity> findByAccountHolderName(String accountHolderName);
    List<PixEntity> findByDateTimeKeyIncluded(Date dateTimeKeyIncluded);
    List<PixEntity> findByDateTimeKeyInactivation(Date dateTimeKeyInactivation);
}
