package com.andreev;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.apache.commons.io.FileUtils.deleteDirectory;

@Nested()
@DisplayName("Тест для txt файлов")
public class GithubReadmeTxtTest {
    private static final String PATH_TO_DOWNLOAD = "downloads";
    private static final String URI_TO_README = "https://github.com/arteeem13/demoqa_tests/blob/main/README.md";

    @AfterAll
    protected static void cleanDownloads() throws IOException {
        deleteDirectory(new File(PATH_TO_DOWNLOAD));
    }

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
