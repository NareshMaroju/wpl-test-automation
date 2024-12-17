package steps;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.WebLoginPage;

public class WebLoginSteps {
    WebLoginPage webLoginPage = new WebLoginPage();

    @When("User enters {string} and {string} on web")
    public void userEntersCredentials(String username, String password) {
        webLoginPage.enterUsername(username);
        webLoginPage.enterPassword(password);
    }

    @Then("User should see the web home screen")
    public void verifyWebHomeScreen() {
        // Web-specific verification
    }
}
