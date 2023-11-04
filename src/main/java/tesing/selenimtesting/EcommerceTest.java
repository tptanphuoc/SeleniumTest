/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tesing.selenimtesting;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author huynh
 */
public class EcommerceTest {
    public static void main(String[] args) {
        String quantity = "10";
        String username = "aaaaa@email.com";  // Replace with your username
        String password = "A123456";
        
        String driverPath = "chromedriver.exe";
        
        System.setProperty("webdriver.chrome.driver", driverPath); // Update path to chromedriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Step 1: Go to the website
            driver.get("http://live.techpanda.org/");

            // Step 2: Click on 'My Account' link
            WebElement myAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("MY ACCOUNT")));
            myAccountLink.click();

            // Step 3: Log in using previously created credentials
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailInput.sendKeys(username);

            WebElement passwordInput = driver.findElement(By.id("pass"));
            passwordInput.sendKeys(password);

            WebElement loginButton = driver.findElement(By.id("send2"));
            loginButton.click();

            // Step 4: Click on 'REORDER' link, change QTY & click Update
            WebElement reorderLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("REORDER")));
            reorderLink.click();

            WebElement quantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Qty']")));
            quantityField.clear();
            quantityField.sendKeys(quantity);

            WebElement updateButton = driver.findElement(By.xpath("//button[@title='Update']"));
            updateButton.click();

            // Step 5: Verify Grand Total is changed
            WebElement grandTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(), 'Grand Total')]//..//..//span")));
            String grandTotal = grandTotalElement.getText();
            assert grandTotal.contains("$") : "Grand total is not displayed correctly.";
            
            WebElement checkoutBtn = driver.findElement(By.xpath("//button[@title='Proceed to Checkout']"));
            checkoutBtn.click();
            
            WebElement checkaddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("billing[use_for_shipping]"))); // Replace with actual locator
            checkaddress.click();
            WebElement continuebtn = driver.findElement(By.xpath("//button[@title='Continue']"));
            continuebtn.click();

            WebElement billingAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billingAddress"))); // Replace with actual locator
            billingAddress.sendKeys("123 Example Street");

WebElement billingCity = driver.findElement(By.id("billingCity")); // Replace with actual locator
billingCity.sendKeys("Example City");

// Repeat for other billing information fields (State, Zip, Country, etc.)

WebElement shippingAddress = driver.findElement(By.id("shippingAddress")); // Replace with actual locator
shippingAddress.sendKeys("123 Example Street");

WebElement shippingCity = driver.findElement(By.id("shippingCity")); // Replace with actual locator
shippingCity.sendKeys("Example City");

// Repeat for other shipping information fields (State, Zip, Country, etc.)

// Assuming there's a "Continue" or "Next" button to proceed to the next step
WebElement continueButton = driver.findElement(By.id("continueButton")); // Replace with actual locator
continueButton.click();

// Step 7: Verify order is generated and note the order number
// Assuming there's a "Place Order" button
WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("placeOrderButton"))); // Replace with actual locator
placeOrderButton.click();

// After placing the order, assuming there's an element that contains the order number
WebElement orderConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Order Number')]"))); // Replace with actual locator
String orderNumber = orderConfirmation.getText();
assert orderNumber.contains("Order Number"): "Order number is not displayed correctly.";

System.out.println("Order placed successfully. Order number: " + orderNumber);
        } finally {
            // Clean up (Close browser)
            driver.quit();
        }
    }
}