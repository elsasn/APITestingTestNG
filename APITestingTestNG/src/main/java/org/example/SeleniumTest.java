package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    private WebDriver driver;

    public SeleniumTest() {
        System.setProperty("webdriver.chrome.driver", "/Users/elsa.nabila/Documents/chromedriver-mac-arm64/chromedriver");
    }

    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    public void tearDown() {
        driver.quit();
    }

    public void loginScenario(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    // You can add more test cases here
    public void runTests() {
        this.setUp();
        this.loginScenario("admin", "admin");
        this.tearDown();

        this.setUp();
        this.loginScenario("tomsmith", "SuperSecretPassword");
        this.tearDown();

        this.setUp();
        this.loginScenario("tomsmith", "SuperSecretPassword!");
        this.tearDown();
    }

    public static void main(String[] args) {
        SeleniumTest test = new SeleniumTest();
        test.runTests();
    }
}