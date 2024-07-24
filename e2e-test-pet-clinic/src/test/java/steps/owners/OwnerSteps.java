package steps.owners;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import pages.owners.OwnerPage;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OwnerSteps {

    @Steps
    OwnerPage ownerPage;

    @Given("el cliente abre el navegador")
    public void elClienteAbreElNavegador() {
        ownerPage.open();
    }

    @When("el cliente navega al menú encontrar propietarios")
    public void elClienteNavegaAlMenuEncontrarPropietarios() {
        ownerPage.clickOnOwnerMenuLink();
    }

    @And("el cliente selecciona la opción encontrar propietarios")
    public void elClienteSeleccionaLaOpciónEncontrarPropietarios() {
        ownerPage.clickOnOwnerListButton();
    }

    @Then("la página debe mostrar una lista de propietarios válida")
    public void laPaginaDebeMostrarUnaListaDePropietariosValida() {
        String headerText = ownerPage.getOwnersHeaderText();
        assertEquals(headerText, "Owners");
    }

    //Add owner
    @Given("el cliente tiene los siguientes datos del propietario:")
    public void elClienteTieneLosSiguientesDatosDelPropietario(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                String header = entry.getKey();
                String cell = entry.getValue();
                Serenity.setSessionVariable(header).to(cell);
            }
        }
    }

    @When("el cliente selecciona la opción agregar nuevo")
    public void elClienteSeleccionaLaOpcionAgregarNuevo() {
        ownerPage.clickAddOwnerButtonOption();
    }

    @And("el cliente ingresa los datos del propietario")
    public void elClienteIngresaLosDatosDelPropietario() {
        ownerPage.enterOwnerData();
    }

    @And("el cliente guarda el propietario")
    public void elClienteGuardaElPropietario() {
        ownerPage.clickAddOwnerButton();
    }

    @Then("la página debe mostrar la información del propietario registrado")
    public void laPaginaDebeMostrarLaInformacionDelPropietarioRegistrado() {
        String firstName = Serenity.sessionVariableCalled("firstName");
        String lastName = Serenity.sessionVariableCalled("lastName");
        String fullName = firstName + " " + lastName;
        assertEquals(fullName, ownerPage.getFullName());
    }

}
