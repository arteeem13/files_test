package com.andreev;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Nested()
@DisplayName("Тесты для txt файлов")
public class TxtFileTest extends Data {

    @Test
    @DisplayName("В скаченном txt файле " + URI_TO_README + " есть строка Tests for Student Registration Form")
    void checkContentTxtDownload() throws Exception {
        Configuration.downloadsFolder = PATH_TO_DOWNLOAD;
        open(URI_TO_README);
        File txtFile =  $("#raw-url").download();
        String contentTxtFile = FileUtils.readFileToString(txtFile, "UTF-8");
        Assertions.assertTrue(contentTxtFile.contains("Tests for Student Registration Form"));
    }

    @Test
    @DisplayName("Файл из resources загружен в систему " + URL_CONVERTER)
    void checkTxtUpload() throws Exception {
        Configuration.downloadsFolder = PATH_TO_DOWNLOAD;
        File txtFile = new File("src/test/resources/txtFile.txt");
        open(URL_CONVERTER);
        $("input[type='file']").uploadFile(txtFile);
        $(".dt-file-name-inner").shouldHave(Condition.text("txtFile.txt"));
        $(".dropdown").click();
        $(".formats-inner").$(byText("PDF")).click();
        $(".convert-button").click();
        $(".dt-file-name-inner").shouldHave(Condition.text("txtFile_1.pdf"));
    }
}
