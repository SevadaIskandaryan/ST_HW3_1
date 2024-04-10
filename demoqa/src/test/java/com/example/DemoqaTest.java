package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.interactions.Actions;


public class DemoqaTest {
    public static final String WEBDRIVER = "webdriver.chrome.driver";
    public static final String DRIVER_PATH = "src/chromedriver.exe";
    public static final String baseURL = "https://demoqa.com/";
    WebDriver driver;

    @BeforeTest
    public void setUp(){
        System.setProperty(WEBDRIVER, DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @org.testng.annotations.Test
    public void textBox(){
        driver.get(baseURL+"text-box");
        WebElement textBox = driver.findElement(By.id("userName"));
        textBox.sendKeys("Myname");
        Assert.assertEquals(textBox.getAttribute("value"), "Myname", "Text entered is incorrect");
    }

    @org.testng.annotations.Test
    public void button(){
        driver.get(baseURL+"buttons");
        WebElement button = driver.findElement(By.id("doubleClickBtn"));
        Actions actions = new Actions(driver);
        actions.doubleClick(button).perform();
        WebElement output = driver.findElement(By.id("doubleClickMessage"));
        Assert.assertEquals(output.getText(), "You have done a double click", "something is incorrect");
    }

    @org.testng.annotations.Test
    public void link(){
        driver.get(baseURL+"links");
        WebElement linkElement = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/p[1]/a"));
        String link = linkElement.getAttribute("href");
        Assert.assertEquals(link, "https://demoqa.com/");
    }

    @org.testng.annotations.Test
    public void checkbox(){
        driver.get(baseURL+"checkbox");
        WebElement checkbox = driver.findElement(By.cssSelector("#tree-node-home"));
        checkbox.click();
        WebElement result = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/span[1]"));
        Assert.assertEquals(result.getText(), "You have selected :");
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}


