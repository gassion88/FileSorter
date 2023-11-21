package com.vogella;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private static final FilePartsReader filePartsReader = new FilePartsReader();
    private static final FilePartsCombiner filePartsCombiner = new FilePartsCombiner();
    private static final FilePartsWriter filePartWriter = new FilePartsWriter();

    public static void main(String[] args) throws IOException {
        externalSort(args[0], args[1], 1000000);
    }

    public static void externalSort(String inputFile, String outputFile, long memoryLimit) throws IOException {
        List<String> fileParts;
        long partSize = memoryLimit / 2;

        fileParts = filePartsReader.splitToParts(inputFile, partSize);

        PriorityQueue<String> sortedFileParts = filePartsCombiner.combine(fileParts);
        
        filePartWriter.write(sortedFileParts, outputFile);

        removeTemporaryFiles(fileParts);
    }

    private static void removeTemporaryFiles(List<String> parts) throws IOException {
        for (String part : parts) {
            //File file = Paths.get(part).toFile();
            Files.delete(Paths.get(part));
        }
    }
}