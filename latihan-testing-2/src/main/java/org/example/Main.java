package org.example;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver:chrome-driver","D:\\Works\\W1 - Satkomindo\\RSP\\Self Projects\\bsdp\\quality-engineer\\driver\\chromedriver-win64\\chromedriver.exe");
//        GreenCart();
//        DragNDrop();
        Tokopedia();
    }


    public static void GreenCart() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

        WebElement productsAll = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div"));
        List<WebElement> products = productsAll.findElements(By.xpath("//h4[@class='product-name']"));
        List<WebElement> buttons = productsAll.findElements(By.xpath("//div[@class='product-action']/button"));
        List<WebElement> increment = productsAll.findElements(By.xpath("//div[@class='stepper-input']/a[@class='increment']"));

        String[] targets = new String[]{"Brocolli", "Cauliflower", "Cucumber", "Tomato"};
        int[] targetsIdx = new int[targets.length];

        String[] allProducts = new String[products.size()];
        for (String target: targets) {
            int i = 0;

            for (WebElement product: products) {
                String productText = product.getText();
                if(productText.contains(" ")){
                    productText= productText.substring(0, productText.indexOf(" "));
                }

                if (productText.equals(target)) {
//                    product.findElement(By.xpath("//button[@class='']")).click();
                    buttons.get(i).click();
                    increment.get(i).click();
                    continue;
                }
                i++;
            }
        }
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[3]/a[4]/img")).click();

//        driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[3]/div[2]/div[2]/button")).click();
    }

    public static void DragNDrop() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/droppable/");

        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"content\"]/iframe")));

        WebElement fromElement = driver.findElement(By.xpath("//*[@id=\"draggable\"]"));

        WebElement toElement = driver.findElement(By.xpath("//*[@id=\"droppable\"]"));

        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.dragAndDrop(fromElement, toElement)
                .build();

        dragAndDrop.perform();

        driver.switchTo().defaultContent();
    }

    public static void Tokopedia() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://seller.tokopedia.com/edu");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1200));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement cookie = driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
        if (cookie != null) {
            cookie.click();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<WebElement> topicsAnchors = driver.findElements(By.xpath("//a[@class='css-1mbplqn']"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        System.out.println(topicsAnchors.size());

        // Keys.LEFT_CONTROL untuk new tab
        // Keys.SHIFT untuk new window
        for (WebElement topic : topicsAnchors) {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.LEFT_CONTROL)
                    .click(topic)
                    .keyUp(Keys.LEFT_CONTROL)
                    .build()
                    .perform();

//            ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
//            driver.switchTo().window(tab.get(1));
        }
    }
}