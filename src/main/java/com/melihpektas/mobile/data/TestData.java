package com.melihpektas.mobile.data;

/** Test users and checkout data. The credentials are the demo app's public test accounts. */
public final class TestData {

    public record User(String username, String password) {
    }

    public record ShippingAddress(String fullName, String addressLine, String city, String zip, String country) {
    }

    public record Card(String holder, String number, String expiry, String securityCode) {
    }

    public static final User STANDARD_USER = new User("bod@example.com", "10203040");

    public static final ShippingAddress ADDRESS =
            new ShippingAddress("Ada Lovelace", "Bagdat Caddesi 12", "Istanbul", "34710", "Turkey");

    public static final Card CARD = new Card("Ada Lovelace", "4111111111111111", "1230", "123");

    private TestData() {
    }
}
