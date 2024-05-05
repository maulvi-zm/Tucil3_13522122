package pkg;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;


public class MapContainer {
    private List<Map<String, List<String>>> listOfMaps;
        
        /**
         * Constructs a new MapContainer object.
         * Initializes the listOfMaps with an ArrayList of maps.
         * The first two elements of the list are set to null, while the rest are created using MapCreator.
         */
    public MapContainer() {
        this.listOfMaps = new ArrayList<Map<String, List<String>>>();

        for (int i = 0; i < 7; i++) {
            if (i == 0 || i == 1) {
                this.listOfMaps.add(null);
                continue;
            }
            
            this.listOfMaps.add(MapCreator(i + 1));
        }
    }

    public Map<String, List<String>> getMap(int i) {
        return this.listOfMaps.get(i);
    }

    public Map<String, List<String>> MapCreator(int i) {
        File file = new File("input/map/" + i + ".txt");

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return null;
        }

        if (!file.isFile()) {
            System.out.println("Path is not a file.");
            return null;
        }

        Map<String, List<String>> map = new HashMap<String, List<String>>();

        try {
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine();
                int value = Integer.parseInt(scanner.nextLine());

                List<String> list = new ArrayList<String>();
                for (int j = 0; j < value; j++) {
                    String line = scanner.nextLine();
                    list.add(line);
                }

                // Kalau value nya 0, tidak usah dimasukkan ke map
                if (value != 0){
                    map.put(key, list);
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return map;
    }
}
