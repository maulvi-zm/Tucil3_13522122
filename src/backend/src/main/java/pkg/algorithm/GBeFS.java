package pkg.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.PriorityQueue;
import pkg.utils.Pair;
import pkg.MapContainer;
import pkg.solution.Solution;
import pkg.utils.PairComparator;

public class GBeFS {
    private MapContainer mapContainer;

    public GBeFS(MapContainer mapContainer) {
        this.mapContainer = mapContainer;
    }

    public int CountScore(String word, String goal) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == goal.charAt(i)) {
                score--;
            }
        }
        return score;
    }

    public Solution Solve(String start, String goal) {
        System.gc();
        // Length of the start string != length of the goal string
        if (start.length() != goal.length()) {
            Solution solution = new Solution(2);
            return solution;
        }
        System.out.println("start: " + start);
        System.out.println("goal: " + goal);
        Map<String, List<String>> map = mapContainer.getMap(start.length() - 1);
        
        // Check if the start and goal string is in the map
        if (!map.containsKey(start) || !map.containsKey(goal)) {
            System.out.println("start or goal not in dictionary or is a stop word.");
            Solution solution = new Solution(1);
            return solution;
        }

        int total_nodes = 0;
        long startTime = System.currentTimeMillis();
       
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        PriorityQueue<Pair<List<String>, Integer>> queue = new PriorityQueue<Pair<List<String>, Integer>>(new PairComparator<List<String>>());

        queue.add(new Pair<List<String>, Integer>(new ArrayList<String>(List.of(start)), this.CountScore(start, goal)));

        while (!queue.isEmpty()){

            Pair<List<String>, Integer> currentPath = queue.poll();
            String currentWord = currentPath.getFirst().get(currentPath.getFirst().size() - 1);

            visited.put(currentWord, true);
            total_nodes++;

            if (currentWord.equals(goal)) {
                long endTime = System.currentTimeMillis();
                long timeElapsed = endTime - startTime;
                long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
                Solution solution = new Solution(timeElapsed, currentPath.getFirst(), total_nodes, memory);
                queue.clear();
                visited.clear();
                return solution;
            }

            if (!map.containsKey(currentWord)) {
                System.out.println("currentWord not in dictionary.");
                continue;
            }

            // Find the word with the lowest score to get to the goal
            int score = Integer.MAX_VALUE;
            for (String word : map.get(currentWord)) {
                int newScore = CountScore(word, goal);
                if (newScore < score && !visited.containsKey(word)) {
                    score = newScore;
                }
            }
    
            for (String word : map.get(currentWord)) {
                // If the word has the same score as the current score, add it to the queue
                if (!visited.containsKey(word) && CountScore(word, goal) == score){
                    List<String> newPath = new ArrayList<String>(currentPath.getFirst());
                    newPath.add(word);
                    queue.add(new Pair<List<String>, Integer>(newPath, CountScore(word, goal)));
                    break;
                }
            }
        }

        // Path not found
        Solution solution = new Solution(3);
        return solution;
    }
}
