package Tests;

import BaseTest.BaseTest;
import Pages.CartPage;
import Pages.Check_out_step1;
import Pages.HomePage;
import Pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Check_out_step1Test extends BaseTest {
    //Helpers
    public void opener (){
        test.log(LogStatus.INFO,"Loggin In");
        LoginPage page = new LoginPage(driver);
        page.Login("standard_user","secret_sauce");
        test.log(LogStatus.INFO,"Login Sucessfully");
        HomePage pge = new HomePage(driver);
        test.log(LogStatus.INFO,"Navigating to Cart");
        pge.openCart();
        test.log(LogStatus.INFO,"Clicking Checkout !");

        CartPage p = new CartPage(driver);
        p.clickCheckout();




    }
    @Test
    public void checkWhetherThePageLoads (){
        test = extent.startTest("Checking whether the page loads");
        opener();
        String url = driver.getCurrentUrl();
        boolean bool = url.equals("https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS : LogStatus.FAIL , bool ? "Passed Sucesfully" : "Failed !");
    }

    @Test
    public void sendOnlyFirstName (){
        test = extent.startTest("Sending only first name and verifying the err_message");
        opener();
        Check_out_step1 page = new Check_out_step1(driver);
        test.log(LogStatus.INFO,"Sending 1st Name");

        page.continueBill("Parth");
        test.log(LogStatus.INFO,"Checking the errMsg");
        String err= page.getErrorMsg();
        System.out.println(err);
        boolean bool = err.contains("Last Name");
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS : LogStatus.FAIL , bool ? "Test Passed" : "Test Failed");

    }
    @Test
    public void sendFirstandLastName (){
        test = extent.startTest("Sending only first name and Last Name verifying the err_message");
        opener();
        Check_out_step1 page = new Check_out_step1(driver);

        test.log(LogStatus.INFO,"Sending 1st and 2nd Name");
        page.continueBill("Parth","Chikalkar");
        test.log(LogStatus.INFO,"Checking the errMsg");
        String err= page.getErrorMsg();
        System.out.println(err);
        boolean bool = err.contains("Postal Code");
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS : LogStatus.FAIL , bool ? "Test Passed" : "Test Failed");
    }

    @Test
    public void SendingEverything(){
        test = extent.startTest("Sending all and verifying the err_message");
        opener();
        Check_out_step1 page = new Check_out_step1(driver);

        test.log(LogStatus.INFO,"Sending 1st and 2nd Name and pin code ");
        page.continueBill("Parth","Chikalkar" , "23510211");
        test.log(LogStatus.INFO,"Checking the errMsg");
        boolean bool = driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS : LogStatus.FAIL , bool ? "Test Passed" : "Test Failed");
    }
    @Test
    public void ClickCancel(){

        test = extent.startTest("Clicking Cancel ");
        opener();
        Check_out_step1 page = new Check_out_step1(driver);

        test.log(LogStatus.INFO,"Clicking Cancel");
        page.cancel();

        boolean bool = driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html");
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS : LogStatus.FAIL , bool ? "Test Passed" : "Test Failed");
    }





}
