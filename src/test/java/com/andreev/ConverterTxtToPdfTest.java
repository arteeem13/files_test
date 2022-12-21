package com.andreev;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Nested
@DisplayName("Проверка содержания преобразованного pdf из txt")
public class ConverterTxtToPdfTest extends Configurations {

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
