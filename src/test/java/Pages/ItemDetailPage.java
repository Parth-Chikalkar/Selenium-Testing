package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemDetailPage {

    private WebDriver driver;

    // Item Detail Page elements
    private By itemTitle = By.className("inventory_details_name");
    private By itemPrice = By.className("inventory_details_price");
    private By itemNameinPge = By.xpath("//div[@data-test='inventory-item-name']");
    // HomePage item click (first item)
    private By firstItem = By.className("inventory_item_name");
    private By addtoCart = By.id("add-to-cart");
    private By remove = By.id("remove");
    private By backtoItems = By.id("back-to-products");


    public String getName(){
        return driver.findElement(itemNameinPge).getText();
    }
    public ItemDetailPage(WebDriver driver) {
        this.driver = driver;
    }
    public void removeFromCart(){
        driver.findElement(remove).click();
    }

    public void click1stItem() {
        driver.findElement(firstItem).click();
    }

    public String getItemTitle() {
        return driver.findElement(itemTitle).getText();
    }

    public String getItemPrice() {
        return driver.findElement(itemPrice).getText();
    }

    public boolean check1sItem() {
        click1stItem();

        String detailTitle = getItemTitle();
        String detailPrice = getItemPrice();

        return detailTitle != null && detailPrice != null;
    }
    public void setAddtoCart(){
        driver.findElement(addtoCart).click();
    }
    public void NavigateToHomePage(){
        driver.findElement(backtoItems).click();
    }
}
