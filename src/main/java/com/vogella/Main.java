package com.vogella;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private static final FilePartsUploader filePartsUploader = new FilePartsUploader();
    private static final FilePartsCombiner filePartsCombiner = new FilePartsCombiner();
    private static final PartsToFileWriter partsToFileWriter = new PartsToFileWriter();
    private static List<String> PARTS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        externalSort("c:/temp/first.txt", "c:/temp/second.txt", 1000000);
    }

    public static void externalSort(String inputFile, String outputFile, long memoryLimit) throws IOException {
        long PART_SIZE = memoryLimit / 2;

        PARTS = filePartsUploader.upload(inputFile, PART_SIZE);

        PriorityQueue<BufferedReader> sortedParts = filePartsCombiner.combine(PARTS);
        
        partsToFileWriter.write(sortedParts, outputFile);

        removeTemproaryFiles(PARTS);
    }

    private static void removeTemproaryFiles(List<String> PARTS) {
        for (String part : Main.PARTS) {
            new File(part).delete();
        }
    }
}