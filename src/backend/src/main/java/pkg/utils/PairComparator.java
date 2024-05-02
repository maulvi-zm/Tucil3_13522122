package pkg.utils;

import java.util.Comparator;

public class PairComparator<T> implements Comparator<Pair<T, Integer>> {
    public int compare(Pair<T, Integer> p1, Pair<T, Integer> p2) {
        // Compare the second value of the pair
        // return true if p1 < p2
        return p1.getSecond() - p2.getSecond();
    }
}