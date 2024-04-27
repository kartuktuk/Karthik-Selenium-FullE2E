package Tests;

import BaseTestComponents.BaseTest;
import BaseTestComponents.Retry;
import PageObjects.CartPage;
import PageObjects.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorTests extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void invalidLogin() throws InterruptedException {
        String productName = "ZARA COAT 3";
        loginPage.loginApp("dum@dum.dum", "Dumsdumsdums");
        Assert.assertEquals("Incorrect email or password.", loginPage.invalidLoginVerify());
    }


    @Test(groups = {"ErrorHandling"})
    public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";
        ProductPage productPage = loginPage.loginApp("dums@dum.dum", "Dumsdumsdums3@");
        productPage.addProductToCart(productName);
        CartPage cartPage = productPage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay("Zara Coat");
        Assert.assertFalse(match);
    }
}
