package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Check_out_step2 {

    private WebDriver driver;

    private By item_total = By.xpath("//div[@class='summary_subtotal_label']");
    private By tax = By.xpath("//div[@class='summary_tax_label']");
    private By total = By.xpath("//div[@class='summary_total_label']");
    private By finish = By.id("finish");
    private By itemPrices = By.xpath("//div[@class='inventory_item_price']");
    private By returnHome = By.id("back-to-products");
    public Check_out_step2(WebDriver driver) {
        this.driver = driver;
    }

    private double extractPrice(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }


    public double getItemTotal() {
        return extractPrice(driver.findElement(item_total).getText());
    }

    public double getTax() {
        return extractPrice(driver.findElement(tax).getText());
    }

    public double getFinalPrice() {
        return extractPrice(driver.findElement(total).getText());
    }

    public double calculateItemSum() {
        List<WebElement> prices = driver.findElements(itemPrices);

        double sum = 0.0;
        for (WebElement price : prices) {
            sum += extractPrice(price.getText());
        }
        return sum;
    }

    public void finishCheckout() {
        driver.findElement(finish).click();
    }
        public void returnHome(){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(returnHome)).click();

        }
}
