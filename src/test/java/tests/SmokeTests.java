package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;

public class SmokeTests {





    @Test
    public void smokeTests2(){

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
        driver.get("https://calc.by/weight-and-calories/body-mass-index-calculator.html");

        driver.quit();

    }
}
