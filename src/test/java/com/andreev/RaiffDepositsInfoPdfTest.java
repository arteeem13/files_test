package com.andreev;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.apache.commons.io.FileUtils.deleteDirectory;

@Nested
@DisplayName("Проверка pdf с условиями вклада на сайте Райффайзен Банка")
public class RaiffDepositsInfoPdfTest {
    private static final String PATH_TO_DOWNLOAD = "downloads";
    private static final String URI = "https://www.raiffeisen.ru/retail/deposit_investing/deposit/fixed/";

    @AfterAll
    protected static void cleanDownloads() throws IOException {
        deleteDirectory(new File(PATH_TO_DOWNLOAD));
    }

    @Test
    @DisplayName("Есть текст Вклад «Фиксированный» в скаченном файле с условиями вклада")
    void checkDownloadDepositFile() throws Exception {
        com.codeborne.selenide.Configuration.downloadsFolder = PATH_TO_DOWNLOAD;
        open(URI);
        $(byText("Условия")).click();
        File pdfFileDownload = $(byText("Условия")).parent().download();
        PDF parsedPDF = new PDF(pdfFileDownload);
        Assertions.assertTrue((parsedPDF.text).contains("Вклад «Фиксированный»"));
    }
}
