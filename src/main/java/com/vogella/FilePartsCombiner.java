package com.vogella;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FilePartsCombiner {
    public PriorityQueue<String> combine(List<String> parts) throws IOException {
        PriorityQueue<String> heap = new PriorityQueue<>();

        for (String part : parts) {
            BufferedReader br = new BufferedReader(new FileReader(part));
            String line;
            while ((line = br.readLine()) != null){
                heap.add(line);
            }

        }

        return heap;
    }
}
