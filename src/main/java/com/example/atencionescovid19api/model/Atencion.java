package com.example.atencionescovid19api.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Atencion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atencion_generator")
    @SequenceGenerator(name = "atencion_generator", sequenceName = "atencion_seq")
    public Long id;
    public Long id_persona;
    public Long id_eess;
    public String fecha_ingreso;
    public String hora_ingreso;
    public byte es_recuperado;
    public String fecha_alta;
    public byte es_recuperado_voluntario;
    public String fecha_alta_voluntaria;
    public byte es_fallecido;
    public String fecha_fallecido;
    public byte es_referido;
    public String fecha_referido;
    public Long eess_destino_id;
}
