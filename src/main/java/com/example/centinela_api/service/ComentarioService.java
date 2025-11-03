package com.example.centinela_api.service;

import com.example.centinela_api.modelos.Comentario;
import com.example.centinela_api.interfaces.IComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private IComentario data;

    public List<Comentario> findAll() {
        return data.findAll();
    }

    public Optional<Comentario> findById(Integer id) {
        return data.findById(id);
    }

    public Comentario save(Comentario comentario) {
        return data.save(comentario);
    }

    public void deleteById(Integer id) {
        data.deleteById(id);
    }

    public Comentario update(Integer id, Comentario comentarioDetails) {
        Optional<Comentario> existente = data.findById(id);
        if (existente.isPresent()) {
            Comentario comentario = existente.get();
            comentario.setMensaje(comentarioDetails.getMensaje());
            comentario.setReporte(comentarioDetails.getReporte());
            comentario.setUsuario(comentarioDetails.getUsuario());
            return data.save(comentario);
        } else {
            return null;
        }
    }
}
