package pkg;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class MapFileMaker {
    private File file;

    public MapFileMaker(String path) {
        this.file = new File(path);

        if (!this.file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        if (!this.file.isFile()) {
            System.out.println("Path is not a file.");
            return;
        }
    }

    /**
     * Checks if two strings have exactly one character difference.
     *
     * @param a the first string
     * @param b the second string
     * @return true if the strings have exactly one character difference, false otherwise
     */
    private Boolean isOneElementDifferent(String a, String b) {
        int diff = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }

            if (diff > 1) {
                return false;
            }
        }

        return diff == 1;
    }

    /**
     * Reads a file, creates a map of words, and writes the map to a file.
     */
    public void MakeMap() {
        List<String> words = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(this.file);
            
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        for (int i = 0; i < words.size(); i++) {
            List<String> list = new ArrayList<String>();
            for (int j = 0; j < words.size(); j++) {
                if (this.isOneElementDifferent(words.get(i), words.get(j))) {
                    list.add(words.get(j));
                }
            }
            this.WriteToFile(words.get(i), list);
        }
    }

    /**
     * Writes the given key-value pair to a file.
     *
     * @param key   the key to be written to the file
     * @param value the list of values to be written to the file
     */
    public void WriteToFile(String key, List<String> value) {
        String path = "input/map/" + key.length() + ".txt";
        File file = new File(path);

        try {
            FileWriter writer = new FileWriter(file, true);
            
            writer.write(key);
            writer.write("\n");
            writer.write(String.valueOf(value.size()));
            writer.write("\n");

            for (int i = 0; i < value.size(); i++) {
                writer.write(value.get(i));
                writer.write("\n");
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
