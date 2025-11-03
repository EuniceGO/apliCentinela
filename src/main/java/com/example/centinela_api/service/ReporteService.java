package com.example.centinela_api.service;

import com.example.centinela_api.modelos.Reporte;
import com.example.centinela_api.interfaces.IReporte;
import com.example.centinela_api.modelos.Usuario;
import com.example.centinela_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private IReporte data;

    @Autowired
    private UsuarioService usuarioService;

    public List<Reporte> findAll() {
        return data.findAll();
    }

    public Optional<Reporte> findById(Integer id) {
        return data.findById(id);
    }

    public Reporte save(Reporte reporte) {
        // Ensure usuario is a managed entity (avoid transient reference)
        if (reporte.getUsuario() == null || reporte.getUsuario().getUsuarioId() == null) {
            throw new IllegalArgumentException("El reporte debe incluir usuario con usuarioId");
        }

        Integer uid = reporte.getUsuario().getUsuarioId();
        Usuario u = usuarioService.findById(uid).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + uid));
        reporte.setUsuario(u);

        return data.save(reporte);
    }

    public void deleteById(Integer id) {
        data.deleteById(id);
    }

    public Reporte update(Integer id, Reporte reporteDetails) {
        Optional<Reporte> existente = data.findById(id);
        if (existente.isPresent()) {
            Reporte reporte = existente.get();
            reporte.setDescripcion(reporteDetails.getDescripcion());
            reporte.setLatitud(reporteDetails.getLatitud());
            reporte.setLongitud(reporteDetails.getLongitud());
            reporte.setFoto(reporteDetails.getFoto());
            reporte.setTipo(reporteDetails.getTipo());
            reporte.setEstado(reporteDetails.getEstado());
            // Resolve usuario to managed entity when updating
            if (reporteDetails.getUsuario() != null && reporteDetails.getUsuario().getUsuarioId() != null) {
                Integer uid = reporteDetails.getUsuario().getUsuarioId();
                Usuario u = usuarioService.findById(uid).orElse(null);
                reporte.setUsuario(u);
            }
            return data.save(reporte);
        } else {
            return null;
        }
    }
}
