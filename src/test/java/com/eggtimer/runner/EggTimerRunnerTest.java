package com.eggtimer.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/Feature",glue="",plugin={"html:target/cucumber-html-report"})
public class EggTimerRunnerTest {

}
