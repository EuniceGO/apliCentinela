package com.example.centinela_api.service;

import com.example.centinela_api.modelos.Usuario;
import com.example.centinela_api.interfaces.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    IUsuario data;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


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
        // Si la contraseña no está encriptada (no comienza con $2a/$2b), encriptarla
        if (usuario.getContrasena() != null) {
            String stored = usuario.getContrasena();
            if (!(stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$"))) {
                usuario.setContrasena(passwordEncoder.encode(stored));
            }
        }
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
                String newPass = usuarioDetails.getContrasena();
                if (!(newPass.startsWith("$2a$") || newPass.startsWith("$2b$") || newPass.startsWith("$2y$"))) {
                    usuario.setContrasena(passwordEncoder.encode(newPass));
                } else {
                    usuario.setContrasena(newPass);
                }
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

    /**
     * Autentica un usuario por correo y contraseña.
     * Si la contraseña en DB está en texto plano y coincide, la actualiza a BCrypt.
     */
    public Optional<Usuario> authenticate(String correo, String contrasena) {
        Optional<Usuario> opt = data.findByCorreo(correo);
        if (opt.isEmpty()) return Optional.empty();
        Usuario u = opt.get();
        String stored = u.getContrasena();
        if (stored == null) return Optional.empty();

        boolean matches = false;
        if (stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$")) {
            matches = passwordEncoder.matches(contrasena, stored);
        } else {
            // contraseña almacenada en texto plano: comparar y actualizar a BCrypt
            if (stored.equals(contrasena)) {
                matches = true;
                u.setContrasena(passwordEncoder.encode(contrasena));
                data.save(u);
            }
        }

        return matches ? Optional.of(u) : Optional.empty();
    }
}