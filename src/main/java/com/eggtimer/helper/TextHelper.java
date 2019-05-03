package com.eggtimer.helper;

import com.eggtimer.core.BaseEggTimer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class TextHelper extends BaseEggTimer {

    private static boolean present = false;
    private static String text = null;

    private Properties or;
    private WebDriver webDriver;

    public TextHelper(Properties or, WebDriver webDriver) {
        this.or = or;
        this.webDriver = webDriver;
    }


    public String getTextByXpath(String locator) {
        try {
            text = webDriver.findElement(By.xpath(or.getProperty(locator))).getText();
        } catch (Exception e) {
            Assert.fail("Text is not present");
            return "Text not present";
        }
        return text;
    }
}
