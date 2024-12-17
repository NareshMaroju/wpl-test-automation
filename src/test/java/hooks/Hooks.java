package hooks;

import base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        // Initialize driver (mobile or web) before each scenario
        logger.info("Initializing driver before the scenario...");
        BaseTest.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        // Capture screenshot if scenario fails
        if (scenario.isFailed()) {
            captureScreenshot(scenario);
        }

        // Quit the driver after each scenario
        BaseTest.quitDriver();
        logger.info("Driver quit after the scenario.");
    }

    // Method to capture screenshot
    private void captureScreenshot(Scenario scenario) {
        WebDriver driver = null;
        // Check if the driver is a mobile driver (Appium) or a web driver (Selenium)
        if (BaseTest.getDriver() instanceof AppiumDriver) {
            driver = (AppiumDriver) BaseTest.getDriver();
        } else if (BaseTest.getDriver() instanceof WebDriver) {
            driver = (WebDriver) BaseTest.getDriver();
        }

        if (driver != null) {
            // Capture screenshot and store it
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                // Save the screenshot with a unique name (timestamp + scenario name)
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotPath = "target/screenshots/" + scenario.getName() + "_" + timestamp + ".png";
                Files.createDirectories(Paths.get("target/screenshots"));
                Files.copy(screenshot.toPath(), Paths.get(screenshotPath));

                logger.info("Screenshot taken for failed scenario: " + scenario.getName());
                // Attach the screenshot to the report
                scenario.attach(Files.readAllBytes(Paths.get(screenshotPath)), "image/png", scenario.getName());
            } catch (IOException e) {
                logger.error("Failed to capture screenshot for scenario: " + scenario.getName(), e);
            }
        }
    }
}
