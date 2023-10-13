package com.alexi3rave;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class JunitParametrTest {


    @Disabled
    @DisplayName("Поиск в яху слово Таганрог")
    @Tag("minor")
    @Test
    void JunitTest() {
        Selenide.open("https://yahoo.com");
        $("#ybar-sbq").setValue("Таганрог");
        $("button[type='submit']").click();
        $$("#main")
                .find(Condition.text("Таганрог"))
                .shouldBe(Condition.visible);
    }

    @Disabled
    @ValueSource(strings = {"Таганрог", "Taganrog"})
    @Tag("minor")
    @ParameterizedTest(name = "Поиск в yahoo слова {0}")
    void JunitParametrTest(String searchQuery) {
        Selenide.open("https://yahoo.com");
        $("#ybar-sbq").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("#main")
                .find(Condition.text(searchQuery))
                .shouldBe(Condition.visible);
    }

    @Disabled
    @ValueSource(strings = {
            "Таганрог_10 лучших достопримечательностей в Таганроге 2023",
            "Taganrog_Bay in the Sea of Azov"})
    @Tag("minor")
    @ParameterizedTest(name = "Кривой массив-поиск в yahoo слова {0}")
    void JunitValueParametrTest(String trashQuery) {
        String[] strings = trashQuery.split("_");
        Selenide.open("https://yahoo.com");
        $("#ybar-sbq").setValue(strings[0]);
        $("button[type='submit']").click();
        $$("#main")
                .find(Condition.text(strings[1]))
                .shouldBe(Condition.visible);
    }

    @Disabled
    @CsvSource(value = {
            "Таганрог| 10 лучших достопримечательностей в Таганроге 2023",
            "Taganrog| Bay in the Sea of Azov"},
            delimiter = '|')
    @Tag("minor")
    @ParameterizedTest(name = "Поиск в yahoo слова {0} и проверка вывода {1}")
    void JunitCsvParametrTest(String searchQuery, String expectedResult) {
        Selenide.open("https://yahoo.com");
        $("#ybar-sbq").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("#main")
                .find(Condition.text(expectedResult))
                .shouldBe(Condition.visible);

    }

    static Stream<Arguments> JunitMethodParametrTest() {
        return Stream.of(
                Arguments.of("Таганрог", List.of("10 лучших достопримечательностей в Таганроге 2023")),
                Arguments.of("Taganrog", List.of("Bay in the Sea of Azov"))
        );
    }

    @Disabled
    @MethodSource
    @Tag("minor")
    @ParameterizedTest(name = "Поиск в yahoo слова {0} и проверка вывода {1}")
    void JunitMethodParametrTest(String searchQuery, List<String> expectedResult) {
        Selenide.open("https://yahoo.com");
        $("#ybar-sbq").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("#main")
                .find(Condition.text(expectedResult.get(0)))
                .shouldBe(Condition.visible);

    }

    @Disabled
    @EnumSource(SearchQuery.class)
    @Tag("minor")
    @ParameterizedTest(name = "Поиск в yahoo слова {0} и проверка вывода {1}")
    void JunitCsvParametrTest(SearchQuery searchQuery) {
        Selenide.open("https://yahoo.com");
        $("#ybar-sbq").setValue(searchQuery.name());
        $("button[type='submit']").click();
        $$("#main")
                .find(Condition.text(searchQuery.name()))
                .shouldBe(Condition.visible);
    }

    static List<String> arguments = List.of("10 лучших достопримечательностей в Таганроге 2023", "Bay in the Sea of Azov");

    static Stream<Arguments> JunitStaticArgParametrTest() {
        return Stream.of(
                Arguments.of("Таганрог", arguments.get(0)),
                Arguments.of("Taganrog", arguments.get(1)));
    }

    @Tag("minor")
    @ParameterizedTest(name = "Поиск в yahoo слова {0} и проверка вывода {1}")
    @MethodSource
    void JunitStaticArgParametrTest(String searchQuery, String expectedResult) {
        Selenide.open("https://yahoo.com");
        $("#ybar-sbq").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("#main")
                .find(Condition.text(String.valueOf(expectedResult)))
                .shouldBe(Condition.visible);
    }
}



