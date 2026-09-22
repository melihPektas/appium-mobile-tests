package com.melihpektas.mobile.tests;

import com.melihpektas.mobile.pages.CartPage;
import com.melihpektas.mobile.pages.ProductDetailPage;
import com.melihpektas.mobile.support.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Test(description = "Adding a product with quantity 2 updates the cart badge and the cart")
    public void addingProductsUpdatesTheCart() {
        String product = catalog().productNames().get(0);
        ProductDetailPage details = catalog().openProduct(0);

        details.increaseQuantity();
        assertTrue(details.hasQuantity("2"), "quantity should be 2 after tapping plus");

        details.addToCart();
        assertTrue(header().hasCartCount("2"), "cart badge should show 2 items");

        CartPage cart = header().openCart();
        assertEquals(cart.itemNames(), List.of(product));
    }
}
