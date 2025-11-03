package com.example.centinela_api.interfaces;

import com.example.centinela_api.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository; // Usamos JpaRepository
import org.springframework.stereotype.Repository;

import java.util.Optional; // Mejor usar Optional para búsquedas únicas

@Repository
// JpaRepository<Entidad, Tipo_de_ID> te da los métodos:
// save(), findAll(), findById(Integer), deleteById(Integer), etc.
public interface IUsuario extends JpaRepository<Usuario, Integer> {

    // 🔑 Método crucial para Lógica de Negocio/Autenticación
    // Busca un usuario por su correo. Útil para login y para validar registro.
    // Usamos Optional<Usuario> ya que el correo es ÚNICO y podría no existir.
    Optional<Usuario> findByCorreo(String correo);

    // NOTA: Los métodos findByPuesto, findByEstado, y findBySalario
    // han sido ELIMINADOS, ya que eran código reciclado no necesario para la API base.

}