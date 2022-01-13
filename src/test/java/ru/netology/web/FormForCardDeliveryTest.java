package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FormForCardDeliveryTest {

    @Test
    public void shouldSendForm() {
        Configuration.holdBrowserOpen = true;
        Configuration.headless = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").val("Пермь");
        $("div[class='popup__content'] span").click();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, +3);
        $("[data-test-id='date'] input[class='input__control']").doubleClick()
                .val(dateFormat.format(cal.getTime())).pressTab();
        $("[data-test-id='name'] input").val("Рогозин Илья");
        $(byName("phone")).setValue("+79054715844");
        $(byClassName("checkbox__box")).click();
        $(withText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Успешно!"), Duration.ofSeconds(15));
    }
}



