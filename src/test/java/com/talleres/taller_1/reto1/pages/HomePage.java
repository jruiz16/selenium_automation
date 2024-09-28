package com.talleres.taller_1.reto1.pages;

import com.talleres.taller_1.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//DIV[@class='user-info']//A")
    WebElement signInLink;

    @FindBy(xpath = "//DIV[@class='no-account']//A")
    WebElement createAccountLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToSignInPage() {
        signInLink.click();
    }

    public void goToCreateAccountPage() {
        createAccountLink.click();
    }
}
