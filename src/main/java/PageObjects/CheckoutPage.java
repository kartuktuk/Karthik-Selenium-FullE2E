package PageObjects;

import AbstractComponents.Abstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends Abstract {

WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    By dropdownList = By.cssSelector(".list-group.ng-star-inserted");

    @FindBy(css = ".ta-item:nth-child(3)")
    WebElement countrySelect;

    @FindBy(css = ".action__submit")
    WebElement submit;

    public void performActionAndSelect(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForAppear(dropdownList);
        countrySelect.click();
    }
    public ConfirmationPage submitOrder() {
        submit.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
