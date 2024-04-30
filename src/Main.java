/**
 * Main
 */

import pkg.FileHandler;
import pkg.MapContainer;
import pkg.MapFileMaker;

import java.util.List;
import pkg.solver.AStar;
import pkg.solver.GBeFS;
import pkg.solver.UCS;

public class Main {
    private static MapContainer mapContainer;
    private static AStar aStar;
    private static GBeFS gBeFS;
    private static UCS ucs;

    public static void MakeMap() {
        for (int i = 1; i <= 15; i++) {

            MapFileMaker mapFileMaker = new MapFileMaker("input/split/data" + i + ".txt");
            mapFileMaker.MakeMap();
        }
    }

    public static void main(String[] args) {

        mapContainer = new MapContainer();

        aStar = new AStar(mapContainer);
        gBeFS = new GBeFS(mapContainer);
        ucs = new UCS(mapContainer);


        String start, goal;

        int input = -1;

        while (input != 4) {
            start = System.console().readLine("Enter start word: ").toUpperCase();
            goal = System.console().readLine("Enter goal word: ").toUpperCase();

            System.out.println("1. A*");
            System.out.println("2. Greedy Best First Search");
            System.out.println("3. UCS");

            input = Integer.parseInt(System.console().readLine("Choose algorithm: "));

            switch (input) {
                case 1:
                    List<String> result = aStar.Solve(start, goal);
                    if (result != null) {
                        System.out.println("Path: " + result);
                    } else {
                        System.out.println("No path found.");
                    }
                    break;
                case 2:
                    List<String> result2 = gBeFS.Solve(start, goal);
                    if (result2 != null) {
                        System.out.println("Path: " + result2);
                    } else {
                        System.out.println("No path found.");
                    }
                    break;
                case 3:
                    List<String> result3 = ucs.Solve(start, goal);
                    if (result3 != null) {
                        System.out.println("Path: " + result3);
                    } else {
                        System.out.println("No path found.");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}