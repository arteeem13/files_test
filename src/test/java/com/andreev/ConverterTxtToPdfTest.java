package com.andreev;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.apache.commons.io.FileUtils.deleteDirectory;

@Nested
@DisplayName("Проверка содержания преобразованного pdf из txt")
public class ConverterTxtToPdfTest {
    private static final String PATH_TO_DOWNLOAD = "downloads";
    private static final String URL_CONVERTER = "https://convertio.co/ru/";

    @AfterAll
    protected static void cleanDownloads() throws IOException {
        deleteDirectory(new File(PATH_TO_DOWNLOAD));
    }

    @Test
    @DisplayName("В конвертированном и скаченном pdf содержится информация из загруженного txt через " + URL_CONVERTER)
    void checkTxtUpload() throws IOException {
        com.codeborne.selenide.Configuration.downloadsFolder = PATH_TO_DOWNLOAD;
        File txtFile = new File("src/test/resources/txtFile.txt");
        String contentTxtFile = FileUtils.readFileToString(txtFile, "UTF-8");

        open(URL_CONVERTER);
        $("input[type='file']").uploadFile(txtFile);
        $(".dt-file-name-inner").shouldHave(Condition.text("txtFile.txt"));
        $(".dropdown").click();
        $(".formats-inner").$(byText("PDF")).click();
        $(".convert-button").click();
        sleep(10000);
        $(".dt-file-name-inner").shouldHave(Condition.text("txtFile.pdf"));

        File pdfFileDownload =  $(byText("Скачать")).download();
        PDF parsedPDF = new PDF(pdfFileDownload);
        Assertions.assertTrue((parsedPDF.text).contains(contentTxtFile));
    }
}
