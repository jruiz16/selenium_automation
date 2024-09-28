package com.talleres.taller_1.reto2.pages;

import com.talleres.taller_1.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(xpath = "//button[@data-test='checkout']")
    WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }
}
