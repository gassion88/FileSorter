package com.vogella;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FilePartsReaderTest {

    private String inputFile;

    @Before
    public void setUp() throws Exception {
        // Create a test input file
        inputFile = "input.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile))) {
            bw.write("line1");
            bw.newLine();
            bw.write("line2");
            bw.newLine();
            bw.write("line3");
            bw.newLine();
        }
    }

    @After
    public void tearDown() throws Exception {
        // Delete the input file after each test
        Files.deleteIfExists(Path.of(inputFile));
    }

    @Test
    public void testSplitToParts() throws IOException {
        FilePartsReader reader = new FilePartsReader();
        List<String> filePartsNames = reader.splitToParts(inputFile, 10);


        Assert.assertEquals(1, filePartsNames.size());
        Assert.assertTrue(filePartsNames.contains("input_part0.txt"));

        Files.deleteIfExists(Path.of("input_part0.txt"));
    }

    @Test
    public void testSplitToPartsWithEmptyFile() throws IOException {
        String emptyFile = "empty.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(emptyFile))) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FilePartsReader reader = new FilePartsReader();
        List<String> filePartsNames = reader.splitToParts(emptyFile, 10);

        Assert.assertEquals(0, filePartsNames.size());

        Files.deleteIfExists(Path.of(emptyFile));
    }
}