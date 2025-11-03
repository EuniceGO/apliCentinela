package com.example.centinela_api.modelos;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "Regiones")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId;

    private String nombre;
    private String descripcion;

    // Usamos Double para DECIMAL(10,6)
    private Double latitud;
    private Double longitud;
}