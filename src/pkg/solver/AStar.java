package pkg.solver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import pkg.utils.Pair;

import pkg.MapContainer;

class PairComparator implements Comparator<Pair<List<String>, Integer>> {
    public int compare(Pair<List<String>, Integer> p1, Pair<List<String>, Integer> p2) {
        // Compare the second value of the pair
        // return true if p1 < p2
        return p1.getSecond() - p2.getSecond();
    }
}

public class AStar {
    private MapContainer mapContainer;

    public AStar(MapContainer mapContainer) {
        this.mapContainer = mapContainer;
    }

    public int CountScore(String word, String goal) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != goal.charAt(i)) {
                score++;
            }
        }
        return score;
    }

    public List<String> Solve(String start, String goal) {

        // Length of the start string != length of the goal string
        if (start.length() != goal.length()) {
            return null;
        }
        System.out.println("start: " + start);
        System.out.println("goal: " + goal);
        Map<String, List<String>> map = mapContainer.getMap(start.length() - 1);
        // Chek if the start and goal string is in the map

        if (!map.containsKey(start) || !map.containsKey(goal)) {
            System.out.println("start or goal not in dictionary or is a stop word.");
            return null;
        }

       
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        PriorityQueue<Pair<List<String>, Integer>> queue = new PriorityQueue<Pair<List<String>, Integer>>(new PairComparator());

        queue.add(new Pair<List<String>, Integer>(new ArrayList<String>(List.of(start)), this.CountScore(start, goal)));

        while (!queue.isEmpty()){

            Pair<List<String>, Integer> currentPath = queue.poll();
            String currentWord = currentPath.getFirst().get(currentPath.getFirst().size() - 1);

            visited.put(currentWord, true);

            if (currentWord.equals(goal)) {
                return currentPath.getFirst();
            }

            if (!map.containsKey(currentWord)) {
                System.out.println("currentWord not in dictionary.");
                continue;
            }

            int score = currentPath.getSecond();
            for (String word : map.get(currentWord)) {
                int newScore = CountScore(word, goal);
                if (newScore < score) {
                    score = newScore;
                }
            }

           
            for (String word : map.get(currentWord)) {
                    if (!visited.containsKey(word) && CountScore(word, goal) == score){
                        List<String> newPath = new ArrayList<String>(currentPath.getFirst());
                        newPath.add(word);
                        queue.add(new Pair<List<String>, Integer>(newPath, currentPath.getSecond() + 1));
                    }
                }
        
        }

        // Path not found
        return null;
    }
}