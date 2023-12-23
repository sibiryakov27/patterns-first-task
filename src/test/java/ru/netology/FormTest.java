package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.util.DataGenerator;
import ru.netology.util.data.CardOrderTestDataWithReplanningDate;

import java.text.SimpleDateFormat;
import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Test
    public void shouldSubmitTheFormAfterReplanning() {
        CardOrderTestDataWithReplanningDate data = DataGenerator.CardOrder.generateData("ru");

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue(data.getCity());

        SelenideElement dateInput =  form.$("[data-test-id=date] input");
        dateInput.sendKeys(Keys.CONTROL + "A");
        dateInput.sendKeys(Keys.BACK_SPACE);
        dateInput.setValue(dateFormat.format(data.getDate()));

        form.$("[data-test-id=name] input").setValue(data.getName());
        form.$("[data-test-id=phone] input").setValue(data.getPhoneNumber());
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text(dateFormat.format(data.getDate())));

        dateInput.sendKeys(Keys.CONTROL + "A");
        dateInput.sendKeys(Keys.BACK_SPACE);
        dateInput.setValue(dateFormat.format(data.getReplanningDate()));
        form.$(".button").click();
        $(".notification__content .button").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text(dateFormat.format(data.getReplanningDate())));
    }

}
