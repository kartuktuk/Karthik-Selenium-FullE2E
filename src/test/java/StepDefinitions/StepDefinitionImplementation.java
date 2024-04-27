package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import BaseTestComponents.BaseTest;
import PageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImplementation extends BaseTest {
    public LoginPage landingPage;
    public ProductPage productPage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password) {
        productPage = loginPage.loginApp(username, password);
    }

    @When("^I add product (.+) from cart$")
    public void I_add_product_from_cart(String productName) throws InterruptedException {
        List<WebElement> products = productPage.getProductList();
        productPage.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void Checkout_product_and_submit_the_order(String productName) {
        CartPage cartPage = productPage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.performActionAndSelect("India");
        confirmationPage = checkoutPage.submitOrder();
    }


    @Then("{string} message is displayed in Confirmation Page.")
    public void messageIsDisplayedInConfirmationPage(String string) {
        String confirmMessage = confirmationPage.verifyConfirmMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed.")
    public void messageIsDisplayed(String strArg) {
        Assert.assertEquals(strArg, loginPage.invalidLoginVerify());
        driver.close();

    }
}
