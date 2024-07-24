package com.rafaelgalvezg.app.repository;

import com.rafaelgalvezg.app.model.Mascota;
import com.rafaelgalvezg.app.model.Propietario;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MascotaRepositoryImpl implements MascotaRepository{

    private final Map<Integer, Mascota> database = new HashMap<>();

    public MascotaRepositoryImpl() {
        inicializarDatos();
    }

    private void inicializarDatos() {
        guardar(crearMascota("Milo", crearPropietario("John", "Lima", "911111111")));
        guardar(crearMascota("Lassie", crearPropietario("Sarah", "Trujillo", "811111111")));
        guardar(crearMascota("Snoopy", crearPropietario("Robert", "Arequipa", "711111111")));
    }

    private Mascota crearMascota(String nombre, Propietario propietario) {
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setPropietario(propietario);
        return mascota;
    }

    private Propietario crearPropietario(String nombre, String ciudad, String telefono) {
        Propietario propietario = new Propietario();
        propietario.setNombre(nombre);
        propietario.setCiudad(ciudad);
        propietario.setTelefono(telefono);
        return propietario;
    }

    @Override
    public Mascota guardar(Mascota mascota) {
        if (mascota.getId() == null) {
            int newId = database.size() + 1;
            while (database.containsKey(newId)) {
                newId++;
            }
            mascota.setId(newId);
        }
        database.put(mascota.getId(), mascota);
        return mascota;
    }

    @Override
    public Optional<Mascota> findById(Integer id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public void deleteById(Integer id) {
        database.remove(id);
    }
}
