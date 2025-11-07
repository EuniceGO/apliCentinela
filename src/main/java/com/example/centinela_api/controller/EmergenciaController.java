package com.example.centinela_api.controller;

import com.example.centinela_api.modelos.Emergencia;
import com.example.centinela_api.service.EmergenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://centinela-frontend.vercel.app"}, allowCredentials = "true")
@RequestMapping("/api/emergencias")
public class EmergenciaController {

    @Autowired
    private EmergenciaService emergenciaService;

    // ... (Métodos GET, POST, DELETE se mantienen igual) ...

    @GetMapping
    public ResponseEntity<List<Emergencia>> getAllEmergencias() {
        return new ResponseEntity<>(emergenciaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emergencia> getEmergenciaById(@PathVariable Integer id) {
        return emergenciaService.findById(id)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Emergencia> createEmergencia(@RequestBody Emergencia emergencia) {
        Emergencia nuevo = emergenciaService.save(emergencia);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // --- MÉTODO PUT MEJORADO PARA SOPORTAR ACTUALIZACIONES PARCIALES (PARCIAL O TOTAL) ---
    @PutMapping("/{id}")
    public ResponseEntity<Emergencia> updateEmergencia(@PathVariable Integer id, @RequestBody Emergencia emergenciaDetails) {

        // 1. Verificar si la emergencia existe
        Emergencia emergenciaExistente = emergenciaService.findById(id).orElse(null);

        if (emergenciaExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 2. Aplicar lógica para actualizar solo los campos presentes (incluyendo 'atendido').
        // Esta lógica debe estar en el servicio, pero la preparamos aquí para el método 'atendido':

        // Si el frontend envió el campo 'atendido', lo actualizamos.
        if (emergenciaDetails.getAtendido() != null) {
            emergenciaExistente.setAtendido(emergenciaDetails.getAtendido());
        }

        // Si el frontend envió 'mensaje', lo actualizamos (y así para latitud, longitud, etc.)
        if (emergenciaDetails.getMensaje() != null) {
            emergenciaExistente.setMensaje(emergenciaDetails.getMensaje());
        }

        // Aquí se deberían incluir todas las validaciones para otros campos (latitud, longitud, etc.)
        // para evitar que se pisen con null si no se envían.

        // 3. Guardar la entidad con los campos actualizados
        Emergencia actualizado = emergenciaService.save(emergenciaExistente);

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmergencia(@PathVariable Integer id) {
        if (emergenciaService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        emergenciaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}