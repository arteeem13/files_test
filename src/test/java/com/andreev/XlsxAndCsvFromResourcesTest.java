package com.andreev;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class XlsxAndCsvFromResourcesTest {

    @Test
    void xlxsTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("xlsxFile.xlsx");
        XLS parsedFile = new XLS(stream);
        String value =  parsedFile.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
        System.out.println(value);
        Assertions.assertEquals("Price", value);
    }

    @Test
    void csvTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("csvFile.csv");
        CSVReader csvFile = new CSVReader(new InputStreamReader(stream));
        List<String[]> result;
        result = csvFile.readAll();
        Assertions.assertEquals("Price", result.get(0)[0]);
    }
}
