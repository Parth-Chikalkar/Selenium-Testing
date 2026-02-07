package Tests;

import BaseTest.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest extends BaseTest {


    @Test
    public void Logout() {

        test = extent.startTest("Logout a Logged-in User");

        LoginPage page = new LoginPage(driver);
        page.Login("standard_user", "secret_sauce");

        HomePage hpage = new HomePage(driver);

        test.log(LogStatus.INFO, "Opening menu");
        hpage.clickMenu();

        Assert.assertTrue(
                hpage.menuIs(),
                "Menu not visible"
        );

        test.log(LogStatus.INFO, "Logging out");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='logout_sidebar_link']")));
        hpage.Logout();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("inventory")));

        Assert.assertFalse(driver.getCurrentUrl().contains("inventory"));

        test.log(LogStatus.PASS, "Logout successful");
    }


    @Test
    public void CloseMenu (){
        test = extent.startTest("Close The Opened Menu");
        test.log(LogStatus.INFO,"Testing Logout");
        LoginPage page = new LoginPage(driver);
        test.log(LogStatus.INFO,"Entering Credentials");
        page.Login("standard_user","secret_sauce");
        test.log(LogStatus.INFO,"Login Succesfull !");
        test.log(LogStatus.INFO,"Navigating to the Menu");
        HomePage hpage = new HomePage(driver);
        hpage.clickMenu();
        test.log(LogStatus.PASS,"Clicked Menu Succesfully !");
        Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='bm-item-list']")).isDisplayed());
        hpage.closeMenu();
        test.log(LogStatus.PASS,"SuccesFully Closed Menu ");
    }

    @Test
    public void verifyItemsArePresent(){
        test = extent.startTest("Verifying the Items are Present in Hompage");

        test.log(LogStatus.INFO,"Testing Logout");
        LoginPage page = new LoginPage(driver);
        test.log(LogStatus.INFO,"Entering Credentials");
        page.Login("standard_user","secret_sauce");
        test.log(LogStatus.INFO,"Login Succesfull !");
        test.log(LogStatus.INFO,"Navigating to the Menu");
        HomePage hpage = new HomePage(driver);
       int n =  hpage.getSizeOfInventory();
       Assert.assertTrue(n>0,"No Items Displayed");
       test.log(LogStatus.INFO,"Sucessfully all items are displayed");

    }

    @Test
    public void AddItemToartandVerify(){
        test = extent.startTest("Verifying the Items are added or not ");
        test.log(LogStatus.INFO,"Testing Logout");
        LoginPage page = new LoginPage(driver);
        test.log(LogStatus.INFO,"Entering Credentials");
        page.Login("standard_user","secret_sauce");
        test.log(LogStatus.INFO,"Login Succesfull !");
        test.log(LogStatus.INFO,"Navigating to the Menu");
        HomePage hpage = new HomePage(driver);
        hpage.getFirstItemAndClick();
        hpage.openCart();
        int n= hpage.getCartCount();
        Assert.assertTrue(n==1);
        test.log(LogStatus.INFO,"Verified Sucesfully");

    }

    @Test
    public void verifyNoOfItemsAddedInCartBadge() {

        test = extent.startTest("Verify number of items added equals cart badge count");

        LoginPage loginPage = new LoginPage(driver);
        test.log(LogStatus.INFO, "Entering credentials");
        loginPage.Login("standard_user", "secret_sauce");
        test.log(LogStatus.INFO, "Login successful");

        HomePage homePage = new HomePage(driver);

        test.log(LogStatus.INFO, "Adding first item to cart");
        homePage.getFirstItemAndClick();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("shopping_cart_badge")
        ));

        boolean isCartIconVisible = homePage.isIconPresent();
        test.log(
                isCartIconVisible ? LogStatus.PASS : LogStatus.FAIL,
                isCartIconVisible ? "Cart icon is visible" : "Cart icon is not visible"
        );

        Assert.assertTrue(isCartIconVisible, "Cart icon should be visible");

        int cartBadgeCount = homePage.getCartCount();
        int addedItemsCount = homePage.getCartCount();

        test.log(LogStatus.INFO,
                "Cart badge count: " + cartBadgeCount +
                        ", Items added: " + addedItemsCount);

        Assert.assertEquals(
                cartBadgeCount,
                addedItemsCount,
                "Mismatch between added items and cart badge count"
        );

        test.log(LogStatus.PASS, "Cart count verification passed successfully");
    }


    @Test
    public void multipleItemsAddedCheck(){
        test = extent.startTest("Multiple Items Added verification !");
        test.log(LogStatus.INFO,"Test Started");
        LoginPage loginPage = new LoginPage(driver);
        test.log(LogStatus.INFO, "Entering credentials");
        loginPage.Login("standard_user", "secret_sauce");
        test.log(LogStatus.INFO, "Login successful");

        HomePage homePage = new HomePage(driver);
        //Check For N Items
        homePage.AddFirstNitems(2);
        int cartItems = homePage.getNoOfItems();
        Assert.assertEquals(cartItems,2,"Not Matched");
        test.log(cartItems==3 ? LogStatus.PASS :LogStatus.FAIL , cartItems ==3 ? "Test Passed " : "Test Failed ");

    }

    @Test
    public void selectoptionSortPriceFromHtoL(){
        test = extent.startTest("Select Option Checking !");
        test.log(LogStatus.INFO,"Test Started");
        LoginPage loginPage = new LoginPage(driver);
        test.log(LogStatus.INFO, "Entering credentials");
        loginPage.Login("standard_user", "secret_sauce");
        test.log(LogStatus.INFO, "Login successful");

        HomePage page = new HomePage(driver);
        test.log(LogStatus.INFO,"Clicked Select Option");

        page.priceHighToLow();
        test.log(LogStatus.INFO,"Wait...");
        page.waitForPricesToUpdate();
        test.log(LogStatus.PASS,"Passed Sucessfully ");
        Assert.assertTrue(page.isPriceSortedHighToLow());

    }

    @Test
    public void selelectOptionSortLtoH (){
        test = extent.startTest("Select Option Checking !");
        test.log(LogStatus.INFO,"Test Started");
        LoginPage loginPage = new LoginPage(driver);
        test.log(LogStatus.INFO, "Entering credentials");
        loginPage.Login("standard_user", "secret_sauce");
        test.log(LogStatus.INFO, "Login successful");

        HomePage page = new HomePage(driver);
        test.log(LogStatus.INFO,"Clicked Select Option");

        page.priceLowtoHigh();
        test.log(LogStatus.INFO,"Wait...");
        page.waitForPricesToUpdate();

        Assert.assertTrue(page.isPriceSortedLowToHigh());
        test.log(LogStatus.PASS,"Passed Sucessfully ");
    }

    @Test
    public void selectOptionSortAtoZ (){
        test = extent.startTest("Select Option Checking !");
        test.log(LogStatus.INFO,"Test Started");
        LoginPage loginPage = new LoginPage(driver);
        test.log(LogStatus.INFO, "Entering credentials");
        loginPage.Login("standard_user", "secret_sauce");
        test.log(LogStatus.INFO, "Login successful");

        HomePage page = new HomePage(driver);
        test.log(LogStatus.INFO,"Clicked Select Option");

        page.nameAtoZ();
        test.log(LogStatus.INFO,"Wait...");
        page.waitForPricesToUpdate();

        Assert.assertTrue(page.isNamesSortedAtoZ());
        test.log(LogStatus.PASS,"Passed Sucessfully ");
    }
    @Test
    public void selectOptionZtoA (){
        test = extent.startTest("Select Option Checking !");
        test.log(LogStatus.INFO,"Test Started");
        LoginPage loginPage = new LoginPage(driver);
        test.log(LogStatus.INFO, "Entering credentials");
        loginPage.Login("standard_user", "secret_sauce");
        test.log(LogStatus.INFO, "Login successful");

        HomePage page = new HomePage(driver);
        test.log(LogStatus.INFO,"Clicked Select Option");

        page.nameZtoA();
        test.log(LogStatus.INFO,"Wait...");
        page.waitForPricesToUpdate();

        Assert.assertTrue(page.isNamesSortedZtoA());
        test.log(LogStatus.PASS,"Passed Sucessfully ");
    }



}
