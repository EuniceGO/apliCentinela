package com.example.centinela_api.modelos;
import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reporte_id")
    private Integer reporteId;

    // Relación ManyToOne con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoReporte tipo;

    private String descripcion;

    private Double latitud;
    private Double longitud;

    @Column(name = "foto_url")
    private String fotoUrl;

    @Enumerated(EnumType.STRING)
    private EstadoReporte estado = EstadoReporte.Activo;

    @Column(name = "fecha_reporte", insertable = false, updatable = false)
    private LocalDateTime fechaReporte;

    // Enum para el campo 'tipo'
    public enum TipoReporte {
        Calle_inundada, Paso_cerrado, Refugio_disponible, Otro
    }

    // Enum para el campo 'estado'
    public enum EstadoReporte {
        Activo, Atendido, Verificado
    }
}