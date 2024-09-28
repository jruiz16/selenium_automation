package com.talleres.taller_1.reto1.pages;

import com.talleres.taller_1.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(id = "field-id_gender-1")
    WebElement socialTitleInput;

    @FindBy(id = "field-firstname")
    WebElement firstNameInput;

    @FindBy(id = "field-lastname")
    WebElement lastNameInput;

    @FindBy(id = "field-email")
    WebElement emailInput;

    @FindBy(id = "field-password")
    WebElement passwordInput;

    @FindBy(id = "field-birthday")
    WebElement birthDateInput;

    @FindBy(name = "psgdpr")
    WebElement termsInput;

    @FindBy(xpath = "//button[@data-link-action='save-customer']")
    WebElement saveButtonInput;

    @FindBy(xpath = "//A[@class='account']//span")
    WebElement accountName;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String firstName, String lastName, String email, String password, String birthDate) {
        socialTitleInput.click();
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        birthDateInput.sendKeys(birthDate);
        termsInput.click();
        saveButtonInput.click();
    }

    public String getAccountName() {
        return accountName.getText();
    }
}
