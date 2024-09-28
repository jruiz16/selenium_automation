package com.talleres.taller_1.reto2.pages;

import com.talleres.taller_1.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    WebElement inputUsername;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(id = "login-button")
    WebElement buttonLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void ingresarUsuario(String username) {
        inputUsername.sendKeys(username);
    }

    public void ingresarPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void realizarLogin() {
        buttonLogin.click();
    }
}
