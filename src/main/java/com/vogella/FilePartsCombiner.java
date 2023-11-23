package com.vogella;

import java.io.*;
import java.util.List;
import java.util.PriorityQueue;

public class FilePartsCombiner {
    public void combineAndWrite(List<String> parts, String outputFile, int chunkSize) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String part : parts) {
                try(BufferedReader br = new BufferedReader(new FileReader(part))) {
                    String line;
                    int count = 0;
                    while ((line = br.readLine()) != null){
                        writer.write(line);
                        writer.newLine();
                        count++;
                        if (count == chunkSize) {
                            writer.flush();
                            count = 0;
                        }
                    }
                }
            }
        }
    }
}
