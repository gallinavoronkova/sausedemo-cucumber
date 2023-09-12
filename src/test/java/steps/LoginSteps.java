package steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginSteps {
    ChromeDriver driver;

    @Given("^I open the page Login$")
    public void iOpenThePageLogin() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver",
                "/Users/halynavoronkova/Downloads/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("^I fill input user name \"([^\"]*)\"$")
    public void iFillInputUserName(String usernameValue) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.usernameInputField.sendKeys(usernameValue);
    }

    @Then("^I fill input password \"([^\"]*)\"$")
    public void iFillInputPassword(String passwordValue) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.passwordInputField.sendKeys(passwordValue);
    }

    @And("^I click on button Login$")
    public void iClickOnButtonLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginButton.click();
    }

    @Then("^The page Inventory is displayed$")
    public void thePageInventoryIsDisplayed() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.inventoryList.isDisplayed());
    }

    @Then("^error message is displayed$")
    public void errorMessageIsDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.errorMessage.isDisplayed());
    }

    @And("^error message about invalid creds has correct text$")
    public void errorMessageAboutInvalidCredsHasCorrectText() {
        LoginPage loginPage = new LoginPage(driver);
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.errorMessage.getText());
    }

    @And("^error message with text \"([^\"]*)\" is displayed$")
    public void errorMessageWithTextIsDisplayed(String expectedText) {
        LoginPage loginPage = new LoginPage(driver);
        assertEquals(expectedText, loginPage.errorMessage.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
