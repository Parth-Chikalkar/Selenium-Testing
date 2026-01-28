package Tests;

import BaseTest.BaseTest;
import Pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

   @Test
    public void validLoginTest(){
       test = extent.startTest("Valid Login");
       LoginPage page = new LoginPage(driver);
       test.log(LogStatus.INFO,"Entering Credentials provided by Authorities");
       page.Login("standard_user","secret_sauce");
       test.log(LogStatus.PASS,"Entered Details Succesfully !");
       Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
       test.log(LogStatus.PASS,"Test Passed Sucesfully");
   }

   @Test
   public void validateWrongUser(){
   test = extent.startTest("Invalid Login");
   LoginPage page = new LoginPage(driver);
   test.log(LogStatus.INFO,"Testing Invalid Login Credentials");
   page.Login("locked_out_user","secret_sauce");
   Assert.assertTrue(page.getErrMSG().contains("locked out"));
   test.log(LogStatus.PASS,"Test Completed Sucessfully !");
   }
   @Test
   public void notUsername (){
      test = extent.startTest("Testing For Empty Username");
      LoginPage page = new LoginPage(driver);
      test.log(LogStatus.INFO,"Setting Empty Username");
      page.Login("");
      Assert.assertTrue(page.getErrMSG().contains("Username is required"));
      test.log(page.getErrMSG().contains("Username is required") ? LogStatus.PASS :LogStatus.FAIL, page.getErrMSG().contains("Username is required") ? "Test Passed" :"Test Failed");

   }
   @Test
   public void missingPassword (){
      test = extent.startTest("Testing For Empty Password");
      LoginPage page = new LoginPage(driver);
      test.log(LogStatus.INFO,"Test Started");
      page.Login("standard_user");
      Assert.assertTrue(page.getErrMSG().contains("Password is required"));
      test.log(page.getErrMSG().contains("Password is required") ? LogStatus.PASS  :LogStatus.FAIL ,
              page.getErrMSG().contains("Password is required") ? "Test Passed" : "Test Failed"
              );



   }
   @Test
   public void CredentislaOutOfService(){
      test = extent.startTest("Credentials Out Of Service");
      LoginPage page = new LoginPage(driver);
      page.Login("Parth","Parth");
      Assert.assertTrue(page.getErrMSG().contains("Username and password do not match any user in this service"));
      test.log(page.getErrMSG().contains("Username and password do not match any user in this service") ? LogStatus.PASS : LogStatus.FAIL , page.getErrMSG().contains("Username and password do not match any user in this service") ? "Test Passed" : "Test Failed");
   }


}
