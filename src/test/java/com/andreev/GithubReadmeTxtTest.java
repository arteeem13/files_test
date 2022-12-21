package com.andreev;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Nested()
@DisplayName("Тест для txt файлов")
public class GithubReadmeTxtTest extends Configurations {

    @Test
    @DisplayName("В скаченном txt файле " + URI_TO_README + " есть строка Tests for Student Registration Form")
    void checkContentTxtDownload() throws Exception {
        com.codeborne.selenide.Configuration.downloadsFolder = PATH_TO_DOWNLOAD;
        open(URI_TO_README);
        File txtFile =  $("#raw-url").download();
        String contentTxtFile = FileUtils.readFileToString(txtFile, "UTF-8");
        Assertions.assertTrue(contentTxtFile.contains("Tests for Student Registration Form"));
    }
}
