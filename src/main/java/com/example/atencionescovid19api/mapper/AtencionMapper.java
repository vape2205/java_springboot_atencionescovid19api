package com.example.atencionescovid19api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.atencionescovid19api.dto.AtencionDTO;
import com.example.atencionescovid19api.model.Atencion;

@Mapper(componentModel = "spring")
public interface AtencionMapper {

    AtencionMapper INSTANCE = Mappers.getMapper(AtencionMapper.class);

    Atencion toEntity(AtencionDTO dto);

    AtencionDTO toDTO(Atencion domain);

    List<AtencionDTO> toDTOList(List<Atencion> list);
}
