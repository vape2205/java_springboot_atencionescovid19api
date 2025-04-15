package com.example.atencionescovid19api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.atencionescovid19api.dto.AtencionDTO;
import com.example.atencionescovid19api.exception.ResourceNotFoundException;
import com.example.atencionescovid19api.helpers.CSVHelper;
import com.example.atencionescovid19api.mapper.AtencionMapper;
import com.example.atencionescovid19api.model.Atencion;
import com.example.atencionescovid19api.repository.AtencionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtencionServiceImpl implements AtencionService {
@Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private AtencionMapper atencionMapper;

    @Override
    public List<AtencionDTO> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        var list = atencionRepository.findAll(pageable).toList();
        return atencionMapper.toDTOList(list);
    }

    @Override
    public AtencionDTO getById(Long id) {
        Atencion atencion = atencionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atencion no encontrado con id: " + id));
        return atencionMapper.toDTO(atencion);
    }

    @Override
    public AtencionDTO create(AtencionDTO atencionDTO) {
        Atencion atencion = atencionMapper.toEntity(atencionDTO);
        Atencion savedAtencion = atencionRepository.save(atencion);
        return atencionMapper.toDTO(savedAtencion);
    }

    @Override
    public AtencionDTO update(Long id, AtencionDTO atencionDTO) {
        Atencion existingAtencion = atencionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atencion no encontrado con id: " + id));

        Atencion updatedAtencion = atencionRepository.save(existingAtencion);
        return atencionMapper.toDTO(updatedAtencion);
    }

    @Override
    public void delete(Long id) {
        Atencion atencion = atencionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atencion no encontrado con id: " + id));
                atencionRepository.delete(atencion);
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Atencion> entities = CSVHelper.loadObjectList(Atencion.class, file.getInputStream());;
            atencionRepository.saveAll(entities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
