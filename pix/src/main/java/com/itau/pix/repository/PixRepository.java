package com.itau.pix.repository;


import com.itau.pix.entities.Pix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PixRepository extends JpaRepository<Pix, UUID> {

}
