package cn.coget.dora.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
public class GitHubApplication {

    // /Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --rete-debugging-port=9222

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/sin/github/dora/user/src/main/resources/chromedriver20191118");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://github.com/join?source=header-home/");
//            driver.get("https://baidu.com");

            String email = "903280167@qq.com";
            String username = email.replaceAll("@", "").replace(".", "");
            String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

            System.err.println("email: " + email);
            System.err.println("username: " + username);
            System.err.println("password: " + password);

            // 设置注册信息
            WebElement userNameEle = driver.findElement(By.xpath("//*[@id=\"user_login\"]"));
            userNameEle.clear();
            userNameEle.sendKeys(username);

            WebElement emailEle = driver.findElement(By.xpath("//*[@id=\"user_email\"]"));
            userNameEle.clear();
            emailEle.sendKeys(email);

            WebElement passwordEle = driver.findElement(By.xpath("//*[@id=\"user_password\"]"));
            userNameEle.clear();
            passwordEle.sendKeys(password);


            WebElement valEle = driver.findElement(By.xpath("//*[@id=\"FunCAPTCHA\"]"));
            valEle.click();

            // 点击按钮
            driver.findElement(By.xpath("//*[@id=\"signup_button\"]")).click();
        } finally {
            driver.quit();
        }
    }
}
