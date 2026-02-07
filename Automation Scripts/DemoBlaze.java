package com.demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DemoBlaze {
	
WebDriver driver;
    

    @BeforeMethod
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }
	
    
    @Test(priority = 1)
    public void validLoginTest() throws InterruptedException {
        driver.findElement(By.id("login2")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("loginusername")).sendKeys("user@15");
        Thread.sleep(1000);
        driver.findElement(By.id("loginpassword")).sendKeys("Pass@1234");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(3000);
        
        Assert.assertEquals(driver.getTitle(), "STORE");

    }
    
    
    @Test(priority = 2)
    public void invalidLoginTest() throws InterruptedException {
        driver.findElement(By.id("login2")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("loginusername")).sendKeys("user@15");
        Thread.sleep(1000);
        driver.findElement(By.id("loginpassword")).sendKeys("Pass@123");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(1000);
    }
    
    
    @Test(priority = 3)
    public void blankLoginTest()  throws InterruptedException{
        driver.findElement(By.id("login2")).click();
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(1000);
        // wait for alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        // assertion
        Assert.assertEquals(alert.getText(), "Please fill out Username and Password.");
        // close alert
        alert.accept();
    }

       
    @Test(priority = 4)
    public void searchProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Phones']"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Iphone 6 32gb']"))).click();
        Thread.sleep(3000);
    }  

    
    @Test(priority = 5)
    public void searchProduct1() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Laptops']"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='MacBook air']"))).click();
        Thread.sleep(3000);
    }
    
    @Test(priority = 6)
    public void addtoCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Phones']"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Nexus 6']"))).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();      
        Thread.sleep(1000);
    } 
    
    @Test(priority = 7)
    public void verifyProductAddedToCart() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Phones']"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//a[text()='Nokia lumia 1520']"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        Thread.sleep(1000);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Product added");
        alert.accept();
      
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();
        Thread.sleep(1000);      
        boolean productPresent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Nokia lumia 1520']"))).isDisplayed();

        Assert.assertTrue(productPresent, "Product was not found in cart");
    }
    
    
    
    @Test(priority = 8)
    public void LogoutTest() throws InterruptedException {
        driver.findElement(By.id("login2")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("loginusername")).sendKeys("user@15");
        Thread.sleep(1000);
        driver.findElement(By.id("loginpassword")).sendKeys("Pass@1234");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("logout2")).click();
        Thread.sleep(2000);
    }
    
    
    
    @Test(priority = 9)
    public void placeOrder() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Monitors']"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Apple monitor 24']"))).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();      
        Thread.sleep(1000);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Product added");
        alert.accept();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']"))).sendKeys("John");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='country']"))).sendKeys("United States");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='city']"))).sendKeys("Boston");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='card']"))).sendKeys("436976158762");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='month']"))).sendKeys("09");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='year']"))).sendKeys("2026");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Purchase']"))).click();     
        Thread.sleep(1000);
    } 
    
    
    @Test(priority = 10)
    public void verifyOrderConfirmation() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Laptops']"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='MacBook air']"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();      
        Thread.sleep(1000);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Product added");
        alert.accept();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']"))).sendKeys("John");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='country']"))).sendKeys("United States");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='city']"))).sendKeys("Boston");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='card']"))).sendKeys("436976158762");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='month']"))).sendKeys("09");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='year']"))).sendKeys("2026");      
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Purchase']"))).click();     
        Thread.sleep(1000);
        
    String confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Thank you for your purchase!']"))).getText();

        Assert.assertEquals(confirmationMsg, "Thank you for your purchase!");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();        
    } 
	 
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
