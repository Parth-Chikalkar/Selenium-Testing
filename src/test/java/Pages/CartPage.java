package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private WebDriver driver ;
    private By itemCard = By.xpath("//div[@class='cart_item']");
    private By itemNo = By.className("shopping_cart_badge");
    private List<WebElement> list;
    private By removeButton = By.id("remove-sauce-labs-backpack");
    private By continueShop = By.id("continue-shopping");
    private By anchor = By.id("item_4_title_link");
    private By check_out = By.id("checkout");




    public CartPage(WebDriver driver){
       this.driver = driver;
    }
    //  Functionalities
    public boolean checkItemIsVisible(){
        return driver.findElements(itemCard).size() > 0 ;
    }
    public void clickCheckout(){
        driver.findElement(check_out).click();
    }

   public void setItems(){
        if(checkItemIsVisible()) list = driver.findElements(itemCard);
   }

   public boolean checkNoOfItems() {
        setItems();
        return (list.size() == Integer.parseInt(driver.findElement(itemNo).getText()));
   }
   public void remove(){
     driver.findElement(removeButton).click();
   }

    public void removeMultipleItems() {
        List<WebElement> removeButtons = driver.findElements(removeButton);

        for (WebElement button : removeButtons) {
            button.click();
        }
    }
public void clickContinueShopping (){
        driver.findElement(continueShop).click();
}
public void clickItemTitle(){
        driver.findElement(anchor).click();
}
public String getName (){
        return driver.findElement(anchor).getText();
}










}
