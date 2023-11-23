package com.vogella;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FilePartsWriter {
    public void write(LinkedList<String> lines, String outputFile){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
