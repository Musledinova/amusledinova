package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import pages.BasicPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;


public class BasicSteps {

    BasicPage basicPage = new BasicPage();

    @Дано("^вошли на страницу со смартфонами$")
    public void goToPageWithSmartPhones() {
        $(xpath("//span[text()='Электроника']")).click();
        $(xpath("//a[text()='Смартфоны']")).click();
    }

    @Тогда("^выбрана стоимость до \"([^\"]*)\"$")
    public void selectPrice(String maxPrice) {
        basicPage.selectPrice(maxPrice);
    }

    @И("^выбрана диагональ от \"([^\"]*)\" до \"([^\"]*)\"$")
    public void selectScreenSize(String fromScreenSize, String toScreenSize) {
        basicPage.selectScreenSize(fromScreenSize, toScreenSize);
    }

    @И("^выбрана оперативная память \"([^\"]*)\"$")
    public void selectRAM(String ram) {
        basicPage.selectRAM(ram);
    }

    @И("^выполнена сортировка \"([^\"]*)\"$")
    public void sortBy(String sortBy) {
        $(By.xpath("//a[text()=\"" + sortBy + "\"]")).click();
        Selenide.sleep(500);
    }

    @И("^выбрали первый смартфон из списка$")
    public void checkSomeElementFromList() {
        $(By.cssSelector(".n-filter-applied-results__content :first-child.n-snippet-cell2 .n-snippet-cell2__title")).click();
    }

    @И("^в списке выбран телефон с рейтингом по отзывам не ниже \"([^\"]*)\", с количеством отзывов больше \"([^\"]*)\", не фирмы \"([^\"]*)\"$")
    public void selectTelephoneWithParametres(String ratingReviews, String numberOfReviews, String manufacturerName) {

        double ratingReviewsParse = Double.parseDouble(ratingReviews);
        int numberOfReviewsParse = Integer.parseInt(numberOfReviews);
        String manufactureNameLowerCase = manufacturerName.toLowerCase();

        List<SelenideElement> listGoodsFromPage = new ArrayList<>();
        for (int i = 0; i < 48; i++) {
            int count = i + 1;
            listGoodsFromPage.add($(By.xpath("(//div[@class='n-snippet-cell2__header'])[" + count + "]/h3")));
        }

        for (int i = 0; i < listGoodsFromPage.size(); i++) {
            int count = i + 1;

            boolean ratingElementExists = $(By.xpath("((//div[@class='n-snippet-cell2__body'])[" + count + "]/a/div/div)[1]")).exists();
            boolean reviewsElementExists = $(By.xpath("(//div[@class='n-snippet-cell2__body'])[" + count + "]//a//span")).exists();
            boolean telephoneNameExists = $(By.xpath("(//div[@class='n-snippet-cell2__brand-name'])[" + count + "]")).exists();

            if (ratingElementExists && reviewsElementExists && telephoneNameExists) {

                String ratingElement = $(By.xpath("((//div[@class='n-snippet-cell2__body'])[" + count + "]/a/div/div)[1]")).getText();
                double rating = Double.parseDouble(ratingElement);

                String reviewsElement = $(By.xpath("(//div[@class='n-snippet-cell2__body'])[" + count + "]//a//span")).getText();
                String[] reviewsToInt = reviewsElement.split(" ");
                int reviews = Integer.parseInt(reviewsToInt[0]);

                String telephoneName = $(By.xpath("(//div[@class='n-snippet-cell2__brand-name'])[" + count + "]")).getText().toLowerCase();

                if (!telephoneName.contains(manufactureNameLowerCase) && rating >= ratingReviewsParse && reviews >= numberOfReviewsParse) {
                    SelenideElement clickToMe = listGoodsFromPage.get(i);
                    clickToMe.click();
                }
            }
        }
    }

    @Тогда("^выполнено переключение на вкладку с телефоном$")
    public void goToNextTab() {
        Selenide.switchTo().window(1);
        Selenide.sleep(5000);
    }

    @Тогда("^выполнено нажатине на кнопку в магазин$")
    public void clickOnButtonToStore() {
        $(By.cssSelector(".RfMzL7i6aO")).click();
        Selenide.sleep(5000);
    }

}
