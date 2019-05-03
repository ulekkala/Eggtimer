package com.eggtimer.helper;

import com.eggtimer.core.BaseEggTimer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class TextBoxHelper extends BaseEggTimer {

    private Properties or;
    private WebDriver webDriver;

    public TextBoxHelper(Properties or, WebDriver webDriver) {
        this.or = or;
        this.webDriver = webDriver;
    }

    public boolean isTextBoxPresentByName(String locator) {
        try {
            webDriver.findElement(By.name(or.getProperty(locator))).isDisplayed();
            return true;
        } catch (ElementNotVisibleException e) {
            Assert.fail("Text box is not present");
        }
        return false;
    }

    public void clearTextByName(String locator) {
        try {
            webDriver.findElement(By.name(or.getProperty(locator))).clear();
        } catch (Exception e) {
            Assert.fail("Text box is not present");
        }
    }

    public void typeTextByName(String locator, String value) {
        try {
            webDriver.findElement(By.name(or.getProperty(locator))).sendKeys(value);
        } catch (Exception e) {
            Assert.fail("Text box is not present");
        }
    }
}
