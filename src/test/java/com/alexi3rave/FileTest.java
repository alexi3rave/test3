package com.alexi3rave;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
@Test
    @DisplayName("PDF download")
    void pdfFileDownloadTest () throws IOException {
    open("https://kub-24.ru/prajs-list-shablon-prajs-lista-2020-v-excel-word-pdf/");
    File pdf = $(By.xpath("//*[@id=\"content\"]/div/div/div[1]/ul[4]/li[3]/a")).download();
    PDF parsetpdf = new PDF(pdf);
    Assertions.assertEquals(1, parsetpdf.numberOfPages);
    Assertions.assertEquals ("HEBEL", parsetpdf.text);
}
    @Test
    @DisplayName("download xls file test")
    void downloadxlsFileTest() throws IOException {
        open("https://kub-24.ru/prajs-list-shablon-prajs-lista-2020-v-excel-word-pdf/");
        File xls = $(By.xpath("//*[@id=\"content\"]/div/div/div[1]/ul[4]/li[1]/a")).download();
        XLS parsetXls = new XLS(xls);
        boolean checkPassed = parsetXls.excel
                .getSheetAt(0)
                .getRow(4)
                .getCell(1)
                .getStringCellValue()
                .contains("1");
        assertTrue(checkPassed);
    }
    }
