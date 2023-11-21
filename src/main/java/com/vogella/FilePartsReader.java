package com.vogella;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilePartsReader {
    public List<String> splitToParts(String inputFile, long memoryLimit) {
        List<String> filePartsNames = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;

            List<String> lines = new ArrayList<>();
            while (((line = br.readLine()) != null) && !line.isEmpty()) {
                lines.add(line);
                long linesSize = lines.stream().mapToLong(String::length).sum();

                if (linesSize > memoryLimit) {
                    String filePartName = createFilePart(inputFile, lines, filePartsNames.size());
                    writeToFilePart(filePartName, lines);
                    filePartsNames.add(filePartName);

                    lines.clear();
                }
            }

            if (!lines.isEmpty()) {
                String filePartName = createFilePart(inputFile, lines, filePartsNames.size());
                writeToFilePart(filePartName, lines);
                filePartsNames.add(filePartName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filePartsNames;
    }

    private String createFilePart(String inputFile, List<String> lines, int size) {
        String filePartName = generateFilePartName(inputFile, size);
        filePartLinesSort(lines);

        return filePartName;
    }

    private void writeToFilePart(String filePartName, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePartName))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void filePartLinesSort(List<String> lines) {
        lines.sort(Comparator.naturalOrder());
    }

    private String generateFilePartName(String inputFile, int size) {
        String fileName = inputFile.split("\\.")[0];
        return fileName + "_chunk" + size + ".txt";
    }
}
