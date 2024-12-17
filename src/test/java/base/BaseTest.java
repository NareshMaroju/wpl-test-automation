package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import utils.CSVUtil;

public class BaseTest {
    private static AppiumDriver mobileDriver;
    private static WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    // Enum to define test type
    public enum TestType {
        MOBILE,
        WEB
    }

    static CSVUtil testData = new CSVUtil("src/test/resources/TestData.csv");
    // Choose test type (can be set dynamically per test)
    private static final TestType testType = TestType.valueOf(testData.getValueByKey("channel")); // Default is mobile; modify as needed

    // Initialize the driver based on the test type
    public static Object getDriver() {
        if (testType == TestType.MOBILE && mobileDriver == null) {
            initializeMobileDriver();
        } else if (testType == TestType.WEB && webDriver == null) {
            initializeWebDriver();
        }
        return testType == TestType.MOBILE ? mobileDriver : webDriver;
    }

    private static void initializeMobileDriver() {
        try {
            logger.info("Reading device capabilities...");
            CSVUtil capabilities = new CSVUtil("src/test/resources/Capabilities.csv");

            logger.info("Initializing Appium Mobile Driver...");
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(capabilities.getValueByKey("deviceName"));
            options.setUdid(capabilities.getValueByKey("udid"));
            options.setPlatformName(capabilities.getValueByKey("platformName"));
            options.setPlatformVersion(capabilities.getValueByKey("platformVersion"));
            options.setAutomationName(capabilities.getValueByKey("automationName"));
            options.setAppPackage(capabilities.getValueByKey("appPackage"));
            options.setAppActivity(capabilities.getValueByKey("appActivity"));
            options.setCapability("ignoreHiddenApiPolicyError", true);

            mobileDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);

            logger.info("Appium Mobile Driver initialized.");
        } catch (Exception e) {
            logger.error("Failed to initialize Appium Mobile Driver", e);
        }
    }

    private static void initializeWebDriver() {
        try {
            logger.info("Initializing Selenium WebDriver...");
            System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver"); // Set path to chromedriver
            webDriver = new ChromeDriver();
            logger.info("Selenium WebDriver initialized.");
        } catch (Exception e) {
            logger.error("Failed to initialize Selenium WebDriver", e);
        }
    }

    // Quit drivers
    public static void quitDriver() {
        if (testType == TestType.MOBILE && mobileDriver != null) {
            mobileDriver.quit();
            logger.info("Appium Mobile Driver quit.");
        } else if (testType == TestType.WEB && webDriver != null) {
            webDriver.quit();
            logger.info("Selenium WebDriver quit.");
        }
    }
}
