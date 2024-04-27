package Tests;

import BaseTestComponents.BaseTest;
import PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PositiveTests extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException {
        ProductPage productPage = loginPage.loginApp(input.get("email"), input.get("password"));
        productPage.addProductToCart(input.get("product"));
        CartPage cartPage = productPage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.performActionAndSelect("India");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.verifyConfirmMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() throws InterruptedException {
        ProductPage productPage = loginPage.loginApp("dum@dum.dum", "Dumsdumsdums3@");
        OrderPage orderPage = productPage.goToOrderPage();
        orderPage.verifyProductDisplay(productName);
        Assert.assertTrue(orderPage.verifyProductDisplay(productName));
    }


    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};


        /*
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "dum@dum.dum");
        map.put("password", "Dumsdumsdums3@");
        map.put("product", "ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("email", "dums@dum.dum");
        map1.put("password", "Dumsdumsdums3@");
        map1.put("product", "ADIDAS ORIGINAL");
         */
    }
}
