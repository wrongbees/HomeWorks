package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SmokeTest_masterskayapola {

    static List<String[]> data;

    static {
        SmokeTest_masterskayapola smokeTest_m = new SmokeTest_masterskayapola();

        data = new ArrayList<String[]>();

        try {
            smokeTest_m.dataListCreator(data);                 // создаём лист
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void smokeTest() throws InterruptedException {
//       1.Открыть сайт https://masterskayapola.ru/kalkulyator/laminata.html

        //1.
        ClassLoader loader = getClass().getClassLoader();
        File file = new File(loader.getResource("drivers/chromedriver.exe").getFile());
        String absolutePath = file.getAbsolutePath();

        System.setProperty("webdriver.chrome.driver", absolutePath);

        ChromeDriver driver = new ChromeDriver();
        WebDriver.Timeouts wait = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://masterskayapola.ru/kalkulyator/laminata.html");

        for (String[] volume : data) {

//        2.1 Ввести в поле "Длина помещения" = 6
            WebElement roomLength = driver.findElement(By.name("calc_roomwidth"));
            setVolume(roomLength, volume[0]);

//        2.2 Ввести в поле "Ширина помещения" = 4
            WebElement roomWidth = driver.findElement(By.name("calc_roomheight"));
            setVolume(roomWidth, volume[1]);

//        2.3 Ввести в поле "Длина:" = 1200
            WebElement width = driver.findElement(By.name("calc_lamwidth"));
            setVolume(width, volume[2]);

//        2.4 Ввести в поле "Ширина:" = 150
            WebElement length = driver.findElement(By.name("calc_lamheight"));
            setVolume(length, volume[3]);

//        2.5 Ввести в поле "В упаковке:" = 20
            WebElement inPackage = driver.findElement(By.name("calc_inpack"));
            setVolume(inPackage, volume[4]);

//        2.6 Ввести в поле "Цена:" = 555
            WebElement price = driver.findElement(By.name("calc_price"));
            setVolume(price, volume[5]);

//        2.7 Выбрать в поле "Направление укладки" = По длине комнаты
            Select layingDirect = new Select(driver.findElement(By.name("calc_direct")));
            layingDirect.selectByVisibleText(volume[6]);

//        2.8 Ввести в поле "Смещение рядов" = 350
            WebElement bias = driver.findElement(By.name("calc_bias"));
            setVolume(bias, volume[7]);

//        2.9 Ввести в поле "Отступ от стены" = 20
            WebElement walldist = driver.findElement(By.name("calc_walldist"));
            setVolume(walldist, volume[8]);

//        3.Нажать на кнопку ‘Рассчитать’
            WebElement calculate = driver.findElement(By.xpath("/html/body/div[2]/div/div/article/div/div[1]/div/div[2]/div[1]/div[2]/div[1]/div/form/div/div[3]/div[7]/div/input"));
            calculate.click();

//        4. Проверить полученные значения
//        4.1 Площадь укладки = 23.60
//        4.2 Кол-во панелей = 136
//        4.2 Кол-во упаковок = 7
//        4.3 Стоимость 13986
//        4.4 Остатки 4
//        4.5 Отрезки 9

            List<WebElement> elements = new ArrayList<WebElement>();
            elements.add(driver.findElement(By.id("s_lam")));
            elements.add(driver.findElement(By.id("l_count")));
            elements.add(driver.findElement(By.id("l_packs")));
            elements.add(driver.findElement(By.id("l_price")));
            elements.add(driver.findElement(By.id("l_over")));
            elements.add(driver.findElement(By.id("l_trash")));

            int i = 0;
            for (WebElement item : elements) {
                try {

                    Assert.assertEquals(item.getText(), volume[9 + (i++)]);

                } catch (AssertionError assertionError) {
                    System.out.println("***********В строчке Длина помещения " + volume[0] + " произошла ошибка!************");
                    assertionError.printStackTrace();
                }

            }
        }

//        5.Закрыть окно браузера
        driver.quit();
    }

    private void dataListCreator(List<String[]> list) throws IOException {
        ClassLoader loader = getClass().getClassLoader();
        File file = new File(loader.getResource("data_for_check/masterskayapola").getFile());

        BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));

        if (reader.ready()) //пропускаем первую строку текстового файла
            reader.readLine();

        while (reader.ready()) {

            String[] data = reader.readLine().split(";");
            for (int i = 0; i < data.length; i++) {
                String trim = data[i].trim();// обрезаем пробелы
                data[i] = trim;
            }
            list.add(data);
        }
        reader.close();
    }

    private static void setVolume(WebElement element, String volume) {
        element.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        element.sendKeys(volume);
    }
}
