package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;

/**
 * Created by xuch on 10/9/16.
 */
public class HelloSelenium
{
    public static void main(String[] args)
    {
        /*
        * /Applications/Firefox.app/Contents/MacOS/firefox    /Users/xuch/Downloads/geckodriver
        * /Applications/Safari.app/Contents/MacOS/Safari
        * /Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome
        * /Applications/Google Chrome.app/Contents/MacOS/Google Chrome
        *
        * */
        System.setProperty("webdriver.firefox.marionette", "/Users/xuch/Downloads/geckodriver");
        WebDriver driver = new FirefoxDriver();
        String target = "https://www.amazon.co.uk/Sony-PlayStation-Infinite-Warfare-Bundle/dp/B01MF987C8/ref=sr_1_2?s=videogames&ie=UTF8&qid=1480300117&sr=1-2&keywords=PS4";//"https://www.baidu.com";
        driver.get(target);
        //driver.manage().window().maximize();

        /*
        WebElement txtBox = driver.findElement(By.name("wd"));
        txtBox.sendKeys("Selenium");
        WebElement button = driver.findElement(By.id("su"));
        button.click();
        */

        String contained = "in stock";
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        while (true) {
            try {
                WebElement availability = driver.findElement(By.id("availability0000"));
                String text = availability.getText();
                if (text.contains(contained)) executor.executeScript("alert('Congratulations, you got available stocks.')");
                break;
            } catch (Exception e) {
                try {
                    executor.executeScript("alert('No available stocks, and will refresh again.')");
                    Thread.sleep(3000);
                    driver.switchTo().alert().accept();
                    driver.navigate().refresh();
                    continue;
                } catch (InterruptedException ie) {
                    executor.executeScript("alert('Auto scipt was interrupted by unkown reason, and no element found.')");
                    break;
                }
            }
        }
        driver.close();
    }

    private void pengQiuYan()
    {
        String url = "http://ba.amac.org.cn/login.action";
        String loginName = "sac121001";
        String password = "ctzg87821423";
        String checkCode;
        String loginNameInput = "j_username";
        String passwordInput = "j_password";
        String checkInput = "textfield3";
        System.setProperty("webdriver.firefox.marionette", "/Users/xuch/Downloads/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().maximize();

        WebElement login = driver.findElement(By.name(loginNameInput));
        WebElement pwd = driver.findElement(By.name(passwordInput));
        WebElement check = driver.findElement(By.name(checkInput));
        //WebElement submit = driver.findElement(By,)
        login.sendKeys(loginName);
        pwd.sendKeys(password);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        checkCode = String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l);
                        check.sendKeys(checkCode);

                    }
                }
            }
        }


        WebElement button = driver.findElement(By.id("su"));
        button.click();

    }
}
