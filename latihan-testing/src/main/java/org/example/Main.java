package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver:chrome-driver","D:\\Works\\W1 - Satkomindo\\RSP\\Self Projects\\bsdp\\quality-engineer\\driver\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/locatorspractice/");

        driver.findElement(By.xpath("//input[@id='inputUsername']")).sendKeys("ikhsan");

        driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]/form/input[@name='inputPassword']")).sendKeys("password");

        driver.findElement(By.xpath("//*[@id=\"chkboxOne\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"chkboxTwo\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]/form/button")).click();

        Thread.sleep(1000);

        String result = driver.findElement(By.xpath("//p[@class='error']")).getText();
        String validationStr = "* Incorrect username or password";
        if (result.equals(validationStr)) {
            System.out.println("Validation error");
        }

        System.out.printf("Testing ends");
    }
}