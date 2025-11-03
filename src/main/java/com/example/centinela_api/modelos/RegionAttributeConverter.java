package com.example.centinela_api.modelos;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Convierte entre el valor almacenado en la base de datos (p. ej. "Santa Ana Norte")
 * y el enum interno `Usuario.Region` (p. ej. `Santa_Ana_Norte`).
 *
 * Se aplica automáticamente a cualquier columna que use ese tipo enum.
 */
@Converter(autoApply = true)
public class RegionAttributeConverter implements AttributeConverter<Usuario.Region, String> {

    @Override
    public String convertToDatabaseColumn(Usuario.Region attribute) {
        if (attribute == null) return null;
        // Guardamos en la DB con espacios para mantener legibilidad
        return attribute.name().replace('_', ' ');
    }

    @Override
    public Usuario.Region convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        String trimmed = dbData.trim();
        if (trimmed.isEmpty()) return null; // tratar cadenas vacías como NULL

        // Normalizamos: reemplazamos espacios por guiones bajos y buscamos el enum.
        String candidate = trimmed.replace(' ', '_');
        try {
            return Usuario.Region.valueOf(candidate);
        } catch (IllegalArgumentException ex) {
            // Intento de recuperación: comparar de forma "amigable" (ignorar mayúsculas/minúsculas)
            for (Usuario.Region r : Usuario.Region.values()) {
                if (r.name().replace('_', ' ').equalsIgnoreCase(trimmed)) {
                    return r;
                }
            }
            // Si no encontramos una coincidencia razonable, relanzar la excepción original
            throw ex;
        }
    }
}
