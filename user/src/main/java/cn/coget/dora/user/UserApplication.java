package cn.coget.dora.user;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

/**
 * https://github.com/mozilla/geckodriver/releases
 * http://chromedriver.storage.googleapis.com/index.html
 * <p>
 * author: sin
 * time: 2020/3/21 8:05 下午
 */
public class UserApplication {


    public static void main(String[] args) throws IOException {
//        System.setProperty("webdriver.gecko.driver", "/Users/sin/github/dora/user/src/main/resources/geckodriver");

        System.setProperty("webdriver.chrome.driver", "/Users/sin/github/dora/user/src/main/resources/chromedriver20191118");
//        System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(Boolean.TRUE);
//        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        WebDriver driver = new ChromeDriver(options);
//        WebDriver driver = new FirefoxDriver(options);


        // 窗口大小
        int winH = 600;
        int winW = 400;
        driver.manage().window().setSize(new Dimension(winW, winH));

//        WebDriver driver = new ChromeDriver(options);
//        WebDriver driver = new ChromeDriver(new ChromeDriverService(
//                new File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"),
//                9222,
//                ImmutableList.<String>builder().build(),
//                ImmutableMap.<String, String>builder().put("debuggerAddress", "wx://127.0.0.1:9222").build()
//        ), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("https://github.com/join?source=header-home/");

            String email = String.valueOf(System.currentTimeMillis() / 1000) + "@qq.com";
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

            // 点击按钮  //*[@id="FunCAPTCHA"]
            WebElement submitElement = driver.findElement(By.xpath("//*[@id=\"signup_button\"]"));


            {
//
//                Actions actions = new Actions(driver);
//                int x = submitButton.getLocation().getX();
//                int y = submitButton.getLocation().getY();
//                actions.clickAndHold(submitButton)
//                        .moveToElement(submitButton, 100, 0)
////                        .release(submitButton)
//                        .perform();
//                System.err.println("移动了~");
//                System.err.println("移动了~");
//                try {
//                    Thread.sleep(1000L * 3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

            {
                Thread.sleep(1000L * 10);
                WebElement iframe1 = driver.findElement(By.xpath("/html/body/div[4]/main/div/div[2]/div/form/div[1]/div[3]/iframe"));
                int f1H = iframe1.getSize().getHeight();
                int f1W = iframe1.getSize().getWidth();
                int f1X = iframe1.getLocation().getX();
                int f1Y = iframe1.getLocation().getY();
                System.err.println(iframe1.getLocation());
                System.err.println(iframe1.getSize());
                WebDriver f1 = driver.switchTo().frame(iframe1);
                WebElement iframe2 = f1.findElement(By.xpath("//*[@id=\"fc-iframe-wrap\"]"));
                WebDriver f2 = f1.switchTo().frame(iframe2);
                WebElement iframe3 = f2.findElement(By.xpath("//*[@id=\"CaptchaFrame\"]"));
                WebDriver f3 = f2.switchTo().frame(iframe3);

                WebElement actionElement = f3.findElement(By.xpath("//*[@id=\"FunCAPTCHA\"]"));
                // 新建一个action
                Actions action = new Actions(f3);
                // 鼠标左键单击
                action.moveToElement(actionElement, 0, 20).click().perform();

                System.err.println("移动了~");
                System.err.println("移动了~");
                System.err.println("移动了~");
                System.err.println("移动了~");

                Thread.sleep(1000L * 3);

                String tmpPath = "/Users/sin/github/dora/user/src/main/resources/tmp/";


                // 截屏操作

//                // JS 脚本执行器
//                JavascriptExecutor executor = ((JavascriptExecutor) driver);
//                // 滚动元素到可视区域
//                // 并设置误差 10px
//                executor.executeScript("window.scrollTo(arguments[0], arguments[1])", f1X - 10, f1Y - 10);

                ImageUtils imageUtils = new ImageUtils();
//                String tmp1Name = tmpPath + System.currentTimeMillis() + "_222.png";
//                IOUtils.write(FileUtils.readFileToByteArray(screenshot), new FileOutputStream(new File(tmp1Name)));

                // Crop the entire page screenshot to get only element screenshot
//                BufferedImage eleScreenshot = fullImg.getSubimage(fullImg.getWidth() / 2 - 150, fullImg.getHeight() / 2 - 10, 300, 200);

//                ImageUtils imageUtils = new ImageUtils();
//                BufferedImage bufferedImage = imageUtils.cropImage(fullImg, 200, 200, 200, 200);

//                String tmp = tmpPath + File.separator + System.currentTimeMillis() + ".png";
//                ImageIO.write(eleScreenshot, "png", new File(tmp));
                action.moveToElement(actionElement, -80, 20).click().perform();
                Thread.sleep(500);
                for (int i = 0; i < 10; i++) {
                    BufferedImage fullImg = getScreenshot(f3);
                    if (!imageUtils.start2(fullImg)) {
                        action.moveToElement(actionElement, -80, 20).click().perform();
                        Thread.sleep(500);
                    } else {
                        break;
                    }
                }

                System.err.println("成功了！");
                System.err.println("成功了！");
                System.err.println("成功了！");

                action.moveToElement(actionElement, 0, 50).click().perform();
                action.moveToElement(actionElement, 0, 60).click().perform();
                action.moveToElement(actionElement, 0, 70).click().perform();
                action.moveToElement(actionElement, 0, 80).click().perform();
                action.moveToElement(actionElement, 0, 90).click().perform();

                Thread.sleep(500);

                // 点击按钮  //*[@id="FunCAPTCHA"]
                submitElement.click();



                {
//                    int rotateSize = imageUtils.start(eleScreenshot, 0);
//                    System.err.println("isRotate: " + rotateSize);
//                    rotateSize = rotateSize * 2;
//                    System.err.println("isRotate: " + rotateSize);
//
//                    for (int i = 0; i < rotateSize; i++) {
//                        action.moveToElement(actionElement, -80, 20).click().perform();
//                        Thread.sleep(500);
//                    }
                }

//
//                action.moveToElement(actionElement,
//                        (-(actionElement.getSize().width / 2)) + 30,
//                        actionElement.getSize().height / 2 + 20
//                ).click().perform();


//                String tmpPath = "/Users/sin/github/dora/user/src/main/resources/tmp/";


//                int x = actionElement.getLocation().getX();
//                int y = actionElement.getLocation().getY();
//                actions.clickAndHold(actionElement)
//                        .moveToElement(actionElement)
//                        .release(actionElement)
//                        .perform();
//                System.err.println("移动了~");
//                try {
//                    Thread.sleep(1000L * 3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                actions.release();//释放鼠标

                //点击前往查看
//                f3.findElement(By.linkText("验证")).click();
//                f3.findElement(By.xpath("//*[@id=\"FunCAPTCHA\"]")).click();
//                f3.findElement(By.xpath("//*[@id=\"FunCAPTCHA\"]")).click();


            }
//            WebDriver f1 = driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[4]/main/div/div[2]/div/form/div/div[3]/iframe")));
//            WebDriver f2 = f1.switchTo().frame(f1.findElement(By.xpath("//*[@id=\"fc-iframe-wrap\"]")));
////            f2.findElement(By.xpath("/html/body/div[4]/span/a[3]/span")).click();
//
//            WebDriver f3 = f1.switchTo().frame(f1.findElement(By.xpath("//*[@id=\"CaptchaFrame\"]")));
//            WebElement canvasElement = f3.findElement(By.xpath("//*[@id=\"FunCAPTCHA\"]"));
//            Object imageURL = ((JavascriptExecutor) f3).executeScript("arguments[0].toDataURL('image/png');", canvasElement);
//            Object a = imageURL;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(1000L * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

    public static BufferedImage getScreenshot(WebDriver webDriver) throws IOException {
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);
        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot = fullImg.getSubimage(fullImg.getWidth() / 2 - 150, fullImg.getHeight() / 2 - 10, 300, 200);

//        String tmp1Name = tmpPath + System.currentTimeMillis() + "_222.png";
//        IOUtils.write(FileUtils.readFileToByteArray(screenshot), new FileOutputStream(new File(tmp1Name)));

        return eleScreenshot;
    }
}
