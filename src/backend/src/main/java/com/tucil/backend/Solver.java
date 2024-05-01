package com.tucil.backend;

import pkg.FileHandler;
import pkg.MapContainer;
import pkg.MapFileMaker;
import pkg.algorithm.AStar;
import pkg.algorithm.GBeFS;
import pkg.algorithm.UCS;
import pkg.solution.Solution;
 
 public class Solver {
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
 
     public static void HandleFile() {
         FileHandler fileHandler = new FileHandler("input/words.txt");
         fileHandler.SplitFileByLength();
     }

     public Solution Solve(String start, String goal, String algorithm) {

        start = start.toUpperCase();
        goal = goal.toUpperCase();

         switch (algorithm) {
             case "AStar":
                 return aStar.Solve(start, goal);
             case "GBeFS":
                return gBeFS.Solve(start, goal);
             case "UCS":
                 return ucs.Solve(start, goal);
             default:
                return null;

         }
     }
    
 
     public Solver() {
            mapContainer = new MapContainer();
            aStar = new AStar(mapContainer);
            gBeFS = new GBeFS(mapContainer);
            ucs = new UCS(mapContainer);
         }
     }