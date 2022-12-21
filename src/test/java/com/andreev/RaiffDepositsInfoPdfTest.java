package com.andreev;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.andreev.Configurations.PATH_TO_DOWNLOAD;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Nested
@DisplayName("Проверка pdf с условиями вклада на сайте Райффайзен Банка")
public class RaiffDepositsInfoPdfTest {

    @Test
    @DisplayName("Есть текст Вклад «Фиксированный» в скаченном файле с условиями вклада")
    void checkDownloadDepositFile() throws Exception {
        com.codeborne.selenide.Configuration.downloadsFolder = PATH_TO_DOWNLOAD;
        open("https://www.raiffeisen.ru/retail/deposit_investing/deposit/fixed/");
        $(byText("Условия")).click();
        File pdfFileDownload = $(byText("Условия")).parent().download();
        PDF parsedPDF = new PDF(pdfFileDownload);
        Assertions.assertTrue((parsedPDF.text).contains("Вклад «Фиксированный»"));
    }
}
