package com.melihpektas.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductDetailPage extends BasePage {

    private final By price = id("priceTV");
    private final By quantity = id("noTV");
    private final By addToCartButton = id("cartBt");

    public ProductDetailPage(AndroidDriver driver) {
        super(driver);
    }

    public ProductDetailPage waitUntilLoaded() {
        visible(price);
        scrollTo("cartBt");
        return this;
    }

    public String price() {
        return textOf(price);
    }

    public boolean canBeAddedToCart() {
        return scrollTo("cartBt").isDisplayed();
    }

    public ProductDetailPage increaseQuantity() {
        scrollTo("plusIV").click();
        return this;
    }

    public boolean hasQuantity(String expected) {
        scrollTo("noTV");
        return waitForText(quantity, expected);
    }

    public ProductDetailPage addToCart() {
        scrollTo("cartBt").click();
        return this;
    }
}
