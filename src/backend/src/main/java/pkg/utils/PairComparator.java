package pkg.utils;

import java.util.Comparator;
import java.util.List;

public class PairComparator implements Comparator<Pair<List<String>, Integer>> {
    public int compare(Pair<List<String>, Integer> p1, Pair<List<String>, Integer> p2) {
        // Compare the second value of the pair
        // return true if p1 < p2
        return p1.getSecond() - p2.getSecond();
    }
}