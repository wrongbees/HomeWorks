package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SmokeTest_masterskayapola {

    @Test
    public void smokeTest() throws InterruptedException {
/*        1.Открыть сайт https://masterskayapola.ru/kalkulyator/laminata.html

          2. Ввести параметры для расчета
          2.1 Ввести в поле "Длина помещения" = 6
          2.2 Ввести в поле "Ширина помещения" = 4
          2.3 Ввести в поле "Длина:" = 1200
          2.4 Ввести в поле "Ширина:" = 150
          2.5 Ввести в поле "В упаковке:" = 20
          2.6 Ввести в поле "Цена:" = 555
          2.7 Выбрать в поле "Направление укладки" = По длине комнаты
          2.8 Ввести в поле "Смещение рядов" = 350
          2.9 Ввести в поле "Отступ от стены" = 20

          3.Нажать на кнопку ‘Рассчитать’

          4. Проверить полученные значения
//        4.1 Площадь укладки = 23.60
//        4.2 Кол-во панелей = 136
//        4.2 Кол-во упаковок = 7
//        4.3 Стоимость 13986
//        4.4 Остатки 4
//        4.5 Отрезки 9

          5.Закрыть окно браузера

 */

        //1.
        ClassLoader loader = getClass().getClassLoader();
        File file = new File(loader.getResource("drivers/chromedriver.exe").getFile());
        String absolutePath = file.getAbsolutePath();

        System.setProperty("webdriver.chrome.driver", absolutePath);

        ChromeDriver driver = new ChromeDriver();
        WebDriver.Timeouts wait = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://masterskayapola.ru/kalkulyator/laminata.html");

//        2.1 Ввести в поле "Длина помещения" = 6
        WebElement roomLength = driver.findElement(By.name("calc_roomwidth"));

        roomLength.clear();
        for (int i = 0; i < 5; i++)
            roomLength.sendKeys(Keys.BACK_SPACE);

        roomLength.sendKeys("6");
       // roomLength.click();

//        2.2 Ввести в поле "Ширина помещения" = 4
        WebElement roomWidth = driver.findElement(By.name("calc_roomheight"));

        roomWidth.clear();
        for (int i = 0; i < 5; i++)
            roomWidth.sendKeys(Keys.BACK_SPACE);
        roomWidth.sendKeys("4");
       // roomWidth.click();

//        2.3 Ввести в поле "Длина:" = 1200
        WebElement width = driver.findElement(By.name("calc_lamwidth"));

        width.clear();
        for (int i = 0; i < 4; i++)
            width.sendKeys(Keys.BACK_SPACE);

        width.sendKeys("1200");
       // width.click();



//        2.4 Ввести в поле "Ширина:" = 150
        WebElement length = driver.findElement(By.name("calc_lamheight"));

        length.clear();
        for (int i = 0; i < 2; i++)
            length.sendKeys(Keys.BACK_SPACE);

        length.sendKeys("150");
        //length.click();

//        2.5 Ввести в поле "В упаковке:" = 20
        WebElement inPackage = driver.findElement(By.name("calc_inpack"));
        inPackage.clear();
        inPackage.sendKeys(Keys.BACK_SPACE);
        inPackage.sendKeys("20");
       // inPackage.click();

//        2.6 Ввести в поле "Цена:" = 555
        WebElement price = driver.findElement(By.name("calc_price"));
        price.clear();

        for (int i = 0; i < 2; i++)
            price.sendKeys(Keys.BACK_SPACE);

        price.sendKeys("555");
       // price.click();

//        2.7 Выбрать в поле "Направление укладки" = По длине комнаты
        Select layingDirect = new Select(driver.findElement(By.name("calc_direct")));
        layingDirect.selectByVisibleText("По длине комнаты");

//        2.8 Ввести в поле "Смещение рядов" = 350
        WebElement bias = driver.findElement(By.name("calc_bias"));
        bias.clear();
        bias.sendKeys(Keys.BACK_SPACE);
        bias.sendKeys("350");

//        2.9 Ввести в поле "Отступ от стены" = 20
        WebElement walldist = driver.findElement(By.name("calc_walldist"));
        walldist.clear();
        walldist.sendKeys(Keys.BACK_SPACE);
        walldist.sendKeys("20");

//        3.Нажать на кнопку ‘Рассчитать’
        WebElement calculate = driver.findElement(By.xpath("/html/body/div[2]/div/div/article/div/div[1]/div/div[2]/div[1]/div[2]/div[1]/div/form/div/div[3]/div[7]/div/input"));
        calculate.click();
        Thread.sleep(5000);
//
//        4. Проверить полученные значения
//        4.1 Площадь укладки = 23.60
//        4.2 Кол-во панелей = 136
//        4.2 Кол-во упаковок = 7
//        4.3 Стоимость 13986
//        4.4 Остатки 4
//        4.5 Отрезки 9
        WebElement s_lam = driver.findElement(By.id("s_lam"));
        WebElement l_count = driver.findElement(By.id("l_count"));
        WebElement l_packs = driver.findElement(By.id("l_packs"));
        WebElement l_price = driver.findElement(By.id("l_price"));
        WebElement l_over = driver.findElement(By.id("l_over"));
        WebElement l_trash = driver.findElement(By.id("l_trash"));

        Assert.assertEquals(s_lam.getText(), "23.60 м2.");
        Assert.assertEquals(l_count.getText(), "136 шт.");
        Assert.assertEquals(l_packs.getText(), "7 шт.");
        Assert.assertEquals(l_price.getText(), "13986 руб.");
        Assert.assertEquals(l_over.getText(), "4 шт.");
        Assert.assertEquals(l_trash.getText(), "9 шт.");
//
//        5.Закрыть окно браузера
        driver.quit();
    }

}
