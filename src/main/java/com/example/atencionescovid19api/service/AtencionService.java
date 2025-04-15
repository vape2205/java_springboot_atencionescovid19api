package com.example.atencionescovid19api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.atencionescovid19api.dto.AtencionDTO;

public interface AtencionService {
    List<AtencionDTO> getAll();
    AtencionDTO getById(Long id);
    AtencionDTO create(AtencionDTO dto);
    AtencionDTO update(Long id, AtencionDTO dto);
    void delete(Long id);
    void save(MultipartFile file);
}
