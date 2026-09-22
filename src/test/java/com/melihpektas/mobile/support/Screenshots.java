package com.melihpektas.mobile.support;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

final class Screenshots {

    private static final Path DIR = Path.of("target", "screenshots");

    private Screenshots() {
    }

    static void save(AndroidDriver driver, String name) {
        try {
            Files.createDirectories(DIR);
            Path source = driver.getScreenshotAs(OutputType.FILE).toPath();
            Files.copy(source, DIR.resolve(name + ".png"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException | RuntimeException e) {
            System.err.println("Could not save screenshot for " + name + ": " + e.getMessage());
        }
    }
}
