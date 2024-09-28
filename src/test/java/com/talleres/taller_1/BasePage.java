package com.talleres.taller_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected final WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void openPage(String url) {
        webDriver.get(url);
    }

    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
