package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.get("https://trulifeadminportal-uat.azurewebsites.net/login"); //
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testValidLogin() {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
        WebElement password = driver.findElement(By.id("txtPassword"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));

        username.clear();
        username.sendKeys("admin");
        password.clear();
        password.sendKeys("12345678");
        loginBtn.click();

        // Replace with actual post-login success condition
        wait.until(ExpectedConditions.urlContains("dashboard")); 
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Valid login succeeded");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
