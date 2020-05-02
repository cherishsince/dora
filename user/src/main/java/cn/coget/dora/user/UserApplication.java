package cn.coget.dora.user;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
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

    public static void main(String[] args) throws IOException {
//        System.setProperty("webdriver.gecko.driver", "/Users/sin/github/dora/user/src/main/resources/geckodriver");
//        WebDriver driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", "/Users/sin/github/dora/user/src/main/resources/chromedriver20191118");
//        WebDriver driver = new ChromeDriver();
//        System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        WebDriver driver = new ChromeDriver(options);
//        WebDriver driver = new ChromeDriver(new ChromeDriverService(
//                new File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"),
//                9222,
//                ImmutableList.<String>builder().build(),
//                ImmutableMap.<String, String>builder().put("debuggerAddress", "wx://127.0.0.1:9222").build()
//        ), options);
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
