package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class SmokeTests {





    @Test
    public void smokeTests2() throws InterruptedException {

          /*
            1. Открыть сайт https://calc.by/weight-and-calories/body-mass-index-calculator.html
            2. Выбрать "Способ укладки ламината" из выпадающего списка
            3. Ввести "Длина комнаты" = 500
            4. Ввести "Ширина комнаты" = 400
            5. Ввести "Длина панели ламината" = 2000
            6. Ввести Ширина панели ламината = 200
            7. Выбрать направление укладки = по ширине комнаты
            8. Нажать на кнопку Рассчитать
            9. Проверить результаты: Требуемое количество досок ламината: 53
            10. Проверить результаты: Количество упаковок ламината: 7
            11. Закрыть окно браузера
         */
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("drivers/chromedriver.exe").getFile());
        String absolutePath = file.getAbsolutePath();

        System.setProperty("webdriver.chrome.driver", absolutePath);

        //1.
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://calc.by/building-calculators/laminate.html");

        //2.

        Select layingMethodLaminate = new Select(driver.findElement(By.id("laying_method_laminate")));
        layingMethodLaminate.selectByVisibleText("с использованием отрезанного элемента");

        //3.
        WebElement roomLength = driver.findElement(By.id("ln_room_id"));
        roomLength.clear();
        roomLength.sendKeys("500");

        //4.
        WebElement roomWidth = driver.findElement(By.id("wd_room_id"));
        roomWidth.clear();
        roomWidth.sendKeys("400");

        //5.
        WebElement panelLength = driver.findElement(By.id("ln_lam_id"));
        panelLength.clear();
        panelLength.sendKeys("2000");

        //6.
        WebElement panelWidth = driver.findElement(By.id("wd_lam_id"));
        panelWidth.clear();
        panelWidth.sendKeys("200");

        //7.
        WebElement layingDirection = driver.findElement(By.id("direction-laminate-id1"));
        layingDirection.click();

        //8.
        WebElement calculate = driver.findElementByPartialLinkText("Рассчитать");
        calculate.click();
        Thread.sleep(3000);

        //9.
        WebElement resultBoard = driver.findElement(By.xpath("//*[@id=\"t3-content\"]/div[3]/article/section/div[2]/div[3]/div[2]/div[1]/span"));
        String expectedResultBoard = "53";
        String actualResultBoard = resultBoard.getText();
        Assert.assertEquals(actualResultBoard,expectedResultBoard);

        //10.
        WebElement resultPackaging = driver.findElement(By.xpath("//*[@id=\"t3-content\"]/div[3]/article/section/div[2]/div[3]/div[2]/div[2]/span"));
        String expectedResultPackaging = "7";
        String actualResultPackaging = resultPackaging.getText();
        Assert.assertEquals(actualResultPackaging,expectedResultPackaging);

        driver.quit();

    }
}
