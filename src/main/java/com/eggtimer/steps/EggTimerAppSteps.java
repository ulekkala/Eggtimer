package com.eggtimer.steps;

import com.eggtimer.core.BaseEggTimer;
import com.eggtimer.helper.ButtonHelper;
import com.eggtimer.helper.TextBoxHelper;
import com.eggtimer.helper.TextHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class EggTimerAppSteps extends BaseEggTimer {

    private Set<String> countSeconds = null;
    private static int timer = 0;
    private TextBoxHelper textBox;
    private ButtonHelper button;
    private TextHelper text;

    public EggTimerAppSteps(){
        super();
        initializeBrowser();
        textBox = new TextBoxHelper(or, webDriver);
        button = new ButtonHelper(or, webDriver);
        text = new TextHelper(or, webDriver);
    }

    @Given("^Load the portal url '(.*)'$")
    public void loadApplication(final String eggTimer) {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(loadConfigFile().getProperty(eggTimer));
    }

    @When("^Pass the time '(.*)' seconds and click on GO!$")
    public void provideTheTimer(String seconds) throws Throwable {
        timer = Integer.valueOf(seconds);
        webDriver.manage().window().maximize();
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(webDriver.findElement(By.xpath("//img[@alt=\"Welcome to e.ggtimer.com\"]")).isDisplayed());
        Assert.assertTrue(textBox.isTextBoxPresentByName("WEdit_Start_Timer"));
        textBox.clearTextByName("WEdit_Start_Timer");
        textBox.typeTextByName("WEdit_Start_Timer", seconds);

    }

    @When("^Count the timer while decreasing the seconds$")
    public void countTheSeconds() {
        int seconds = 0;
        try {
            button.clickButtonById("Btn_timer_go");
            // webDriver.navigate().refresh();
            // Write code here that turns the phrase above into concrete actions
            countSeconds = new LinkedHashSet<String>();
            do {
                if (!text.getTextByXpath("Txt_Sec_message").equalsIgnoreCase("Time Expired!")){
                    countSeconds.add(text.getTextByXpath("Txt_Sec_message"));
                }
                if(text.getTextByXpath("Txt_Sec_message").equalsIgnoreCase("1 second")){
                    int size = countSeconds.size();
                    for(int i = timer; i > size; i--){
                        countSeconds.add(+i+" Seconds");
                    }

                    Thread.sleep(2000);
                    webDriver.switchTo().alert().accept();
                }

            }while (!text.getTextByXpath("Txt_Sec_message").equalsIgnoreCase("Time Expired!"));
            System.out.println("Actual Time: "+timer+ " \nDecremented Timer : "+countSeconds);
            Assert.assertEquals("Count equals to provided seconds", timer, countSeconds.size());

        } catch (Exception e) {
            Assert.fail("Time interrupt exception");
        }
    }

    @Then("^Display the message after time expiry$")
    public void verifyTimeExpiryMessage() {
        Assert.assertEquals("Time Expired! message displayed successfully", "Time Expired!", text.getTextByXpath("Txt_Sec_message"));
        webDriver.quit();
    }
}