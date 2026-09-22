package com.melihpektas.mobile.tests;

import com.melihpektas.mobile.data.TestData;
import com.melihpektas.mobile.pages.CheckoutCompletePage;
import com.melihpektas.mobile.pages.CheckoutShippingPage;
import com.melihpektas.mobile.pages.LoginPage;
import com.melihpektas.mobile.pages.ReviewOrderPage;
import com.melihpektas.mobile.support.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest {

    @Test(groups = "smoke", description = "A guest adds a product, logs in at checkout and places an order")
    public void customerCanCompleteAPurchase() {
        catalog().openProduct(0).addToCart();
        assertTrue(header().hasCartCount("1"), "cart badge should show 1 item");

        LoginPage login = header().openCart().proceedToCheckoutAsGuest();
        login.loginAs(TestData.STANDARD_USER);

        ReviewOrderPage review = new CheckoutShippingPage(driver)
                .fill(TestData.ADDRESS)
                .continueToPayment()
                .fill(TestData.CARD)
                .reviewOrder();
        assertTrue(review.isShown(), "order review screen should be shown");

        CheckoutCompletePage complete = review.placeOrder();
        assertTrue(complete.isShown(), "order confirmation should be shown");
    }
}
