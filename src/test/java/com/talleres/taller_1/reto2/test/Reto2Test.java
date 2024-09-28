package com.talleres.taller_1.reto2.tests;

import com.talleres.taller_1.reto2.pages.LoginPage;
import com.talleres.taller_1.reto2.pages.ProductsPage;
import com.talleres.taller_1.reto2.pages.CartPage;
import com.talleres.taller_1.reto2.pages.ShippingInfoPage;
import com.talleres.taller_1.reto2.pages.CheckoutSummaryPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Reto2Test {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static ProductsPage productsPage;
    private static CartPage cartPage;
    private static ShippingInfoPage shippingInfoPage;
    private static CheckoutSummaryPage checkoutSummaryPage;

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // Inicializar las páginas
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        shippingInfoPage = new ShippingInfoPage(driver);
        checkoutSummaryPage = new CheckoutSummaryPage(driver);
    }

    @Test
    public void reto2() {
        // Login
        loginPage.ingresarUsuario("standard_user");
        loginPage.ingresarPassword("secret_sauce");
        loginPage.realizarLogin();

        // Seleccionar productos al azar
        double sumaTotalProductos = productsPage.selectRandomProducts(2);

        // Ir al carrito
        productsPage.goToCart();

        // Proceder al checkout desde el carrito
        cartPage.proceedToCheckout();

        // Llenar formulario de información de envío
        shippingInfoPage.fillShippingInfo("Nombre Prueba", "Apellido Prueba", "05001");

        // Obtener los valores de resumen de la compra
        double subtotal = checkoutSummaryPage.getSubtotal();
        double impuesto = checkoutSummaryPage.getTax();
        double total = checkoutSummaryPage.getTotal();

        // Agregar más detalles de los valores para debug
        System.out.println("Subtotal mostrado en resumen: " + subtotal);
        System.out.println("Impuesto mostrado en resumen: " + impuesto);
        System.out.println("Total mostrado en resumen: " + total);

        // Calcular el total esperado
        double totalEsperado = subtotal + impuesto;
        System.out.println("Total esperado: " + totalEsperado);
        System.out.println("Suma total de productos seleccionados: " + sumaTotalProductos);

        // Aserciones con mayor precisión
        assertEquals(sumaTotalProductos, subtotal, 0.01, "El subtotal no coincide con la suma de los productos.");
        assertEquals(totalEsperado, total, 0.01, "El total calculado no coincide con el total mostrado.");
    }

    @AfterAll
    public static void tearDown() {
        // Cerrar el WebDriver después de todos los tests
        if (driver != null) {
            driver.quit();
        }
    }
}
