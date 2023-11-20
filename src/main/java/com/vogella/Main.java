package com.vogella;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private static final FilePartsReader filePartsReader = new FilePartsReader();
    private static final FilePartsCombiner filePartsCombiner = new FilePartsCombiner();
    private static final FilePartsWriter filePartWriter = new FilePartsWriter();
    private static List<String> fileParts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        externalSort("c:/temp/first.txt", "c:/temp/second.txt", 1000000);
    }

    public static void externalSort(String inputFile, String outputFile, long memoryLimit) throws IOException {
        long partSize = memoryLimit / 2;

        fileParts = filePartsReader.upload(inputFile, partSize);

        PriorityQueue<BufferedReader> sortedFileParts = filePartsCombiner.combine(fileParts);
        
        filePartWriter.write(sortedFileParts, outputFile);

        removeTemporaryFiles(fileParts);
    }

    private static void removeTemporaryFiles(List<String> PARTS) {
        for (String part : Main.fileParts) {
            new File(part).delete();
        }
    }
}