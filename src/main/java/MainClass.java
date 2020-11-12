import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainClass {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "/Users/eugeneburdukov/Documents/java/automatecourses/driver/chromedriver");

        WebDriver driver = new ChromeDriver();

        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.wikipedia.org/");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());;
        WebElement searchInput =
                driver.findElement
                        (By.xpath("/html[1]/body[1]/div[3]/form[1]/fieldset[1]/div[1]/input[1]"));
        searchInput.sendKeys("Software development process");
        searchInput.sendKeys(Keys.ENTER);
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile
                    (screenshot, new File("/Users/eugeneburdukov/Documents/screenshots/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
