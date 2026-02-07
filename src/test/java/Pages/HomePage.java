package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.Name;
import java.time.Duration;
import java.util.ArrayList;
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
    private By selection = By.className("product_sort_container");
    private List <WebElement> pricesList ;
    private By prices = By.xpath("//div[@class='inventory_item_price']");
    private Select select ;
    private List<WebElement> slisttt ;
    private By nam = By.xpath("//div[@class='inventory_item_name ']");
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
  // All bout Select in the homerpage
    public void clickSelect(){
       select = new Select(driver.findElement(selection));
    }

    public void priceHighToLow (){
        clickSelect();
        select.selectByIndex(3);
    }
    public void priceLowtoHigh (){
        clickSelect();
        select.selectByIndex(2);
    }
    public void nameAtoZ (){
        clickSelect();
        select.selectByIndex(0);
    }
    public void nameZtoA (){
        clickSelect();
        select.selectByIndex(1);
    }

    public List<Double> setListAndGetPrice (){
        pricesList = driver.findElements(prices);
        List<Double> plist = new ArrayList<>();
        for (int i = 0; i < pricesList.size(); i++) {
            plist.add(Double.parseDouble(pricesList.get(i).getText().replace("$","")));
        }
        return plist;
    }
    public List<String> setListAndSetString(){
        slisttt = driver.findElements(nam);
        List<String> l = new ArrayList<>();
        for (int i = 0; i < slisttt.size(); i++) {
            l.add(slisttt.get(0).getText());
        }
        return l;
    }


    public boolean isNamesSortedAtoZ() {
        List<String> list = setListAndSetString();

        if (list == null || list.size() < 2) return true;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareToIgnoreCase(list.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }
    public boolean isNamesSortedZtoA() {
        List<String> list = setListAndSetString();

        if (list == null || list.size() < 2) return true;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareToIgnoreCase(list.get(i - 1)) > 0) {
                return false;
            }
        }
        return true;
    }



    public boolean isPriceSortedHighToLow() {
        List<Double> list = setListAndGetPrice();
        if (list == null || list.size() < 2) return true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPriceSortedLowToHigh() {
        List<Double> list =setListAndGetPrice();
        if (list == null || list.size() < 2) return true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    public void waitForPricesToUpdate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(prices));
    }














}
