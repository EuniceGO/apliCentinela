package com.example.centinela_api.service;

import com.example.centinela_api.modelos.Usuario;
import com.example.centinela_api.interfaces.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    IUsuario data;


    public List<Usuario> findAll( ){
        // JpaRepository permite esto directamente, sin casting
        return data.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        // Uso de Optional para una mejor gestión de la ausencia de resultados
        return data.findById(id);
    }

    /**
     * Nuevo método esencial para la API.
     * @param correo Correo del usuario a buscar.
     * @return Optional que contiene el Usuario si existe.
     */
    public Optional<Usuario> findByCorreo(String correo) {
        return data.findByCorreo(correo);
    }


    public Usuario save(Usuario usuario) {
        // Lógica de ENCRIPTACIÓN DE CONTRASEÑA
        return data.save(usuario);
    }

    public void deleteById(Integer id) {
        data.deleteById(id);
    }

    public Usuario update(Integer id, Usuario usuarioDetails) {
        Optional<Usuario> usuarioExistente = data.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();

            // Actualización de campos
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setCorreo(usuarioDetails.getCorreo());
            if (usuarioDetails.getContrasena() != null) {
                // ¡IMPORTANTE! Procesar/Encriptar aquí
                usuario.setContrasena(usuarioDetails.getContrasena());
            }
            usuario.setTelefono(usuarioDetails.getTelefono());
            usuario.setDepartamento(usuarioDetails.getDepartamento());
            usuario.setCiudad(usuarioDetails.getCiudad());
            usuario.setRegion(usuarioDetails.getRegion());

            return data.save(usuario);
        } else {
            return null;
        }
    }
}