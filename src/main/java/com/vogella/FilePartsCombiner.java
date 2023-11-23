package com.vogella;

import java.io.*;
import java.util.*;

public class FilePartsCombiner {

    private final FilePartsWriter writer = new FilePartsWriter();
    public void writePartsToFile(List<String> parts, String outFile) throws IOException {
        Map<String, LinkedList<String>> collectors = getCollectors();

        for(String key : collectors.keySet()) {
            LinkedList<String> partLines = new LinkedList<>();

            for (String part : parts) {
                try(BufferedReader br = new BufferedReader(new FileReader(part))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        String lineFirstChar = String.valueOf(line.charAt(0)).toLowerCase();

                        if (key.equals(lineFirstChar)) {
                            partLines = collectors.get(key);

                            partLines.add(line);
                        }
                    }
                }

                writer.write(partLines, outFile);
            }
        }
    }


    private Map<String,  LinkedList<String>> getCollectors() {
        Map<String, LinkedList<String>> collectors = new LinkedHashMap<>();

        collectors.put("a", new LinkedList<>());
        collectors.put("b", new LinkedList<>());
        collectors.put("c", new LinkedList<>());
        collectors.put("d", new LinkedList<>());
        collectors.put("e", new LinkedList<>());
        collectors.put("f", new LinkedList<>());
        collectors.put("g", new LinkedList<>());
        collectors.put("h", new LinkedList<>());
        collectors.put("i", new LinkedList<>());
        collectors.put("j", new LinkedList<>());
        collectors.put("k", new LinkedList<>());
        collectors.put("l", new LinkedList<>());
        collectors.put("m", new LinkedList<>());
        collectors.put("n", new LinkedList<>());
        collectors.put("o", new LinkedList<>());
        collectors.put("p", new LinkedList<>());
        collectors.put("q", new LinkedList<>());
        collectors.put("r", new LinkedList<>());
        collectors.put("s", new LinkedList<>());
        collectors.put("t", new LinkedList<>());
        collectors.put("u", new LinkedList<>());
        collectors.put("v", new LinkedList<>());
        collectors.put("w", new LinkedList<>());
        collectors.put("x", new LinkedList<>());
        collectors.put("y", new LinkedList<>());
        collectors.put("z", new LinkedList<>());

        return collectors;
    }
}