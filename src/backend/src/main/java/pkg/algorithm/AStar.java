package pkg.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

import pkg.utils.Pair;
import pkg.MapContainer;
import pkg.solution.Solution;
import pkg.utils.PairComparator;


public class AStar {
    private MapContainer mapContainer;

    public AStar(MapContainer mapContainer) {
        this.mapContainer = mapContainer;
    }

    public int CountCost(String word, String goal) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != goal.charAt(i)) {
                score++;
            }
        }
        return score;
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
        Map<String, Integer> distanceMap = new HashMap<String, Integer>();
        Map<String, String> pathMap = new HashMap<String, String>();

        // AKan menyimpan total cost dari start ke node tersebut
        PriorityQueue<Pair<String, Integer>> queue = new PriorityQueue<Pair<String, Integer>>(new PairComparator<String>());

        queue.add(new Pair<String, Integer>(start, this.CountCost(start, goal)));
        distanceMap.put(start, 0);

        while (!queue.isEmpty()){

            String currentWord = queue.poll().getFirst();

            visited.put(currentWord, true);
            total_nodes++;

            if (currentWord.equals(goal)) {
                long endTime = System.currentTimeMillis();
                long time =  endTime - startTime;
                long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;

                List<String> path = new ArrayList<String>();

                //Make path from start to goal
                while (currentWord != null) {
                    path.add(0, currentWord);
                    currentWord = pathMap.get(currentWord);
                }

                Solution solution = new Solution(time, path, total_nodes, memory);
                queue.clear();
                visited.clear();
                return solution;
            }

            if (!map.containsKey(currentWord)) {
                System.out.println("currentWord not in dictionary. Skipping.");
                continue;
            }

            int distanceToCurrent = distanceMap.get(currentWord);

            for (String child : map.get(currentWord)) {
                int newDistance = distanceToCurrent + 1;
                int newScore = CountCost(child, goal) + newDistance;

                if (!visited.containsKey(child)){

                    if (!distanceMap.containsKey(child)) {
                        distanceMap.put(child, newDistance);
                        pathMap.put(child, currentWord);
                        queue.add(new Pair<String, Integer>(child, newScore));
                    } else if (newDistance < distanceMap.get(child)) {
                        distanceMap.put(child, newDistance);

                        // Find currentScore in queue and remove it if the newScore is smaller
                        Iterator<Pair<String, Integer>> iterator = queue.iterator();
                        while (iterator.hasNext()) {
                            Pair<String, Integer> pair = iterator.next();
                            if (newScore < pair.getSecond()) {
                                queue.remove(pair);
                                pathMap.put(child, currentWord);
                                queue.add(new Pair<String, Integer>(child, newScore));

                                break;
                            }
                        }
                    } 

                }
            }
        }

        // Path not found
        Solution solution = new Solution(3);
        return solution;
    }
}