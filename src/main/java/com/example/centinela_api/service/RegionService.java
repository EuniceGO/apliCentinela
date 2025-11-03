package com.example.centinela_api.service;

import com.example.centinela_api.modelos.Region;
import com.example.centinela_api.interfaces.IRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private IRegion data;

    public List<Region> findAll() {
        return data.findAll();
    }

    public Optional<Region> findById(Integer id) {
        return data.findById(id);
    }

    public Region save(Region region) {
        return data.save(region);
    }

    public void deleteById(Integer id) {
        data.deleteById(id);
    }

    public Region update(Integer id, Region regionDetails) {
        Optional<Region> existente = data.findById(id);
        if (existente.isPresent()) {
            Region region = existente.get();
            region.setNombre(regionDetails.getNombre());
            region.setDescripcion(regionDetails.getDescripcion());
            region.setLatitud(regionDetails.getLatitud());
            region.setLongitud(regionDetails.getLongitud());
            return data.save(region);
        } else {
            return null;
        }
    }
}
