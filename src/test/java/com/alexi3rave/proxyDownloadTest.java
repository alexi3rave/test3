package com.alexi3rave;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class proxyDownloadTest {
@AfterEach
void closeBrowser(){
    Selenide.clearBrowserCookies();
    Selenide.closeWebDriver();
   }

               @Test
        @DisplayName("download file test")
        void ProxyDownloadFileTest () throws FileNotFoundException {
                   Configuration.proxyEnabled = true;
                   Configuration.fileDownload = PROXY;
                   open("https://kub-24.ru/prajs-list-shablon-prajs-lista-2020-v-excel-word-pdf/");
            File download = $(By.xpath("//*[@id=\"content\"]/div/div/div[1]/ul[4]/li[1]/a")).download();
            System.out.println(download.getAbsolutePath());
                                     }


    @Test
    @DisplayName("download Git file test")
    void ProxyDownloadGitFileTest () throws FileNotFoundException {
        Configuration.proxyEnabled = true;
        Configuration.fileDownload = PROXY;

        open("https://github.com/selenide/selenide/blob/main/README.md#selenide--ui-testing-framework-powered-by-selenium-webdriver");
        File download = $(By.xpath("//*[@aria-label=\"Download raw content\"]")).download();
        System.out.println(download.getAbsolutePath());
    }

}
