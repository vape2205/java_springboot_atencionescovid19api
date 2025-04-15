package com.example.atencionescovid19api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.atencionescovid19api.dto.AtencionDTO;
import com.example.atencionescovid19api.helpers.CSVHelper;
import com.example.atencionescovid19api.service.AtencionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/atenciones")
@RequiredArgsConstructor
public class AtencionController {
    
    @Autowired
    private AtencionService atencionService;

    @PostMapping
    public ResponseEntity<AtencionDTO> crear(@RequestBody AtencionDTO request){
        var creado = atencionService.create(request);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtencionDTO> obtenerPorId(@PathVariable Long id) {
        var atencion = atencionService.getById(id);
        return ResponseEntity.ok(atencion);
    }

    @GetMapping
    public ResponseEntity<List<AtencionDTO>> obtenerTodos(@RequestParam(name = "page",defaultValue = "0") Integer page,
    @RequestParam(name = "size",defaultValue = "20") Integer size) {
        var lista = atencionService.getAll(page, size);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtencionDTO> actualizar(
            @PathVariable Long id,
            @RequestBody AtencionDTO request){
        var actualizado = atencionService.update(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        atencionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (!CSVHelper.hasCSVFormat(file)) {
            message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } 

        try {
            atencionService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
