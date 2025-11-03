package com.example.centinela_api.modelos;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class TipoReporteAttributeConverter implements AttributeConverter<Reporte.TipoReporte, String> {

    @Override
    public String convertToDatabaseColumn(Reporte.TipoReporte attribute) {
        if (attribute == null) return null;
        // Convert enum name with underscores to DB-friendly string with spaces
        return attribute.name().replace('_', ' ');
    }

    @Override
    public Reporte.TipoReporte convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) return null;
        String normalized = dbData.trim().replace(' ', '_');
        try {
            return Reporte.TipoReporte.valueOf(normalized);
        } catch (IllegalArgumentException ex) {
            // If value doesn't match, return null to let validation handle it
            return null;
        }
    }
}
