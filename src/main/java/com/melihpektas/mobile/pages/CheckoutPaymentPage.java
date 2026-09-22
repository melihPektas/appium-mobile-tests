package com.melihpektas.mobile.pages;

import com.melihpektas.mobile.data.TestData.Card;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPaymentPage extends BasePage {

    public CheckoutPaymentPage(AndroidDriver driver) {
        super(driver);
    }

    public CheckoutPaymentPage fill(Card card) {
        typeInto("nameET", card.holder());
        typeInto("cardNumberET", card.number());
        typeInto("expirationDateET", card.expiry());
        typeInto("securityCodeET", card.securityCode());
        useShippingAddressForBilling();
        return this;
    }

    public ReviewOrderPage reviewOrder() {
        scrollTo("paymentBtn").click();
        return new ReviewOrderPage(driver);
    }

    private void useShippingAddressForBilling() {
        WebElement sameAsShipping = scrollTo("billingAddressCB");
        if (!"true".equals(sameAsShipping.getAttribute("checked"))) {
            sameAsShipping.click();
        }
    }
}
