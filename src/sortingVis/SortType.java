package sortingVis;

import java.util.ArrayList;

/**
 * Enum containing the different types of
 * sorting algorithms.
 *
 * @author Collin Phipps
 */
public enum SortType {
    SELECTION_SORT,
    INSERTION_SORT,
    BUBBLE_SORT;

    /**
     * Helper method that returns the algorithm name as a String.
     *
     * @param type - the type of algorithm
     * @return the algorithm name as a String
     */
    private static String getDisplayName(SortType type) {
        return switch(type) {
            case SELECTION_SORT -> "Selection Sort";
            case INSERTION_SORT -> "Insertion Sort";
            case BUBBLE_SORT -> "Bubble Sort";
        };
    }

    /**
     * Returns an array of Strings containing algorithm names.
     *
     * @return the algorithm names
     */
    public static String[] getSortNames() {
        ArrayList<String> sorts = new ArrayList<>();
        for (SortType sort : SortType.values()) {
            sorts.add(getDisplayName(sort));
        }
        return sorts.toArray(new String[0]);
    }

    /**
     * Converts an index to a SortType.
     *
     * @param index - the index to be converted
     * @return the corresponding SortType
     */
    public static SortType getSortFromIndex(int index) {
        return SortType.values()[index];
    }
}