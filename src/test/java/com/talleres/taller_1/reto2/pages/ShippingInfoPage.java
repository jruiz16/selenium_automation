package com.talleres.taller_1.reto2.pages;

import com.talleres.taller_1.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingInfoPage extends BasePage {

    @FindBy(xpath = ".//form//input[@data-test='firstName']")
    WebElement firstNameInput;

    @FindBy(xpath = ".//form//input[@data-test='lastName']")
    WebElement lastNameInput;

    @FindBy(xpath = ".//form//input[@data-test='postalCode']")
    WebElement postalCodeInput;

    @FindBy(xpath = ".//form//input[@data-test='continue']")
    WebElement continueButton;

    public ShippingInfoPage(WebDriver driver) {
        super(driver);
    }

    public void fillShippingInfo(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
        continueButton.click();
    }
}
