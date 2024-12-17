package pages;
import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileLoginPage {
    private AndroidDriver driver;

    public MobileLoginPage() {
        this.driver = (AndroidDriver) BaseTest.getDriver();
    }

    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(By.id("com.app.id.username"));
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("com.app.id.password"));
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.id("com.app.id.loginButton"));
        loginButton.click();
    }
}