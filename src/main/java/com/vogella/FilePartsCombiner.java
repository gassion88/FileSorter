package com.vogella;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FilePartsCombiner {
    public PriorityQueue<BufferedReader> combine(List<String> parts) throws IOException {
        PriorityQueue<BufferedReader> heap = new PriorityQueue<>(Comparator.comparing(s -> {
            try {
                return s.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }));

        for (String part : parts) {
            BufferedReader br = new BufferedReader(new FileReader(part));
                heap.add(br);
        }

        return heap;
    }
}
