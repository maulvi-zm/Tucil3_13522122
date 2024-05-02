package pkg.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import pkg.solution.Solution;

import pkg.MapContainer;

public class UCS {
    private MapContainer mapContainer;

    public UCS(MapContainer mapContainer) {
        this.mapContainer = mapContainer;
    }

    public Solution Solve(String start, String goal) {

        // Length of the start string != length of the goal string
        if (start.length() != goal.length()) {
            Solution solution = new Solution(2);
            return solution;
        }
        System.out.println("start: " + start);
        System.out.println("goal: " + goal);
        Map<String, List<String>> map = mapContainer.getMap(start.length() - 1);
        // Chek if the start and goal string is in the map

        if (!map.containsKey(start) || !map.containsKey(goal)) {
            System.out.println("start or goal not in dictionary or is a stop word.");
            Solution solution = new Solution(1);
            return solution;
        }

        int total_nodes = 0;
        long startTime = System.currentTimeMillis();
       
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        Queue<List<String>> queue = new LinkedList<List<String>>();

        queue.add(new ArrayList<String>(List.of(start)));

        while (!queue.isEmpty()){

            List<String> currentPath = queue.poll();
            String currentWord = currentPath.get(currentPath.size() - 1);

            visited.put(currentWord, true);
            total_nodes++;

            if (currentWord.equals(goal)) {
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
                Solution solution = new Solution(time, currentPath, total_nodes, memory);
                queue.clear();
                visited.clear();
                return solution;
            }

            if (!map.containsKey(currentWord)) {
                System.out.print(currentPath);
                System.out.print(currentWord);
                System.out.println("currentWord not in dictionary.");
                continue;
            }

            for (String word : map.get(currentWord)) {
                if (!visited.containsKey(word)) {
                    List<String> newPath = new ArrayList<String>(currentPath);
                    newPath.add(word);
                    queue.add(newPath);
                }
            }
        }

        // Path not found
        Solution solution = new Solution(3);
        return solution;
    }
}
