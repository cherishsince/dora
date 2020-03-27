package cn.coget.dora.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

/**
 * https://github.com/mozilla/geckodriver/releases
 * http://chromedriver.storage.googleapis.com/index.html
 *
 * author: sin
 * time: 2020/3/21 8:05 下午
 */
public class UserApplication {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/Users/sin/github/dora/user/src/main/resources/geckodriver");
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("https://github.com/join?source=header-home/");

            String email = "903280167@qq.com";
            String username = email.replaceAll("@", "").replace(".", "");
            String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

            System.err.println("email: " + email);
            System.err.println("username: " + username);
            System.err.println("password: " + password);

            // 设置注册信息
            WebElement userNameEle = driver.findElement(By.xpath("//*[@id=\"user_login\"]"));
            userNameEle.sendKeys(username);

            WebElement emailEle = driver.findElement(By.xpath("//*[@id=\"user_email\"]"));
            emailEle.sendKeys(email);

            WebElement passwordEle = driver.findElement(By.xpath("//*[@id=\"user_password\"]"));
            passwordEle.sendKeys(password);

            // 点击按钮
            driver.findElement(By.xpath("//*[@id=\"signup_button\"]")).click();
        } finally {
            driver.quit();
        }
    }
}
