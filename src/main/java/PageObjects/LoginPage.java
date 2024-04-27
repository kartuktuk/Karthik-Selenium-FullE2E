package PageObjects;

import AbstractComponents.Abstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Abstract {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        //constructor always intialized first
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "userEmail")
    WebElement email;
    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id ="login")
    WebElement submitButton;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductPage loginApp(String emailElem, String passwordElem) {
        email.sendKeys(emailElem);
        password.sendKeys(passwordElem);
        submitButton.click();
        ProductPage productPage = new ProductPage(driver);
        return productPage;
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");

    }

    public String invalidLoginVerify() {
        waitForAppear(errorMessage);
        return errorMessage.getText();
    }




}
