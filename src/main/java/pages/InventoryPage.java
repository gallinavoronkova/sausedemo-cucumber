package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

    public InventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_list")
    public WebElement inventoryList;

}