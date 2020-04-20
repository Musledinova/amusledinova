package pages;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;


public class BasicPage {

    public void selectPrice(String maxPrice) {
        $(name("Цена до")).setValue(maxPrice);
    }

    public void selectScreenSize(String fromScreenSize, String toScreenSize) {
        $(name("Диагональ экрана (точно) от")).setValue(fromScreenSize);
        $(name("Диагональ экрана (точно) до")).setValue(toScreenSize);
    }

    public void selectRAM(String ram) {
        $(xpath("//span[text()=\"" + ram + "\"]")).click();
    }

}
