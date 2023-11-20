package com.vogella;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class FilePartsWriter {
    public void write(PriorityQueue<BufferedReader> sortedFile, String outputFile){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            while (!sortedFile.isEmpty()) {
                BufferedReader br = sortedFile.poll();
                String line = br.readLine();
                if (line != null) {
                    bw.write(line);
                    bw.newLine();
                    sortedFile.add(br);
                } else {
                    br.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
