package com.melihpektas.mobile.driver;

/** Runtime settings, overridable through environment variables so the same suite runs locally and in CI. */
public final class Config {

    public static final String APP_PACKAGE = "com.saucelabs.mydemoapp.android";

    private Config() {
    }

    public static String appiumUrl() {
        return env("APPIUM_URL", "http://127.0.0.1:4723");
    }

    public static String appPath() {
        return env("APP_PATH", "apps/mda.apk");
    }

    private static String env(String name, String fallback) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? fallback : value;
    }
}
