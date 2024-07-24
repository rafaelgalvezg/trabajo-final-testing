package com.rafaelgalvezg.app.service;

import com.rafaelgalvezg.app.model.Mascota;

public interface ExternalService {

    boolean validarVacunas(Mascota mascota);
    boolean verificarRegistroMunicipal(Mascota mascota);
}
