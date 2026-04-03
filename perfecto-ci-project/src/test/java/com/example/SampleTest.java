package com.example;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;

public class SampleTest {

    @Test
    public void testPerfecto() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("deviceName", "iPhone.*");

        capabilities.setCapability("securityToken", System.getenv("PERFECTO_TOKEN"));

        IOSDriver driver = new IOSDriver(
                new URL("https://YOUR-CLOUD.perfectomobile.com/nexperience/perfectomobile/wd/hub"),
                capabilities
        );

        System.out.println("Test started successfully!");

        driver.quit();
    }
}