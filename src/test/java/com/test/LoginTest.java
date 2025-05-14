package com.test;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Required for GitHub Actions
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testLogin() {
        driver.get("https://trulifeadminportal-uat.azurewebsites.net/login");

        // Locate and fill username
        WebElement usernameField = driver.findElement(By.id("txtUsername")); // Adjust ID if needed
        usernameField.sendKeys("admin");

        // Locate and fill password
        WebElement passwordField = driver.findElement(By.id("txtPassword")); // Adjust ID if needed
        passwordField.sendKeys("12345678");

        // Locate and click login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        loginButton.click();

        // Wait and verify post-login page (adjust as per actual landing page)
        try {
            Thread.sleep(3000);  // Replace with explicit waits if needed
            System.out.println("Page title after login: " + driver.getTitle());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
