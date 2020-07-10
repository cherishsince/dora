package cn.coget.dora.user.webdriver;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.List;

/**
 * https://github.com/mozilla/geckodriver/releases
 * http://chromedriver.storage.googleapis.com/index.html
 *
 * author: sin
 * time: 2020/3/21 8:05 下午
 */
public class OpenCurrentChrome {

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/Users/sin/github/dora/user/src/main/resources/chromedriver20191118");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        WebDriver driver = new ChromeDriver(options);

        List<String> windowsIds = Lists.newArrayList();
        for (String s : driver.getWindowHandles()) {
            System.err.println(s);
            String title = driver.switchTo().window(s).getTitle();
            System.err.println(title);
            if ("阿里云 | 账户中心".equals(title)) {
                windowsIds.add(s);
            }
        }

        try {
            for (int i = 1132; i <= 9999; i++) {
                System.err.println("验证码: " + i);
                driver.findElement(By.xpath("//*[@id=\"verifyCode\"]")).clear();
                driver.findElement(By.xpath("//*[@id=\"verifyCode\"]")).sendKeys(String.valueOf(i));
                driver.findElement(By.xpath("//*[@id=\"verifyCode\"]")).sendKeys(String.valueOf(i));
                Thread.sleep(100L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
