package Tests;

import BaseTest.BaseTest;
import Pages.*;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Check_out_step2Test extends BaseTest {
    //Helper
    public void EmptyCartOpener (){
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
        Check_out_step1 pg = new Check_out_step1(driver);
        test.log(LogStatus.INFO,"Entering Details");
        pg.continueBill("Parth","Chikalkar","23510111");

    }

    public void addElemsCartOpener (int n){
        test.log(LogStatus.INFO,"Loggin In");
        LoginPage page = new LoginPage(driver);
        page.Login("standard_user","secret_sauce");
        test.log(LogStatus.INFO,"Login Sucessfully");
        HomePage pge = new HomePage(driver);
        test.log(LogStatus.INFO,"Adding Items to Cart");
        pge.AddFirstNitems(n);
        test.log(LogStatus.INFO,"Navigating to Cart");
        pge.openCart();
        test.log(LogStatus.INFO,"Clicking Checkout !");

        CartPage p = new CartPage(driver);
        p.clickCheckout();
        Check_out_step1 pg = new Check_out_step1(driver);
        test.log(LogStatus.INFO,"Entering Details");
        pg.continueBill("Parth","Chikalkar","23510111");

    }
    public boolean CheckCalculations() {

        Check_out_step2 page = new Check_out_step2(driver);
        test.log(LogStatus.INFO, "Verifying billing calculations");

        double itemSum = page.calculateItemSum();
        double itemTotal = page.getItemTotal();
        double tax = page.getTax();
        double finalTotal = page.getFinalPrice();

        boolean itemsMatch = Math.abs(itemSum - itemTotal) < 0.01;
        boolean finalMatch = Math.abs((itemTotal + tax) - finalTotal) < 0.01;

        return itemsMatch && finalMatch;
    }

    @Test
    public void CheckWhetherThePageLoad(){
        test = extent.startTest("Verifying Whether the page loads or not !");
        EmptyCartOpener();
        String url = driver.getCurrentUrl();
        boolean bool = url.equals("https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS : LogStatus.FAIL , bool ? "Test Passed" : "Test Failed");

    }
    @Test
    public void CheckForEmptyCheckout() {

        test = extent.startTest("Verifying checkout with empty cart");
        EmptyCartOpener();

        Check_out_step2 page = new Check_out_step2(driver);

        Assert.assertEquals(page.calculateItemSum(), 0.0, 0.01);
        Assert.assertEquals(page.getItemTotal(), 0.0, 0.01);

        test.log(LogStatus.PASS, "Empty cart checkout validated successfully");
    }
    @Test
    public void CheckforOneItemAdded (){
        test = extent.startTest("Verifying checkout for 1 item");
        addElemsCartOpener(1);


        Check_out_step2 page = new Check_out_step2(driver);
        boolean bool = CheckCalculations();
        Assert.assertTrue(bool);




        test.log(LogStatus.PASS, "1 Item checkout validated successfully");
    }

    @Test
    public void CheckForMultipleItems(){
        test = extent.startTest("Verifying checkout for multiple item");
        addElemsCartOpener(3);
        Check_out_step2 page = new Check_out_step2(driver);
        boolean bool = CheckCalculations();
        Assert.assertTrue(bool);
        test.log(LogStatus.PASS, "Multiple Item checkout validated successfully");
    }
    @Test
    public void finishCheckout(){
        test = extent.startTest("Verifying checkout for multiple item finish");
        addElemsCartOpener(3);
        Check_out_step2 page = new Check_out_step2(driver);
        boolean bool = CheckCalculations();
        Assert.assertTrue(bool);
        test.log(LogStatus.PASS, "Multiple Item checkout validated successfully");
        page.finishCheckout();
        boolean bools = driver.getCurrentUrl().contains("/checkout-complete.html");
        Assert.assertTrue(bool);
        test.log(bools ? LogStatus.PASS : LogStatus.FAIL , bools ? "Passed" :"Failed");

    }

    @Test
    public void returnHome(){
        test = extent.startTest("Verifying checkout for multiple item finish");
        addElemsCartOpener(3);
        Check_out_step2 page = new Check_out_step2(driver);
        boolean bool = CheckCalculations();
        Assert.assertTrue(bool);
        test.log(LogStatus.PASS, "Multiple Item checkout validated successfully");
        page.finishCheckout();
        boolean bools = driver.getCurrentUrl().contains("/checkout-complete.html");
        Assert.assertTrue(bool);
        test.log(bools ? LogStatus.PASS : LogStatus.FAIL , bools ? "Passed" :"Failed");
        test.log(LogStatus.INFO,"Clicking return home ");
        page.returnHome();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        test.log(LogStatus.PASS,"Succesfully Naviagted to the inventory Page");
    }





}
