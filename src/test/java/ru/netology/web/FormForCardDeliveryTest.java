package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FormForCardDeliveryTest {

    @Test
    public void shouldSendForm() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").val("Пермь");
        $("div[class='popup__content'] span").click();
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input[class='input__control']").doubleClick()
                .val(planningDate).pressTab();
        $("[data-test-id='name'] input").val("Рогозин Илья");
        $(byName("phone")).setValue("+79054715844");
        $(byClassName("checkbox__box")).click();
        $(withText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Успешно!"), Duration.ofSeconds(15));
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


}



