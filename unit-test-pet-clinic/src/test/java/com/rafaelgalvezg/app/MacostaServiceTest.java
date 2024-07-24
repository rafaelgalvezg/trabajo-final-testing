package com.rafaelgalvezg.app;

import com.rafaelgalvezg.app.model.Mascota;
import com.rafaelgalvezg.app.model.Propietario;
import com.rafaelgalvezg.app.repository.MascotaRepository;
import com.rafaelgalvezg.app.repository.MascotaRepositoryImpl;
import com.rafaelgalvezg.app.service.ExternalService;
import com.rafaelgalvezg.app.service.MascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MacostaServiceTest {

    @InjectMocks
    MascotaService mascotaService;
    @Spy
    MascotaRepository mascotaRepository = new MascotaRepositoryImpl();
    @Mock
    ExternalService externalService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRestistrarMascotaCorectamente() {
        // Arrange
        Propietario propietario = new Propietario("Rafael", "Guatemala", "23122132");
        Mascota mascota = new Mascota();
        mascota.setNombre("Wine");
        mascota.setPropietario(propietario);

        when(externalService.validarVacunas(any(Mascota.class))).thenReturn(true);
        when(externalService.verificarRegistroMunicipal(any(Mascota.class))).thenReturn(true);
        when(mascotaRepository.findById(any())).thenReturn(Optional.empty());

        // Act
        Mascota mascotaRegistrada = mascotaService.registrarMascota(mascota);

        // Assert

        // JUnit
        assertNotNull(mascotaRegistrada, "La mascota registrada no puede ser nula");
        assertSame(mascota, mascotaRegistrada, "La mascota registrada no es la misma que la enviada");
        assertTrue(mascota.getId() > 0, "El id de la mascota debe ser un entero positivo");
        assertEquals("Wine", mascotaRegistrada.getNombre(), "El nombre de la mascota no coincide");
        assertEquals("Rafael", mascotaRegistrada.getPropietario().getNombre(), "El nombre del propietario no coincide");
        assertEquals("Guatemala", mascotaRegistrada.getPropietario().getCiudad(), "La ciudad del propietario no coincide");
        assertEquals("23122132", mascotaRegistrada.getPropietario().getTelefono(), "El teléfono del propietario no coincide");

        // Hamcrest
        assertThat("La mascota registrada no puede ser nula", mascotaRegistrada, is(notNullValue()));
        assertThat("El nombre de la mascota no coincide", mascotaRegistrada, hasProperty("nombre", is("Wine")));
        assertThat("El id de la mascota debe ser un entero positivo", mascotaRegistrada.getId(), is(greaterThanOrEqualTo(0)));
        assertThat("La nombre del propietario no coincide", mascotaRegistrada.getPropietario(), hasProperty("nombre", is("Rafael")));
        assertThat("La ciudad del propietario no coincide", mascotaRegistrada.getPropietario(), hasProperty("ciudad", is("Guatemala")));
        assertThat("El teléfono del propietario no coincide", mascotaRegistrada.getPropietario(), hasProperty("telefono", is("23122132")));
    }

    @Test
    @DisplayName("Registrar mascota con nombre null")
    void testRegistrarMascotaConNombreNull() {
        // Arrange
        Mascota mascota = new Mascota();
        mascota.setNombre(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mascotaService.registrarMascota(mascota);
        });
        assertEquals("El nombre de la mascota no puede estar vacío.", exception.getMessage(), "El mensaje de error no coincide");
    }

    @Test
    @DisplayName("Registrar mascota con nombre vacío")
    void testRegistrarMascotaConNombreVacio() {
        // Arrange
        Mascota mascota = new Mascota();
        mascota.setNombre("");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mascotaService.registrarMascota(mascota);
        });
        assertEquals("El nombre de la mascota no puede estar vacío.", exception.getMessage(), "El mensaje de error no coincide");
    }

    @Test
    @DisplayName("Registrar mascota sin propietario")
    void testRegistrarMascotaSinPropietario() {
        // Arrange
        Mascota mascota = new Mascota();
        mascota.setNombre("Wine");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mascotaService.registrarMascota(mascota);
        });
        assertEquals("La mascota debe tener un propietario.", exception.getMessage(), "El mensaje de error no coincide");
    }

    @ParameterizedTest(name = "Registrar mascota sin teléfono propietario [{arguments}]")
    @NullAndEmptySource
    void testRegistrarMascotaSinTelefonoPropietario( String telefono ) {
        // Arrange
        Propietario propietario = new Propietario("Rafael", "Guatemala", telefono);
        Mascota mascota = new Mascota();
        mascota.setNombre("Wine");
        mascota.setPropietario(propietario);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mascotaService.registrarMascota(mascota);
        });
        assertEquals("El propietario debe tener un teléfono registrado.", exception.getMessage(), "El mensaje de error no coincide");
    }

    @Test
    @DisplayName("Registrar mascota sin vacunas al día")
    void testRegistrarMascotaSinVacunasAlDia() {
        // Arrange
        Propietario propietario = new Propietario("Rafael", "Guatemala", "23122132");
        Mascota mascota = new Mascota();
        mascota.setNombre("Wine");
        mascota.setPropietario(propietario);

        when(externalService.validarVacunas(any(Mascota.class))).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            mascotaService.registrarMascota(mascota);
        });
        assertEquals("La mascota no tiene todas las vacunas al día.", exception.getMessage(), "El mensaje de error no coincide");
    }

    @Test
    @DisplayName("Registrar mascota sin registro municipal")
    void testRegistrarMascotaSinRegistroMunicipal() {
        // Arrange
        Propietario propietario = new Propietario("Rafael", "Guatemala", "23122132");
        Mascota mascota = new Mascota();
        mascota.setNombre("Wine");
        mascota.setPropietario(propietario);

        when(externalService.validarVacunas(any(Mascota.class))).thenReturn(true);
        when(externalService.verificarRegistroMunicipal(any(Mascota.class))).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            mascotaService.registrarMascota(mascota);
        });
        assertEquals("La mascota no está registrada en el municipio.", exception.getMessage(), "El mensaje de error no coincide");
    }

    @Test
    @DisplayName("Registrar mascota ya registrada")
    void testRegistrarMascotaYaRegistrada() {
        // Arrange
        Propietario propietario = new Propietario("Rafael", "Guatemala", "23122132");
        Mascota mascota = new Mascota();
        mascota.setNombre("Wine");
        mascota.setPropietario(propietario);

        when(externalService.validarVacunas(any(Mascota.class))).thenReturn(true);
        when(externalService.verificarRegistroMunicipal(any(Mascota.class))).thenReturn(true);
        when(mascotaRepository.findById(any())).thenReturn(Optional.of(mascota));

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            mascotaService.registrarMascota(mascota);
        });
        assertEquals("Esta mascota ya está registrada.", exception.getMessage(), "El mensaje de error no coincide");
    }

    @Test
    @DisplayName("Buscar mascota por id")
    void testBuscarMascotaPorId() {
        // Arrange
        Mascota mascota = new Mascota();
        when(mascotaRepository.findById(1)).thenReturn(Optional.of(mascota));

        // Act
        Optional<Mascota> mascotaEncontrada = mascotaService.buscarMascotaPorId(1);

        // Assert
        assertTrue(mascotaEncontrada.isPresent(), "La mascota no fue encontrada");
        assertSame(mascota, mascotaEncontrada.get(), "La mascota encontrada no es la misma que la esperada");
    }

    @Test
    @DisplayName("Buscar mascota por id no encontrada")
    void testBuscarMascotaPorIdNoEncontrada() {
        // Arrange
        when(mascotaRepository.findById(any())).thenReturn(Optional.empty());

        // Act
        Optional<Mascota> mascotaEncontrada = mascotaService.buscarMascotaPorId(999);

        // Assert
        assertFalse(mascotaEncontrada.isPresent(), "La mascota fue encontrada");
    }


    @Test
    @DisplayName("Eliminar mascota por id")
    void testEliminarMascotaPorId() {
        // Arrange
        Mascota mascota = new Mascota();
        when(mascotaRepository.findById(1)).thenReturn(Optional.of(mascota));

        // Act
        mascotaService.eliminarMascotaPorId(1);

        // Assert
        when(mascotaRepository.findById(1)).thenReturn(Optional.empty());
        Optional<Mascota> mascotaEliminada = mascotaService.buscarMascotaPorId(1);
        assertFalse(mascotaEliminada.isPresent(), "La mascota no fue eliminada");
    }

    @Test
    @DisplayName("Eliminar mascota por id no encontrada")
    void testEliminarMascotaPorIdNoEncontrada() {
        // Arrange
        when(mascotaRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mascotaService.eliminarMascotaPorId(999);
        });
        assertEquals("No se puede eliminar: No se encontró mascota con el ID proporcionado.", exception.getMessage(), "El mensaje de error no coincide");
    }

}
