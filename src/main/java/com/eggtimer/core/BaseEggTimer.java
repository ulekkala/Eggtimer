package com.eggtimer.core;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseEggTimer {
    Properties config = null;
    public Properties or = null;
    public WebDriver webDriver = null;
    File file = null;

    public void initializeBrowser() {
        config = loadConfigFile();
        or = loadORFile();
        if (config != null && or != null) {
            if (config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver.exe");
                webDriver = new ChromeDriver();
            } else {
                Assert.fail("Provided browser type is not available in list");
            }
        } else {
            Assert.fail("Either config or or file is null");
        }
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public Properties loadConfigFile() {
        try {
            file = new File(System.getProperty("user.dir") + "//src/main//resources//config//" + "application.properties");
            InputStream inputStream = new FileInputStream(file);
            config = new Properties();
            config.load(inputStream);
        } catch (FileNotFoundException e) {
            Assert.fail("Config file not found");
        } catch (IOException e) {
            Assert.fail("IOException occured");
        }
        return config;
    }

    public Properties loadORFile() {
        try {
            file = new File(System.getProperty("user.dir") + "//src//main//resources//config//" + "or.properties");
            InputStream inputStream = new FileInputStream(file);
            or = new Properties();
            or.load(inputStream);
        } catch (FileNotFoundException e) {
            Assert.fail("Config file not found");
        } catch (IOException e) {
            Assert.fail("IOException occured");
        }
        return or;
    }
}
