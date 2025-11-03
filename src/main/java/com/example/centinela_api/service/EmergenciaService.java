package com.example.centinela_api.service;

import com.example.centinela_api.modelos.Emergencia;
import com.example.centinela_api.interfaces.IEmergencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmergenciaService {

    @Autowired
    private IEmergencia data;

    public List<Emergencia> findAll() {
        return data.findAll();
    }

    public Optional<Emergencia> findById(Integer id) {
        return data.findById(id);
    }

    public Emergencia save(Emergencia emergencia) {
        return data.save(emergencia);
    }

    public void deleteById(Integer id) {
        data.deleteById(id);
    }

    public Emergencia update(Integer id, Emergencia emergenciaDetails) {
        Optional<Emergencia> existente = data.findById(id);
        if (existente.isPresent()) {
            Emergencia emergencia = existente.get();
            emergencia.setMensaje(emergenciaDetails.getMensaje());
            emergencia.setLatitud(emergenciaDetails.getLatitud());
            emergencia.setLongitud(emergenciaDetails.getLongitud());
            emergencia.setAtendido(emergenciaDetails.getAtendido());
            emergencia.setUsuario(emergenciaDetails.getUsuario());
            return data.save(emergencia);
        } else {
            return null;
        }
    }
}
