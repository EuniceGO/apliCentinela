package com.example.centinela_api.interfaces;

import com.example.centinela_api.modelos.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegion extends JpaRepository<Region, Integer> {

}
