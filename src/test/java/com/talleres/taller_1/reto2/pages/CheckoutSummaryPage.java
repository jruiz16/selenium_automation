package com.talleres.taller_1.reto2.pages;

import com.talleres.taller_1.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutSummaryPage extends BasePage {

    @FindBy(xpath = ".//div[@class='summary_info']//div[@data-test='subtotal-label']")
    WebElement subTotalPrice;

    @FindBy(xpath = ".//div[@class='summary_info']//div[@data-test='tax-label']")
    WebElement taxPrice;

    @FindBy(xpath = ".//div[@class='summary_info']//div[@data-test='total-label']")
    WebElement totalPrice;

    public CheckoutSummaryPage(WebDriver driver) {
        super(driver);
    }

    public double getSubtotal() {
        String text = subTotalPrice.getText().replace("Item total: $", "").trim();
        return parsePrice(text);
    }

    public double getTax() {
        String text = taxPrice.getText().replace("Tax: $", "").trim();
        return parsePrice(text);
    }

    public double getTotal() {
        String text = totalPrice.getText().replace("Total: $", "").trim();
        return parsePrice(text);
    }

    // Método auxiliar para convertir los precios en double, manejando errores si ocurre.
    private double parsePrice(String priceText) {
        try {
            return Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing price: " + priceText);
            return 0.0;
        }
    }
}
