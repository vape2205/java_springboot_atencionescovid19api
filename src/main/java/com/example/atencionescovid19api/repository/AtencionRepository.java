package com.example.atencionescovid19api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.atencionescovid19api.model.Atencion;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Long> {

}
