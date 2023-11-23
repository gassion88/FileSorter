package com.vogella;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final FilePartsReader filePartsReader = new FilePartsReader();
    private static final FilePartsCombiner filePartsCombiner = new FilePartsCombiner();

    public static void main(String[] args) throws IOException {
        externalSort(args[0], args[1], Long.parseLong(args[2]));
    }

    public static void externalSort(String inputFile, String outputFile, long memoryLimit) throws IOException {
        List<String> fileParts;
        long partSize = memoryLimit / 3;

        fileParts = filePartsReader.splitToParts(inputFile, partSize);

        filePartsCombiner.writePartsToFile(fileParts, outputFile);


        removeTemporaryFiles(fileParts);
    }

    private static void removeTemporaryFiles(List<String> parts) throws IOException {
        for (String part : parts) {
            Files.delete(Paths.get(part));
        }
    }
}