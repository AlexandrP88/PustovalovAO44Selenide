package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.DurationFormat;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FormForCardDeliveryTest {

    @Test
    public <gradlew> void shouldSendForm() {
        Configuration.holdBrowserOpen = true; Configuration.headless = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").val("Пермь");
        $("div[class='popup__content'] span").click();
        $("[data-test-id='name'] input").val("Рогозин Илья");
        $(byName("phone")).setValue("+79054715844");
        $(byClassName("checkbox__box")).click();
        $(withText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Успешно!"), Duration.ofSeconds(15));
    }
}



