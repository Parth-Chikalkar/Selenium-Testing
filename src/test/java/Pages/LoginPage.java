package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By username = By.xpath("//input[@placeholder='Username']");
    private By password = By.xpath("//input[@placeholder='Password']");
    private By loginbutton = By.id("login-button");
    private By errMessage =  By.xpath("//h3[@data-test='error']");
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // Operations
    public void setUserName (String name){
        driver.findElement(username).sendKeys(name);
    }

    public void setPassword (String pass){
        driver.findElement(password).sendKeys(pass);
    }
    public void clickLogin (){
        driver.findElement(loginbutton).click();
    }
    public void Login (String username , String pass){
        setUserName(username);
        setPassword(pass);
        clickLogin();
    }

    public void Login (String username){
        setUserName(username);
        clickLogin();
    }


    public String getErrMSG (){
        return driver.findElement(errMessage).getText();
    }
}
