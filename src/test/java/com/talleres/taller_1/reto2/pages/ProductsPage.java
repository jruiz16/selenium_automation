package com.talleres.taller_1.reto2.pages;

import com.talleres.taller_1.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//div[@class='inventory_item']")
    List<WebElement> products;

    @FindBy(xpath = ".//div[@data-test='header-container']//a[@data-test='shopping-cart-link']")
    WebElement cartButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public double selectRandomProducts(int minQuantity) {
        Random random = new Random();
        double totalSum = 0;

        // Hacer una copia de la lista de productos para evitar manipular directamente la lista original
        List<WebElement> availableProducts = products;

        if (availableProducts.size() > 1) {
            int randomQuantity = random.nextInt(availableProducts.size() - minQuantity) + minQuantity;
            System.out.println("Seleccionando " + randomQuantity + " productos al azar.");

            for (int i = 0; i < randomQuantity; i++) {
                int randomIndex = random.nextInt(availableProducts.size());  // Selección aleatoria
                WebElement product = availableProducts.get(randomIndex);

                // Obtener el precio del producto
                WebElement priceElement = product.findElement(By.xpath(".//div[@class='pricebar']//div[@class='inventory_item_price']"));
                double price = Double.parseDouble(priceElement.getText().replace("$", ""));

                totalSum += price;
                System.out.println("Producto seleccionado: $" + price);

                // Añadir el producto al carrito
                WebElement addToCartButton = product.findElement(By.xpath(".//div[@class='pricebar']//button[contains(@class,'btn_inventory')]"));
                addToCartButton.click();

                // Eliminar el producto seleccionado de la lista para evitar seleccionarlo nuevamente
                availableProducts.remove(randomIndex);
            }
        } else {
            System.out.println("No se encontraron productos suficientes para realizar la prueba.");
        }

        return totalSum;
    }

    public void goToCart() {
        cartButton.click();
    }
}
