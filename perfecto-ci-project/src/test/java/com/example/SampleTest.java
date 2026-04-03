package com.example;

//import Utilities.CloudCreds;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SampleTest {
    public static void main(String[] args) throws MalformedURLException, IOException {

        // WebContexts WC = new WebContexts();
        String CloudName = "demo";
        String SecurityToken = "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI4YmI4YmZmZS1kMzBjLTQ2MjctYmMxMS0zNTYyMmY1ZDkyMGYifQ.eyJpYXQiOjE3NzQ4NjMxNDMsImp0aSI6ImY0YzNhYjBiLTQzNWItNGIzYy1iNmE4LWQwM2ZmN2I5ZGZjMyIsImlzcyI6Imh0dHBzOi8vYXV0aC5wZXJmZWN0b21vYmlsZS5jb20vYXV0aC9yZWFsbXMvZGVtby1wZXJmZWN0b21vYmlsZS1jb20iLCJhdWQiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2RlbW8tcGVyZmVjdG9tb2JpbGUtY29tIiwic3ViIjoiNzRjMTZmNDUtMGQwMC00YmE2LTgyZjMtZDZmN2Y2YmVhMDM4IiwidHlwIjoiT2ZmbGluZSIsImF6cCI6Im9mZmxpbmUtdG9rZW4tZ2VuZXJhdG9yIiwibm9uY2UiOiI0NDAwMzc0Ni02YTc2LTQ5OTktYjc3Yy1lMTM1NDUzZTA1N2QiLCJzZXNzaW9uX3N0YXRlIjoiN2I5MzkxZGEtYzVhZi00ZDI3LWFhMDktYjYyODRmNjE2MjFiIiwic2NvcGUiOiJvcGVuaWQgb2ZmbGluZV9hY2Nlc3MiLCJzaWQiOiI3YjkzOTFkYS1jNWFmLTRkMjctYWEwOS1iNjI4NGY2MTYyMWIifQ.6qlLo3BtxKPE58wWI9aCEP1nYyhywBPPqi_R8oa5sN0";

        String host = CloudName + ".perfectomobile.com";

        System.out.println("Run started");
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setDeviceName("R5CY63273JL")
                .setAutomationName("UIAutomator2")
                .setPlatformName("Android")
                .autoGrantPermissions()
                //.setAppPackage("com.android.settings")

                ;

        //uiAutomator2Options.setCapability("browserName", "chrome");

        Map<String, Object> perfectoOptions = new HashMap<>();
        perfectoOptions.put("securityToken", SecurityToken);
        perfectoOptions.put("appiumVersion", "latest");
        //perfectoOptions.put("deviceName", "R5CXC216NGN");
        //perfectoOptions.put("platformName", "ANDROID");
        //perfectoOptions.put("appPackage","com.truist.mobile");
        perfectoOptions.put("automationVersion", "2.45.1");

        //perfectoOptions.put("noReset", true);
        perfectoOptions.put("enableAppiumBehavior", true);
        perfectoOptions.put("useAppiumForWeb", true);
        perfectoOptions.put("browserName", "Chrome");
        perfectoOptions.put("autoAcceptAlerts", true);
        // perfectoOptions.put("useVirtualDevice",false);
        //perfectoOptions.put("deviceLanguage","en-US");
        //perfectoOptions.put("deviceSessionId","372d63e7-c713-484b-acf6-b8a7589e6073" );


//        perfectoOptions.put("setWebContentsDebuggingEnabled", true);
//        perfectoOptions.put("autoGrantPermissions", true);
        //perfectoOptions.put("browserName", "Chrome");

        uiAutomator2Options.setCapability("perfecto:options", perfectoOptions);
//        uiAutomator2Options.setCapability("browserName", "mobileOS");

        AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), uiAutomator2Options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));


        PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
                .withProject(new Project("Appium2 Test", "1.0"))
                .withJob(new Job("Android", 45))
                .withContextTags("Test")
                .withWebDriver(driver)
                .build();
        ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

        try {
            reportiumClient.testStart("PerfectoTestS4", new TestContext("T1", "T2"));
            /*Boolean result = (Boolean) driver.executeScript("perfecto:ai:user-action",
                    Map.of(
                            "action", "go to www.facebook.com",
                            "reasoning", false,
                            "outputVariable", false

                    )
            );*/
            driver.get("http://www.demoblaze.com");
            //String password = ":perfecto:secure:Selenium Java";
            String raw = "Selenium";
            String secured = ":perfecto:secure:" + raw;
            useraction(driver, "click on login button and enter the password as " + secured + "");
            /*driver.activateApp("com.android.settings");

            driver.findElement(By.xpath("//*[@class=\"androidx.appcompat.widget.LinearLayoutCompat\"]")).click();
            driver.findElement(By.xpath("//*[@resource-id=\"com.android.settings.intelligence:id/search_src_text\"]")).sendKeys("Font Size");
            driver.findElement(By.xpath("//*[@resource-id=\"android:id/title\" and @text=\"Font size and style\"]")).click();
            driver.findElement(By.xpath("//*[@resource-id=\"com.android.settings:id/recycler_view\"]/android.widget.LinearLayout[9]/android.widget.RelativeLayout[1]")).click();
            driver.findElement(By.xpath("//android.widget.SeekBar"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement seekBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.SeekBar")));
            String perfectoElementId = ((RemoteWebElement) seekBar).getId();
            System.out.println("Perfecto element id = " + perfectoElementId);
            String contentDescription = seekBar.getAttribute("contentDescription");
            if (contentDescription == null || contentDescription.trim().isEmpty()) {    contentDescription = seekBar.getAttribute("content-desc"); }
            System.out.println("SeekBar content description = " + contentDescription);
            String textVal   = seekBar.getAttribute("text");
            String boundsVal = seekBar.getAttribute("bounds");
            System.out.println("SeekBar text   = " + textVal);
            System.out.println("SeekBar bounds = " + boundsVal);*/


            //driver.getCurrentUrl();
            //driver.get("www.perforce.com");
            Thread.sleep(3000);

            reportiumClient.testStop(TestResultFactory.createSuccess());
        } catch (Exception e) {
            reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
            e.printStackTrace();
        } finally {
            try {
                driver.quit();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Run ended");
    }


    public static boolean useraction(RemoteWebDriver driver, String command) {
        Boolean result = (Boolean) driver.executeScript("perfecto:ai:user-action",
                Map.of(
                        "action", command,
                        "reasoning", false,
                        "outputVariable", false

                )
        );
        return result;
    }

}
