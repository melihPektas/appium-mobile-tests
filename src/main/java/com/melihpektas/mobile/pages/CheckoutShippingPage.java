package com.melihpektas.mobile.pages;

import com.melihpektas.mobile.data.TestData.ShippingAddress;
import io.appium.java_client.android.AndroidDriver;

public class CheckoutShippingPage extends BasePage {

    public CheckoutShippingPage(AndroidDriver driver) {
        super(driver);
    }

    public CheckoutShippingPage fill(ShippingAddress address) {
        typeInto("fullNameET", address.fullName());
        typeInto("address1ET", address.addressLine());
        typeInto("cityET", address.city());
        typeInto("zipET", address.zip());
        typeInto("countryET", address.country());
        return this;
    }

    public CheckoutPaymentPage continueToPayment() {
        scrollTo("paymentBtn").click();
        return new CheckoutPaymentPage(driver);
    }
}
