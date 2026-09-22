package com.melihpektas.mobile.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.time.Duration;

/** Creates a fresh Android session; each test starts from a clean app state. */
public final class DriverFactory {

    private DriverFactory() {
    }

    public static AndroidDriver create() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setApp(Path.of(Config.appPath()).toAbsolutePath().toString())
                .setAppWaitActivity("*")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(120))
                .setUiautomator2ServerInstallTimeout(Duration.ofSeconds(120))
                .setAdbExecTimeout(Duration.ofSeconds(60));
        try {
            return new AndroidDriver(URI.create(Config.appiumUrl()).toURL(), options);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid Appium URL: " + Config.appiumUrl(), e);
        }
    }
}
