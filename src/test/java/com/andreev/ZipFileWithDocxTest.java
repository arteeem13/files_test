package com.andreev;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Nested
@DisplayName("Тесты для zip архива и содержимого файла docx нем")
public class ZipFileWithDocxTest {

    @Test
    @DisplayName("Есть файл test.docx в zip архиве")
    void docxFileInZipTest() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("zipFile.zip")) {
            ZipInputStream zip = new ZipInputStream(is);
            ZipEntry entry;
            String name;
            while ((entry = zip.getNextEntry()) != null) {
                name = entry.getName();
                Assertions.assertEquals(name, "test.docx");
            }
        }
    }

    @Test
    @DisplayName("В docxFile.docx есть строка Checking content in word")
    void checkTextInDocxFileInZip() throws IOException {
        String path = "src/test/resources/";
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("zipFile.zip")) {
            ZipInputStream zip = new ZipInputStream(is);
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                String nameFile = entry.getName();
                Assertions.assertEquals(nameFile, "test.docx");

                int countFiles;
                FileOutputStream docxFile = new FileOutputStream(path + nameFile);
                ByteArrayOutputStream byteFile = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                while((countFiles = zip.read(buffer)) != -1)
                {
                    byteFile.write(buffer, 0, countFiles);
                    byte[] bytes = byteFile.toByteArray();
                    docxFile.write(bytes);
                    byteFile.reset();
                }
                docxFile.close();
                zip.closeEntry();

                File docx = new File(path + nameFile);
                String contentDocxFile = FileUtils.readFileToString(docx, "UTF-8");
                Assertions.assertEquals(contentDocxFile, "Checking content in word");
                docx.delete();
            }
        }
    }
}
