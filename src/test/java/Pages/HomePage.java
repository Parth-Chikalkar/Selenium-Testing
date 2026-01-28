package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private By menu = By.id("react-burger-menu-btn");
    private By cart = By.xpath("//a[@data-test='shopping-cart-link']");
    private WebDriver driver ;
    private By items = By.className("inventory_item");
    private By nav = By.xpath("//nav[@class='bm-item-list']");
    private By logout = By.xpath("//a[@id='logout_sidebar_link']");
    private By closeBtn = By.id("react-burger-cross-btn");
    private By cartitems = By.className("cart_item");
    private By addToCart = By.id("add-to-cart-sauce-labs-backpack");
    private By navList = By.xpath("//nav[@class='bm-item-list']");
    private By noOfItems = By.className("shopping_cart_badge");
    public HomePage (WebDriver driver){
        this.driver = driver;
    }
    ///Functionalities
    public boolean menuIs(){
        try {
            return driver.findElement(navList).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickMenu (){

        driver.findElement(menu).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nav));


    }
    public void Logout(){
        driver.findElement(logout).click();

    }
    public void openCart(){
        driver.findElement(cart).click();
    }
    public List<WebElement> getAllItems (){
      return driver.findElements(items);
    }
    public int getSizeOfInventory (){
        return driver.findElements(items).size();
    }
    public void getFirstItemAndClick (){

         driver.findElements(addToCart).get(0).click();
    }
    public void closeMenu (){
        driver.findElement(closeBtn).click();
    }
    public int getCartCount(){
        return driver.findElements(cartitems).size();
    }
    public boolean isIconPresent (){
        return driver.findElements(noOfItems).size() > 0;
    }
    public int getNoOfItems(){
        if (isIconPresent()) return Integer.parseInt(driver.findElement(noOfItems).getText());
        return 0;
    }
    public void AddFirstNitems(int n ){

        // Ass Dom changes evertime

        for (int i = 0; i < n ; i++) {
            List<WebElement> buttons =
                    driver.findElements(By.xpath("//button[contains(@id,'add-to-cart')]"));

            if (buttons.isEmpty()) {
                break;
            }

            buttons.get(0).click();
        }
    }




}
