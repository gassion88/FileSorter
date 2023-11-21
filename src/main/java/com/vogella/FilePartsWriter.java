package com.vogella;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class FilePartsWriter {
    public void write(PriorityQueue<String> sortedFile, String outputFile){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            while (!sortedFile.isEmpty()) {
                String line = sortedFile.poll();
                        bw.write(line);
                        bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
