package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.MobileLoginPage;
import utils.CSVUtil;

import java.net.MalformedURLException;

public class LoginSteps {
    MobileLoginPage mobileLoginPage = new MobileLoginPage();
    LoginPage loginpage = new LoginPage();

    @When("User enters {string} and {string} on mobile")
    public void userEntersCredentials(String username, String password) {
        mobileLoginPage.enterUsername(username);
        mobileLoginPage.enterPassword(password);
    }

    @Then("User should see the mobile home screen")
    public void verifyHomeScreen() {

        // Mobile-specific verification
    }

    @Given("^the user is on the mobile login page$")
    public void userOnMobileLoginPage() throws MalformedURLException {
        CSVUtil testData = new CSVUtil("src/test/resources/TestData.csv");
        String userName = testData.getValueByKey("userName");
        String password = testData.getValueByKey("password");
        System.out.println("I'm not doing anything here");
        loginpage.login(userName, password);
    }

    @When("^the user enters a valid username$")
    public void userEntersUsername() {
        loginpage.enterUsername();
    }

    @And("^the user enters a valid password$")
    public void userEntersPassword() {
        loginpage.enterPassword();
    }

    @And("^the user taps on the Login button$")
    public void userTapsOnLoginButton() {
        loginpage.clickLoginButton();
    }

    @Then("^the user should be redirected to the account overview page$")
    public void userRedirectedToAccountPage() {
    }


}
