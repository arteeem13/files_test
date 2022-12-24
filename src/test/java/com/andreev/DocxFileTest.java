package com.andreev;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@Nested
@DisplayName("Тест для docx файла в resources")
public class DocxFileTest {

    @Test
    @DisplayName("В docxFile.docx есть строка Checking content in word")
    void checkTextInDocxFileInZip() throws IOException {
        File doc = new File("src/test/resources/docxFile.docx");
        String contentDocFile = FileUtils.readFileToString(doc, "UTF-8");
        Assertions.assertTrue(contentDocFile.contains("Checking content in word"));
        }
    }
