package com.example;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SampleTest {

    @Test
    public void testPerfecto() throws Exception {

        String SecurityToken = System.getProperty("PERFECTO_TOKEN");
        String CloudName = System.getProperty("PERFECTO_CLOUD");
        System.out.println("Cloud: " + CloudName);
        System.out.println("Token present: " + (SecurityToken != null));
        System.out.println("Token length: " + (SecurityToken != null ? SecurityToken.length() : 0));

        String host = CloudName + ".perfectomobile.com";

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("R5CY63273JL")
                .setPlatformName("Android")
                .setAutomationName("UIAutomator2");

        Map<String, Object> perfectoOptions = new HashMap<>();
        perfectoOptions.put("securityToken", SecurityToken);
        perfectoOptions.put("reportKey", "GitHubExecution"); // IMPORTANT
        perfectoOptions.put("useAppiumForWeb", true);
        perfectoOptions.put("browserName", "Chrome");


        options.setCapability("perfecto:options", perfectoOptions);

        AndroidDriver driver = new AndroidDriver(
                new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
                options
        );
        System.out.println("Creating driver...");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Session ID: " + driver.getSessionId());

        PerfectoExecutionContext context = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
                .withProject(new Project("CI Project", "1.0"))
                .withJob(new Job("GitHub", 1))
                .withWebDriver(driver)
                .build();

        ReportiumClient reportiumClient =
                new ReportiumClientFactory().createPerfectoReportiumClient(context);

        reportiumClient.testStart("CI Test", new TestContext("GitHub"));

        try {
            System.out.println("Opening website...");
            driver.get("https://www.demoblaze.com");

            useraction(driver, "click on login button");

            Thread.sleep(3000);

            reportiumClient.testStop(TestResultFactory.createSuccess());

        } catch (Exception e) {
            reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
            throw e;
        } finally {
            driver.quit();
        }
    }

    public static boolean useraction(RemoteWebDriver driver, String command) {
        return (Boolean) driver.executeScript("perfecto:ai:user-action",
                Map.of(
                        "action", command,
                        "reasoning", false,
                        "outputVariable", false
                )
        );
    }
}