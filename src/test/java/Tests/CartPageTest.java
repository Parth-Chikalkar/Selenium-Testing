package Tests;

import BaseTest.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ItemDetailPage;
import Pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

    public void OpenPage(){
        LoginPage page = new LoginPage(driver);
        page.Login("standard_user","secret_sauce");
        HomePage pge = new HomePage(driver);
        pge.openCart();

    }
    public void AddAItemandOpen() {
        test.log(LogStatus.INFO,"Logging In ");
        LoginPage page = new LoginPage(driver);
        page.Login("standard_user","secret_sauce");
        HomePage pge = new HomePage(driver);
        test.log(LogStatus.INFO,"Clicking 1st Item");
        pge.getFirstItemAndClick();
        test.log(LogStatus.INFO,"Opening Cart");
        pge.openCart();
    }
    @Test
    public void verifyCartIsOpen(){
        test = extent.startTest("Navigating to CartPage");
        OpenPage();
        test.log(LogStatus.PASS,"Navigated to the cart page succesfully");
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }

    @Test
    public void verifyItemsINcart (){
        test= extent.startTest("Verifying Item is added in Cart");

        AddAItemandOpen();
        CartPage page = new CartPage(driver);
        Assert.assertTrue(page.checkItemIsVisible());
        test.log(LogStatus.PASS,"Passed Sucessfully !");



    }
    @Test
    public void checkTheBadgeNoAndNoOfItemInCart(){
        test = extent.startTest("Verifying the badge no and the number of items in cart");
        AddAItemandOpen();
        CartPage page = new CartPage(driver);
        test.log(LogStatus.PASS,"Passed for single items added ");
        Assert.assertTrue( page.checkNoOfItems());
    }

    @Test
    public void removeItemFromCart(){
        test = extent.startTest("Verifying Removal of item from the Cart");
        AddAItemandOpen();
        test.log(LogStatus.INFO,"Clicking remove button");
        CartPage page = new CartPage(driver);
page.remove();
Assert.assertTrue(!page.checkItemIsVisible());
test.log(LogStatus.PASS,"Test Passed ");
    }
    @Test
    public void addMultipleItems (){
        test = extent.startTest("Adding Multiple items to cart and Verifying");
        test.log(LogStatus.INFO,"Test Started !");
        LoginPage page = new LoginPage(driver);
        page.Login("standard_user","secret_sauce");
        test.log(LogStatus.PASS,"Login Sucessfull");
        HomePage pge = new HomePage(driver);
        pge.AddFirstNitems(3);
        test.log(LogStatus.PASS,"Sucessfully Added 3 items to Cart");
        test.log(LogStatus.INFO,"Starting Verification");
        pge.openCart();
        test.log(LogStatus.INFO,"Opened Cart Sucessfully");
        CartPage pg = new CartPage(driver);
        boolean bool = pg.checkNoOfItems();
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS :LogStatus.FAIL , bool ? "Test Passed " : "Test Failed");

    }
    @Test
    public void removeMultipleItems (){
        test = extent.startTest("Adding Multiple items to cart and Verifying");
        test.log(LogStatus.INFO,"Test Started !");
        LoginPage page = new LoginPage(driver);
        page.Login("standard_user","secret_sauce");
        test.log(LogStatus.PASS,"Login Sucessfull");
        HomePage pge = new HomePage(driver);
        pge.AddFirstNitems(3);
        test.log(LogStatus.PASS,"Sucessfully Added 3 items to Cart");
        test.log(LogStatus.INFO,"Starting Verification");
        pge.openCart();
        test.log(LogStatus.INFO,"Opened Cart Sucessfully");
        CartPage pg = new CartPage(driver);
        boolean bool = pg.checkNoOfItems();
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS :LogStatus.FAIL , bool ? "Test Passed " : "Test Failed");
        test.log(LogStatus.INFO,"Started Removing Items");
        pg.removeMultipleItems();
        boolean bool2 = pg.checkNoOfItems();
        Assert.assertTrue(bool2);
        test.log(bool2 ? LogStatus.PASS :LogStatus.FAIL , bool2 ? "Test Passed " : "Test Failed");
    }
    @Test
    public void continueShopping (){
        test = extent.startTest("Clicking continue Shopping");
        test = extent.startTest("Adding Multiple items to cart and Verifying");
        test.log(LogStatus.INFO,"Test Started !");
        LoginPage page = new LoginPage(driver);
        page.Login("standard_user","secret_sauce");
        test.log(LogStatus.PASS,"Login Sucessfull");
        HomePage pge = new HomePage(driver);
        pge.AddFirstNitems(3);
        test.log(LogStatus.PASS,"Sucessfully Added 3 items to Cart");
        test.log(LogStatus.INFO,"Starting Verification");
        pge.openCart();
        CartPage pg = new CartPage(driver);
        test.log(LogStatus.INFO,"Clicked continue Shopping");
        pg.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }
    @Test
    public void verifyItemDetails(){
        test = extent.startTest("Verifying Details");
        test = extent.startTest("Adding Multiple items to cart and Verifying");
        test.log(LogStatus.INFO,"Test Started !");
        LoginPage page = new LoginPage(driver);
        page.Login("standard_user","secret_sauce");
        test.log(LogStatus.PASS,"Login Sucessfully");
        HomePage pge = new HomePage(driver);
        pge.AddFirstNitems(1);
        pge.openCart();
        CartPage pg = new CartPage(driver);
        test.log(LogStatus.INFO,"Clicking Item to fetch Details");
        String iname = pg.getName();
        pg.clickItemTitle();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        test.log(LogStatus.PASS,"Succesfully Navigated to details via cart page");
        ItemDetailPage page1 = new ItemDetailPage(driver);
        String itemname = page1.getName();
        boolean bool = iname.equals(itemname);
        Assert.assertTrue(bool);
        test.log(bool ? LogStatus.PASS : LogStatus.FAIL , bool ? "Tess Passed" : "Test Failed");
    }
}
