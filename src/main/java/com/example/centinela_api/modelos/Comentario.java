package com.example.centinela_api.modelos;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id")
    private Integer comentarioId;

    // Relación ManyToOne con Reporte
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    // Relación ManyToOne con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String mensaje;

    /**
     * 📌 CAMPO AGREGADO: Almacena la fecha y hora de publicación.
     * No lleva anotaciones de timestamp automáticas para permitir el control manual
     * desde el Controller/Service.
     */
    @Column(name = "fecha")
    private LocalDateTime fecha;
}