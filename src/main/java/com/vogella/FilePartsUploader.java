package com.vogella;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilePartsUploader {
    long partSize = 0;
    List<String> parts = new ArrayList<>();

    public List<String> upload(String inputFile, long memoryLimit) {
        partSize = memoryLimit;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;

            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                lines.add(line);
                if (lines.stream().mapToLong(String::length).sum() > partSize) {
                    generatePartFile(inputFile, lines);
                    lines.clear();
                }
            }

            if (!lines.isEmpty()) {
                generatePartFile(inputFile, lines);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parts;
    }

    private void generatePartFile(String inputFile, List<String> lines) throws IOException {
        partLinesSort(lines);
        String chunkFile = generatePartFileName(inputFile);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(chunkFile))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        }
        parts.add(chunkFile);
    }

    private static void partLinesSort(List<String> lines) {
        lines.sort(Comparator.naturalOrder());
    }

    private String generatePartFileName(String inputFile) {
        String fileName = inputFile.split("\\.")[0];
        return fileName + "_chunk" + parts.size() + ".txt";
    }
}
