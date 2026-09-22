package com.melihpektas.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductDetailPage extends BasePage {

    private final By productName = id("productTV");
    private final By quantity = id("noTV");

    public ProductDetailPage(AndroidDriver driver) {
        super(driver);
    }

    public String productName() {
        return textOf(productName);
    }

    public ProductDetailPage increaseQuantity() {
        scrollTo("plusIV").click();
        return this;
    }

    public String quantity() {
        return scrollTo("noTV").getText();
    }

    public ProductDetailPage addToCart() {
        scrollTo("cartBt").click();
        return this;
    }

    public boolean hasQuantity(String expected) {
        scrollTo("noTV");
        return waitForText(quantity, expected);
    }
}
