package com.andreev;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Configuration.fileDownload;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PairwiseToolsXlsxTest {

    @Test
    void checkPairsInFile() throws Exception {

        open("https://pairwise.teremokgames.com/");
        // Заполняем названия столбцов
        $("input[placeholder='{your column name}']").setValue("Device");
        $("input[class='add']").click();
        $("input[placeholder='Column 2']").setValue("VALUE_RAM");
        $("input[class='add']").click();
        $("input[placeholder='Column 3']").setValue("VALUE_ROM");

        // Добавляем еще 3 строки
        $(byText("Row 1")).parent().parent().sibling(0).$("input[class='add']").click();
        $(byText("Row 1")).parent().parent().sibling(1).$("input[class='add']").click();
        $(byText("Row 1")).parent().parent().sibling(2).$("input[class='add']").click();

        // Заполняем строки данными
        $(byText("Row 1")).parent().sibling(0).$("input[placeholder='{your value}']").setValue("Samsung");
        $(byText("Row 1")).parent().sibling(1).$("input[placeholder='{your value}']").setValue("4GB");
        $(byText("Row 1")).parent().sibling(2).$("input[placeholder='{your value}']").setValue("32GB");
        $(byText("Row 2")).parent().sibling(0).$("input[placeholder='{your value}']").setValue("Apple");
        $(byText("Row 2")).parent().sibling(1).$("input[placeholder='{your value}']").setValue("6GB");
        $(byText("Row 2")).parent().sibling(2).$("input[placeholder='{your value}']").setValue("64GB");
        $(byText("Row 3")).parent().sibling(0).$("input[placeholder='{your value}']").setValue("Xiaomi");
        $(byText("Row 3")).parent().sibling(1).$("input[placeholder='{your value}']").setValue("8GB");
        $(byText("Row 3")).parent().sibling(2).$("input[placeholder='{your value}']").setValue("128GB");
        $(byText("Row 4")).parent().sibling(0).$("input[placeholder='{your value}']").setValue("SONY");
        $(byText("Row 4")).parent().sibling(2).$("input[placeholder='{your value}']").setValue("256GB");

        File file = $("#generateButton").download();
        sleep(5000);

//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        InputStream stream = classLoader.getResourceAsStream("Pairwise.xlsx");
//        System.out.println(stream);

//        XLS parsedFile = new XLS(file);
        System.out.println(fileDownload);
        System.out.println(file);
//        String stringCellValue = xlsFile.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue();
//        System.out.println(stringCellValue);
    }
}
