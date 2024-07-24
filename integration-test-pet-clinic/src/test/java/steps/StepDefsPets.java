package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.core.Serenity.*;
import static net.serenitybdd.rest.SerenityRest.*;

public class StepDefsPets {


    @Given("El cliente configura la URI base de la API")
    public void elClienteConfiguraLaURIBaseDeLaAPI() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        RestAssured.baseURI = EnvironmentSpecificConfiguration.from(environmentVariables).getOptionalProperty("environments.default.base.url").orElse("environments.dev.base.url");
    }

    @And("El cliente configura el header Content-Type con {string}")
    public void elClienteConfiguraElHeaderContentTypeCon(String APPLICATION_JSON) {
       rest().given().contentType(APPLICATION_JSON);
    }

    // Scenario: agregar mascota
    @Given("el cliente tiene los datos de una nueva mascota")
    public void elClienteTieneLosDatosDeUnaNuevaMascota(String body) {
        setSessionVariable("pet").to(body);
    }

    @When("el usuario envia la peticion POST a {string} con los datos de la mascota")
    public void elUsuarioEnviaLaPeticionPOSTAConLosDatosDeLaMascota(String path) {
        String pet = sessionVariableCalled("pet");
        rest().given().contentType(ContentType.JSON)
                .body(pet)
                .post(path).andReturn()
                .then()
                .log().all();
    }
    @Then("el servidor debe retornar un status {int}")
    public void elServidorDebeRetornarUnStatus(Integer statusCode) {
        rest().then().statusCode(statusCode).then().log().all();
    }


    // Scenario: consultar mascota por id

    @Given("el cliente tiene el id {int} de una mascota")
    public void elClienteTieneElIdDeUnaMascota(Integer idPet) {
        setSessionVariable("idPet").to(idPet);
    }
    @When("el cliente envia la peticion GET a {string}")
    public void elClienteEnviaLaPeticionGETA(String path) {
        Integer idPet = sessionVariableCalled("idPet");
       rest().get(path.concat(idPet.toString())).andReturn();
    }

    // Scenario: actualizar mascota

    @Given("el cliente tiene los detalles de un tipo de mascota actualizado")
    public void elClienteTieneLosDetallesDeUnTipoDeMascotaActualizado(String body) {
        setSessionVariable("pet").to(body);
    }

    @When("el cliente realiza una peticion PUT a {string} con los datos actualizados")
    public void elClienteRealizaUnaPeticionPUTAConLosDatosActualizados(String path) {
        String pet = sessionVariableCalled("pet");
        rest().given().contentType(ContentType.JSON)
                .body(pet)
                .put(path).andReturn()
                .then()
                .log().all();
    }

    @When("el cliente realiza una peticion DELETE a {string} con id tipo de mascota eliminado {int}")
    public void elClienteRealizaUnaPeticionDELETEAConIdTipoDeMascotaEliminado(String path, Integer id) {
        given().contentType(ContentType.JSON).pathParam("id", id)
                .delete(path)
                .andReturn();
    }

}
