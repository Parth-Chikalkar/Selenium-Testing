package Tests;

import BaseTest.BaseTest;
import Pages.HomePage;
import Pages.ItemDetailPage;
import Pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.logging.Log;
import org.openqa.selenium.devtools.v142.domstorage.model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;

    public class ItesmDetailPage extends BaseTest {
    //Helper Function
    public void opener(){
        LoginPage page = new LoginPage(driver);
        page.Login("standard_user","secret_sauce");
    }
    @Test
    public void pageOpeningTest (){
        test = extent.startTest("Navigating to the Page !");
        opener();
        ItemDetailPage Ipage = new ItemDetailPage(driver);
        Ipage.click1stItem();
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("id"));
        test.log(LogStatus.PASS,"Sucesfully Navigated to the Page");
    }
    @Test
    public void verifyDetails (){
        test = extent.startTest("Verifying Detials from homepage and the detials page ");
        opener();
        test.log(LogStatus.INFO,"Clicking 1st Item");
        ItemDetailPage page = new ItemDetailPage(driver);
        test.log(LogStatus.INFO,"Items are being clicked ");
        boolean bool = page.check1sItem();
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS : LogStatus.FAIL , bool ? "Test Passed Items price and title is verified" : "Test Failed");
    }

    @Test
    public void addToCart (){
        test = extent.startTest("Verifying Add to Cart Functionality !");
        opener();
        ItemDetailPage pgae = new ItemDetailPage(driver);
        test.log(LogStatus.INFO,"Clicking the 1st Item in the cart");
        pgae.click1stItem();
        pgae.setAddtoCart();
        HomePage page = new HomePage(driver);
        boolean bool = page.isIconPresent();
        test.log(LogStatus.INFO,bool ? "Icon is Visible": "Icon is not visible ");
        int n = page.getNoOfItems();
        Assert.assertTrue(n==1);
        test.log(n==1 ? LogStatus.PASS : LogStatus.FAIL , n==1 ? "Test Passed" : "Test Failed");
    }
        @Test
        public void AddedtoCartAAndRemoved(){
        test = extent.startTest("Verifying whether multiple items are able to add ");
        opener();
        ItemDetailPage pgae = new ItemDetailPage(driver);
        test.log(LogStatus.INFO,"Clicking the 1st Item in the cart");
        pgae.click1stItem();
        test.log(LogStatus.INFO,"Clicked 1st Item");
        pgae.setAddtoCart();
        test.log(LogStatus.INFO,"Added 1st Item to Cart");
        HomePage page = new HomePage(driver);
            pgae.NavigateToHomePage();
        boolean bool = page.isIconPresent();

        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS : LogStatus.FAIL,bool ? "Added 1st Item Succesfully !" : "Failed Test ");
        test.log(LogStatus.INFO,"Navigating to 1st Item");
        pgae.click1stItem();
            test.log(LogStatus.INFO,"Removing item from cart");

            pgae.removeFromCart();
            Assert.assertFalse(page.isIconPresent());


        test.log(!page.isIconPresent() ? LogStatus.PASS : LogStatus.FAIL , !page.isIconPresent() ? "Test Passed , Sucessfully added and Removed From the Cart  " : "Test Failed");
            pgae.NavigateToHomePage();
            test.log(LogStatus.PASS,"Navigated Succesfully to HomePage");
    }

}
