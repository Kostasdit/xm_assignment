package com.xm.assignment.driver;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;

/**
 * The launch driver class
 * Here we create the loader for the first time and we use it in the test
 *
 */

@Component
public class LaunchDriver {
    public static WebDriver d = null;

    /**
     * The class which determines the url , the driver and the resolution of the screen(given from testNG parameters)
     * @param targetHostUrl
     * @param driver
     * @param resolution
     */
    public static void launchBrowser(String targetHostUrl,String driver,String resolution) {
        switch (driver) {
            case "ChromeDriver" -> {
                System.out.println("Launching Chrome Driver");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                d = new ChromeDriver();
            }
            case "FirefoxDriver" -> {
                System.out.println("Launching FireFox Driver");
                System.setProperty("webdriver.gecko.driver", "src/main/resources/firefoxdriver.exe");
                d = new FirefoxDriver();
            }
            ///and so on..
        }

        switch (resolution) {
            case "max" -> d.manage().window().maximize();
            case "1024x768" -> d.manage().window().setSize(new Dimension(1024, 768));
            case "800x600" -> d.manage().window().setSize(new Dimension(800, 600));
        }

        d.navigate().to(targetHostUrl);
        System.out.println("Driver is up");
    }

    public WebDriver getDriver(){
        return d;
    }

    public void closeDriver(){d.quit();}
}
