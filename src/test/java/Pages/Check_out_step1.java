package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v143.domsnapshot.model.StringIndex;

public class Check_out_step1 {
    private WebDriver driver ;
    private By first_name = By.id("first-name");
    private By last_name = By.id("last-name");
    private By pin_code = By.id("postal-code");
    private By conti = By.id("continue");
    private By err_msg = By.xpath("//div[@class='error-message-container error']");
    private By cancel = By.id("cancel");
    public Check_out_step1(WebDriver driver){
        this.driver=driver;
    }



    //Functionalities
    public void sendFirstName (String str){
        driver.findElement(first_name).sendKeys(str);
    }
    public void sendLastName(String str){
        driver.findElement(last_name).sendKeys(str);
    }
    public void sendPostalCode (String str ){
        driver.findElement(pin_code).sendKeys(str);
    }


    public void continueBill (String fn){
      sendFirstName(fn);
      driver.findElement(conti).click();
    }
    public void continueBill (String fn ,String ln){
        sendFirstName(fn);
        sendLastName(ln);
        driver.findElement(conti).click();
    }
    public void continueBill (String fn , String ls , String pc){
        sendLastName(ls);
        sendFirstName(fn);
        sendPostalCode(pc);
        driver.findElement(conti).click();
    }

    public String getErrorMsg (){
        return driver.findElement(err_msg).getText();
    }

    public boolean isErrVisible(){
        return driver.findElement(err_msg).isDisplayed();
    }

    public void cancel (){
        driver.findElement(cancel).click();
    }




}
