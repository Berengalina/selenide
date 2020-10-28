package ru.netology.web;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    @Test
    void shouldRegisterCardDelivery() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Пермь");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("Белоусова Анна");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(text("Успешно!"), 15000).shouldBe(visible);
    }

    @Test
    void shouldNotFillName() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Пермь");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79266858100");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=name].input_invalid").shouldHave(exactText("Фамилия и имя Поле обязательно для заполнения"));
    }
}

