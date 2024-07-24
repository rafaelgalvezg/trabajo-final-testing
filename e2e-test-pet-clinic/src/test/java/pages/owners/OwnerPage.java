package pages.owners;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class OwnerPage extends PageObject {

    @FindBy(xpath = "//*[@id=\"main-navbar\"]/ul/li[2]/a")
    WebElementFacade ownerMenuLink;

    @FindBy(xpath = "//*[@id=\"search-owner-form\"]/div[2]/div/button")
    WebElementFacade ownerListButton;

    @FindBy(id = "owners")
    WebElementFacade ownersTitle;

    @Step("Click on the owner menu link")
    public void clickOnOwnerMenuLink() {
        ownerMenuLink.waitUntilClickable().click();
    }

    @Step("Click on the owner list button")
    public void clickOnOwnerListButton() {
        ownerListButton.waitUntilClickable().click();
    }

    @Step("Get Owners header text")
    public String getOwnersHeaderText() {
        return ownersTitle.waitUntilVisible().getText();
    }


    // Add owner
    @FindBy(xpath = "/html/body/div/div/a")
    WebElementFacade addOwnerButtonOption;

    @FindBy(id = "firstName")
    WebElementFacade firstNameField;

    @FindBy(id = "lastName")
    WebElementFacade lastNameField;

    @FindBy(id = "address")
    WebElementFacade addressField;

    @FindBy(id = "city")
    WebElementFacade cityField;

    @FindBy(id = "telephone")
    WebElementFacade telephoneField;

    @FindBy(xpath = "//*[@id=\"add-owner-form\"]/div[2]/div/button")
    WebElementFacade addOwnerButton;

    @FindBy(xpath = "/html/body/div/div/table[1]/tbody/tr[1]/td/strong")
    WebElementFacade ownersFullName;




    @Step
    public void clickAddOwnerButtonOption() {
        addOwnerButtonOption.waitUntilClickable().click();
    }

    @Step
    public void enterFirstName(String firstName) {
        firstNameField.waitUntilVisible().type(firstName);
    }

    @Step
    public void enterLastName(String lastName) {
        lastNameField.waitUntilVisible().type(lastName);
    }

    @Step
    public void enterAddress(String address) {
        addressField.waitUntilVisible().type(address);
    }

    @Step
    public void enterCity(String city) {
        cityField.waitUntilVisible().type(city);
    }

    @Step
    public void enterTelephone(String telephone) {
        telephoneField.waitUntilVisible().type(telephone);
    }

    @Step
    public void enterOwnerData() {
        String firstName = Serenity.sessionVariableCalled("firstName");
        String lastName = Serenity.sessionVariableCalled("lastName");
        String address = Serenity.sessionVariableCalled("address");
        String city = Serenity.sessionVariableCalled("city");
        String telephone = Serenity.sessionVariableCalled("telephone");
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        enterCity(city);
        enterTelephone(telephone);
    }

    @Step
    public void clickAddOwnerButton() {
        addOwnerButton.waitUntilClickable().click();
    }

    @Step
    public String getFullName() {
        return ownersFullName.waitUntilVisible().getText();
    }
}
