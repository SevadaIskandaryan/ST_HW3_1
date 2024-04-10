package com.example;

import java.util.List;

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
    public void login(){
        driver.get(baseURL+"login");
        WebElement loginName = driver.findElement(By.cssSelector("#userName"));
        loginName.sendKeys("admin");
        WebElement loginPassword = driver.findElement(By.cssSelector("#password"));
        loginPassword.sendKeys("admin");
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), baseURL+"login");
    }

    @org.testng.annotations.Test
    public void radioButton(){
        driver.get(baseURL+"radio-button");
        WebElement radioButton = driver.findElement(By.id("yesRadio"));
        radioButton.click();
        Assert.assertTrue(radioButton.isSelected());
    }

    @org.testng.annotations.Test
    public void books(){
        driver.get(baseURL+"books");
        WebElement rtTable = driver.findElement(By.className("rt-table"));
        List<WebElement> links = rtTable.findElements(By.tagName("a"));
        Boolean allLinksExist = true;
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty()) {
                allLinksExist = false;
            }
        }
        Assert.assertTrue(allLinksExist);
    }



    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}


