package SauceDemo.stepDef;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.util.concurrent.*;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("Login Page Swag Labs")
    public void login_page_swag_labs(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // timeout
        driver.manage().window().maximize();
        driver.get(baseUrl);

        // Assertion aja
        String loginPageAssert = driver.findElement(By.xpath("//div[contains(text(),'Swag Labs')]")).getText();
        Assert.assertEquals(loginPageAssert,"Swag Labs");
    }

    @When("input Username")
    public void inputUsername() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("input Password")
    public void inputPassword() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("click Login button")
    public void clickLoginButton() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("user is on Product page")
    public void userIsOnProductPage() {
        driver.findElement(By.xpath("//span[contains(text(),'Products')]"));
        String username = driver.findElement(By.xpath("//div[contains(text(),'Swag Labs')]/preceding-sibling::dt")).getText();
        Assert.assertEquals(username,"Swag Labs");
    }

    @And("input invalid Password")
    public void inputInvalidPassword() {
        driver.findElement(By.id("password")).sendKeys("saosrahasia");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        String ErrorLogin = driver.findElement(By.xpath("//div[@role='heading']")).getText();
        Assert.assertEquals(ErrorLogin,"Epic sadface: Username and password do not match any user in this service");
    }
}
