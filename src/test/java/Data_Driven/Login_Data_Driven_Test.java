package Data_Driven;

import BaseTest.BaseTest;
import Pages.HomePage;
import Utils.JsonFileReaderUtil;
import com.relevantcodes.extentreports.LogStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_Data_Driven_Test extends BaseTest {
    private By username = By.xpath("//input[@placeholder='Username']");
    private By password = By.xpath("//input[@placeholder='Password']");
    private By button = By.id("login-button");
    private By menu = By.id("react-burger-menu-btn");
    @Test
    public void checkUsersFromFile() {

        JSONArray users =
                JsonFileReaderUtil.returnJsonArrayfrom("Test_Data/login.json");

        test = extent.startTest("Data Driven Testing");
        test.setDescription("Login test using JSON data");

        for (int i = 0; i < users.length(); i++) {

            JSONObject user = users.getJSONObject(i);
            String usernameVal = user.getString("user");
            String passwordVal = user.getString("pass");

            test.log(LogStatus.INFO, "Testing user: " + usernameVal);

            driver.findElement(username).clear();
            driver.findElement(password).clear();

            driver.findElement(username).sendKeys(usernameVal);
            driver.findElement(password).sendKeys(passwordVal);
            driver.findElement(button).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(menu)));

            boolean isLoginSuccess =
                    driver.getCurrentUrl().contains("inventory");

            test.log(
                    isLoginSuccess ? LogStatus.PASS : LogStatus.FAIL,
                    isLoginSuccess ? "Login Successful" : "Login Failed"
            );

            if (isLoginSuccess) {
                HomePage page = new HomePage(driver);
                page.clickMenu();

                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("logout_sidebar_link"))));
                page.Logout();
            }
            else {
                test.log(LogStatus.FAIL,"Test Failed");
            }
        }
    }

}
