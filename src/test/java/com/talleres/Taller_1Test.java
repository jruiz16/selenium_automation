package com.talleres;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;


//Hola

public class Taller_1Test {

    @Test
    public void reto1() {

        // ARRANGE
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://teststore.automationtesting.co.uk/index.php");

        // ACT
        WebElement signIn = driver.findElement(By.xpath("//DIV[@class='user-info']//A"));
        signIn.click();
        WebElement noAccount = driver.findElement(By.xpath("//DIV[@class='no-account']//A"));
        noAccount.click();

        // Form

        String firstName = "Juan Prueba";
        String lastName = "Ruiz Prueba";
        String email = "juanprueba4@gmail.com";
        String password = "ahsfjkahsiuhriyw";
        String birthDate = "05/31/1970";

        WebElement socialTitleInput = driver.findElement(By.xpath("//SPAN[@class='custom-radio']//input[@id='field-id_gender-1']"));
        WebElement firstNameInput = driver.findElement(By.xpath("//form[@class='js-customer-form']//input[@id='field-firstname']"));
        WebElement lastNameInput = driver.findElement(By.xpath("//form[@class='js-customer-form']//input[@id='field-lastname']"));
        WebElement emailInput = driver.findElement(By.xpath("//form[@class='js-customer-form']//input[@id='field-email']"));
        WebElement passwordInput = driver.findElement(By.xpath("//form[@class='js-customer-form']//input[@id='field-password']"));
        WebElement birthDateInput = driver.findElement(By.xpath("//form[@class='js-customer-form']//input[@id='field-birthday']"));
        WebElement termsInput = driver.findElement(By.xpath("//form[@class='js-customer-form']//input[@name='psgdpr']"));
        WebElement saveButtonInput = driver.findElement(By.xpath("//form[@class='js-customer-form']//button[@data-link-action='save-customer']"));

        socialTitleInput.click();
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        birthDateInput.sendKeys(birthDate);
        termsInput.click();
        saveButtonInput.click();

        // ASSERT
        WebElement accountName = driver.findElement(By.xpath("//A[@class='account']//span"));
        assertEquals(firstName + " " + lastName, accountName.getText());

    }

    @Test
    public void reto2() {

        // ARRANGE
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        // Login

        WebElement inputUser = driver.findElement(By.id("user-name"));
        WebElement inputPassword = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.id("login-button"));

        inputUser.sendKeys("standard_user");
        inputPassword.sendKeys("secret_sauce");
        buttonLogin.click();

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item']"));

        if (products.size() > 1) {
            Random random = new Random();
            int randomQuantity = random.nextInt(products.size() - 1) + 2;
            System.out.println("Seleccionando " + randomQuantity + " productos al azar.");

            double totalSum = 0;

            for (int i = 0; i < randomQuantity; i++) {
                int randomIndex = random.nextInt(products.size());
                WebElement product = products.get(randomIndex);

                WebElement priceElement = product.findElement(By.xpath(".//div[@class='pricebar']//div[@class='inventory_item_price']"));
                String priceText = priceElement.getText().replace("$", "");
                double price = Double.parseDouble(priceText);

                totalSum += price;

                System.out.println("Producto seleccionado: $" + price);

                WebElement addToCartButton = product.findElement(By.xpath(".//div[@class='pricebar']//button[contains(@class,'btn_inventory')]"));
                addToCartButton.click();

                products.remove(randomIndex);
            }

            System.out.println("La suma total de los productos seleccionados es: $" +
                    totalSum);

            WebElement cartButton = driver.findElement(By.xpath(".//div[@data-test='header-container']//a[@data-test='shopping-cart-link']"));
            cartButton.click();

            WebElement checkoutButton = driver.findElement(By.xpath(".//div[@class='cart_footer']//button[@data-test='checkout']"));
            checkoutButton.click();

            WebElement firstNameInput = driver.findElement(By.xpath(".//form//input[@data-test='firstName']"));
            WebElement lastNameInput = driver.findElement(By.xpath(".//form//input[@data-test='lastName']"));
            WebElement postalCodeInput = driver.findElement(By.xpath(".//form//input[@data-test='postalCode']"));
            WebElement continueButton = driver.findElement(By.xpath(".//form//input[@data-test='continue']"));

            firstNameInput.sendKeys("Nombre Prueba");
            lastNameInput.sendKeys("Apellido Prueba");
            postalCodeInput.sendKeys("05001");
            continueButton.click();

            WebElement subTotalPrice = driver.findElement(By.xpath(".//div[@class='summary_info']//div[@data-test='subtotal-label']"));
            WebElement taxPrice = driver.findElement(By.xpath(".//div[@class='summary_info']//div[@data-test='tax-label']"));
            WebElement totalPrice = driver.findElement(By.xpath(".//div[@class='summary_info']//div[@data-test='total-label']"));

            String textSubTotalPrice = subTotalPrice.getText();
            String textTaxPrice = taxPrice.getText();
            String textTotalPrice = totalPrice.getText();

            String subtotalValue = textSubTotalPrice.replace("Item total: $", "").trim();
            String taxValue = textTaxPrice.replace("Tax: $", "").trim();
            String totalValue = textTotalPrice.replace("Total: $", "").trim();

            double subtotal = Double.parseDouble(subtotalValue);
            double tax = Double.parseDouble(taxValue);
            double total = Double.parseDouble(totalValue);

            System.out.println("Subtotal: " + subtotal);
            System.out.println("Total Sum: " + totalSum);
            System.out.println("Impuesto: " + tax);
            System.out.println("Total esperado: " + (totalSum + tax));
            System.out.println("Total: " + total);

            assertEquals(subtotal, totalSum);
            assertEquals(totalSum + tax, total);

        } else {
            System.out.println("No se encontraron productos suficientes para realizar la prueba.");
        }
    }

    @Test
    public void retoIframe() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationtesting.co.uk/iframes.html");

        // Cambiar al iframe
        WebElement iframe = driver.findElement(By.xpath("//iframe[@src='index.html']"));
        driver.switchTo().frame(iframe);

        // Esperar hasta que el botón de toggle esté interactuable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sideBarToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='sidebar']//a[@class='toggle']")));
        sideBarToggle.click();

        // Hacer clic en el enlace 'Accordion' del menú
        WebElement listText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='sidebar']//ul/li/a[text()='Accordion']")));
        listText.click();

        // Cambiar al contenido principal
        driver.switchTo().defaultContent();

        // Scroll y clic en el toggle grande de la página principal
        WebElement bigSideBarToggle = driver.findElement(By.xpath("//div[@id='sidebar']//a[@class='toggle']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bigSideBarToggle);
        wait.until(ExpectedConditions.elementToBeClickable(bigSideBarToggle)).click();

    }

    @Test
    public void retoAlert() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationtesting.co.uk/popups.html");

        WebElement buttonTrigger = driver.findElement(By.xpath("//div[@class='row']//button[text()='Trigger Alert']"));

        buttonTrigger.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    
    @Test
    public void retoTab() {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationtesting.co.uk/browserTabs.html");

        WebElement openTabButton = driver.findElement(By.xpath("//div[@class='col-4 col-12-medium']//input[@value='Open Tab']"));
        openTabButton.click();

        String originalWindow = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();
        while (allWindows.size() == 1) {
            allWindows = driver.getWindowHandles();
        }

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement searchInput = driver.findElement(By.xpath("//div[@id='searchform']//input[@name='q']"));
        searchInput.sendKeys("Cualquier cosa" + Keys.ENTER);

        driver.quit();
    }

    
}
