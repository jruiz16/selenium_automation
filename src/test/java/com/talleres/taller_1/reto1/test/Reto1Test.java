package com.talleres.taller_1.reto1.tests;

import com.talleres.taller_1.reto1.pages.HomePage;
import com.talleres.taller_1.reto1.pages.RegisterPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Reto1Test {

    private static WebDriver driver;
    private static HomePage homePage;
    private static RegisterPage registerPage;

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://teststore.automationtesting.co.uk/index.php");

        // Inicializar las páginas
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void reto1() {
        // Ir a la página de inicio de sesión y luego a la página de creación de cuenta
        homePage.goToSignInPage();
        homePage.goToCreateAccountPage();

        // Generar un número aleatorio de 4 dígitos para el correo
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;  // Generar un número entre 1000 y 9999

        // Llenar el formulario de registro
        String firstName = "Juan Prueba";
        String lastName = "Ruiz Prueba";
        String email = "juanprueba" + randomNumber + "@gmail.com";  // Correo con número aleatorio
        String password = "ahsfjkahsiuhriyw";
        String birthDate = "05/31/1970";

        registerPage.fillForm(firstName, lastName, email, password, birthDate);

        // Validar que el nombre del usuario registrado es el correcto
        String actualName = registerPage.getAccountName();
        assertEquals(firstName + " " + lastName, actualName);
    }

    @AfterAll
    public static void tearDown() {
        // Cerrar el WebDriver después de todos los tests
        if (driver != null) {
            driver.quit();
        }
    }
}
