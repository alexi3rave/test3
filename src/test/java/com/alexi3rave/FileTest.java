package com.alexi3rave;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FileTest {

    @Test
    @DisplayName("Upload by absolute path...")
void uploadFileTest() {
        Selenide.open("https://the-internet.herokuapp.com/upload");
        File exampleFile = new File("C:\\Users\\user\\IdeaProjects\\test3\\src\\test\\resources\\example.txt");
        $("input[type='file']").uploadFile(exampleFile);
    $("#file-submit").submit();
    $("#uploaded-files").shouldHave(Condition.text("example.txt"));
    }
    @Test
    @DisplayName("Upload by classpath...")
    void uploadFileTestyClasspath() {
        Selenide.open("https://the-internet.herokuapp.com/upload");
                $("input[type='file']").uploadFromClasspath("example.txt");
        $("#file-submit").submit();
        $("#uploaded-files").shouldHave(Condition.text("example.txt"));
    }
@Test
@DisplayName("download simple file test")
void downloadSimpleFileTest() throws FileNotFoundException {
       open("https://kub-24.ru/prajs-list-shablon-prajs-lista-2020-v-excel-word-pdf/");
       File download = $(By.xpath("//*[@id=\"content\"]/div/div/div[1]/ul[4]/li[1]/a")).download();
       System.out.println(download.getAbsolutePath());
}

    }
