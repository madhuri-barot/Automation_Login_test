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
  @Test(priority = 1)
    public void testValidLogin() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys("admin");

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("12345678");

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        loginButton.click();

        // Add a simple wait or better use WebDriverWait for better handling of the page load
        try {
            Thread.sleep(3000); // Replace with WebDriverWait in real implementation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the page title or a specific element confirms login success
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Dashboard - Trulife Admin Portal", "Login failed - Title mismatch");
    }

    // 2. Invalid Username Test
    @Test(priority = 2)
    public void testInvalidUsername() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys("invalidUser");

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("12345678");

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        loginButton.click();

        try {
            Thread.sleep(3000); // Replace with WebDriverWait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement errorMessage = driver.findElement(By.id("error-message-id")); // Adjust ID based on actual page
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid username");
    }

    // 3. Invalid Password Test
    @Test(priority = 3)
    public void testInvalidPassword() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys("admin");

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("wrongPassword");

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        loginButton.click();

        try {
            Thread.sleep(3000); // Replace with WebDriverWait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement errorMessage = driver.findElement(By.id("error-message-id")); // Adjust ID based on actual page
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid password");
    }

    // 4. Empty Username and Password Test
    @Test(priority = 4)
    public void testEmptyUsernameAndPassword() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.clear();

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.clear();

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        loginButton.click();

        try {
            Thread.sleep(3000); // Replace with WebDriverWait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement errorMessage = driver.findElement(By.id("error-message-id")); // Adjust ID based on actual page
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for empty fields");
    }

    // 5. Login Button Disabled on Empty Fields
    @Test(priority = 5)
    public void testLoginButtonDisabled() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.clear();

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.clear();

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        Assert.assertFalse(loginButton.isEnabled(), "Login button should be disabled when fields are empty");
    }

    // 6. Special Characters in Username/Password Test
    @Test(priority = 6)
    public void testSpecialCharacters() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys("admin!@#");

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("1234$%^");

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        loginButton.click();

        try {
            Thread.sleep(3000); // Replace with WebDriverWait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Dashboard - Trulife Admin Portal", "Login failed with special characters");
    }

    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
