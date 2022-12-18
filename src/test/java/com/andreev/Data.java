package com.andreev;

import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.deleteDirectory;

public class Data {
    protected static final String PATH_TO_DOWNLOAD = "downloads";
    protected static final String URI_TO_README = "https://github.com/arteeem13/demoqa_tests/blob/main/README.md";
    protected static final String URL_CONVERTER = "https://convertio.co/ru/";

    @AfterAll
    private static void cleanDownloads() throws IOException {
        deleteDirectory(new File(PATH_TO_DOWNLOAD));
    }
}
