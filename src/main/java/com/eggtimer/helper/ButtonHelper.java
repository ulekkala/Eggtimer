package com.eggtimer.helper;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ButtonHelper {

    private Properties or;
    private WebDriver webDriver;

    public ButtonHelper(Properties or, WebDriver webDriver) {
        this.or = or;
        this.webDriver = webDriver;
    }

    public void clickButtonById(String locator) {
        try {
            webDriver.findElement(By.id(or.getProperty(locator))).click();
        } catch (Exception e) {
            Assert.fail("Button is not present");
        }
    }
}
