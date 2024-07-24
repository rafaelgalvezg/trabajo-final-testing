package com.rafaelgalvezg.app.repository;

import com.rafaelgalvezg.app.model.Mascota;

import java.util.Optional;

public interface MascotaRepository {
    Mascota guardar(Mascota mascota);
    Optional<Mascota> findById(Integer id);
    void deleteById(Integer id);
}
