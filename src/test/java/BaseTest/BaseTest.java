package BaseTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;


    @BeforeClass
    public void reportSetup(){
      extent = new ExtentReports("C:/Users/parth/Desktop/Demo_Blaze_Testing/Test_Outputs/Test_Report_03.html");

    }
    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito");

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection");

        driver = new ChromeDriver(options);


        driver.get("https://www.saucedemo.com");
    }


    @AfterMethod
    public void finish (){
      driver.quit();
    }
    @AfterClass
    public void finishReport(){
      extent.flush();
    }
}
