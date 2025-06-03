package sortingVis;

import javax.swing.*;
import java.util.List;

/**
 * Manages all sorting functionality.
 * Includes algorithms selection sort, insertion sort, and bubble sort.
 *
 * @author Collin Phipps
 */
public class SortingManager {

    private SortType currentSort;
    private final JPanel panel;
    private boolean sorting = false;

    /**
     * Creates a new SortingManager, and sets the default sort to selection sort.
     *
     * @param panel - the panel to be drawn to.
     */
    public SortingManager(JPanel panel) {
        currentSort = SortType.SELECTION_SORT;
        this.panel = panel;
    }

    /**
     * Determines the sorting algorithm to be used.
     *
     * @param columns - the List to be sorted
     */
    public void sort(List<Column> columns) {
        switch (currentSort) {
            case SELECTION_SORT -> selectionSort(columns);
            case INSERTION_SORT -> insertionSort(columns);
            case BUBBLE_SORT -> bubbleSort(columns);
            }
        }

    /**
     * Sorting the given List using the selection sort
     * algorithm. Updates the drawing to the panel as the
     * algorithm progresses.
     *
     * @param columns - the List to be sorted.
     */
    private void selectionSort(List<Column> columns) {
        final int[] i = {0};
        final int[] j = {1};
        final int[] min = {0};

        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            if (!sorting) {
                ((Timer) e.getSource()).stop();
                return;
            }

            if (i[0] >= columns.size()) {
                columns.forEach(c -> c.setState(Column.State.SORTED));
                panel.repaint();
                ((Timer) e.getSource()).stop();
                return;
            }

            columns.forEach(c -> {
                if (c.getState() != Column.State.SORTED)
                    c.setState(Column.State.NORMAL);
            });

            columns.get(i[0]).setState(Column.State.ACTIVE);
            columns.get(min[0]).setState(Column.State.ACTIVE);
            if (j[0] < columns.size()) {
                columns.get(j[0]).setState(Column.State.ACTIVE);
                if (columns.get(j[0]).getHeight() < columns.get(min[0]).getHeight()) {
                    min[0] = j[0];
                }
                j[0]++;
            } else {
                Column temp = columns.get(i[0]);
                columns.set(i[0], columns.get(min[0]));
                columns.set(min[0], temp);

                columns.get(i[0]).setState(Column.State.SORTED);
                i[0]++;
                j[0] = i[0] + 1;
                min[0] = i[0];
            }
            panel.repaint();
        });
        timer.start();
    }

    /**
     * Sorting the given List using the insertion sort
     * algorithm. Updates the drawing to the panel as the
     * algorithm progresses.
     *
     * @param columns - the List to be sorted.
     */
    private void insertionSort(List<Column> columns) {
        final int[] i = {1};
        final int[] j = {1};

        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            if (!sorting) {
                ((Timer) e.getSource()).stop();
                return;
            }

            if (i[0] >= columns.size()) {
                columns.forEach(c -> c.setState(Column.State.SORTED));
                panel.repaint();
                ((Timer) e.getSource()).stop();
                return;
            }

            columns.forEach(c -> {
                if (c.getState() != Column.State.SORTED)
                    c.setState(Column.State.NORMAL);
            });

            if (j[0] > 0) columns.get(j[0]).setState(Column.State.ACTIVE);

            if (j[0] > 0 && columns.get(j[0]).compareTo(columns.get(j[0] - 1)) < 0) {
                swapColumns(columns, j[0], j[0] - 1);
                j[0]--;
            } else {
                i[0]++;
                j[0] = i[0];
            }

            for (int k = 0; k < i[0]; k++) {
                if (columns.get(k).getState() != Column.State.ACTIVE)
                    columns.get(k).setState(Column.State.SORTED);
            }

            panel.repaint();
        });
        timer.start();
    }

    /**
     * Sorting the given List using the bubble sort
     * algorithm. Updates the drawing to the panel as the
     * algorithm progresses.
     *
     * @param columns - the List to be sorted.
     */
    private void bubbleSort(List<Column> columns) {
        final int[] i = {0};
        final int[] j = {0};

        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            if (!sorting) {
                ((Timer) e.getSource()).stop();
                return;
            }

            if (i[0] >= columns.size() - 1) {
                columns.forEach(c -> c.setState(Column.State.SORTED));
                panel.repaint();
                ((Timer) e.getSource()).stop();
                return;
            }

            columns.forEach(c -> {
                if (c.getState() != Column.State.SORTED)
                    c.setState(Column.State.NORMAL);
            });

            columns.get(j[0]).setState(Column.State.ACTIVE);

            if (j[0] < columns.size() - 1 - i[0]) {
                if (columns.get(j[0]).compareTo(columns.get(j[0] + 1)) > 0) {
                    swapColumns(columns, j[0], j[0] + 1);
                }
                j[0]++;
            } else {
                for (int k = columns.size() - 1 - i[0]; k < columns.size(); k++) {
                    columns.get(k).setState(Column.State.SORTED);
                }

                i[0]++;
                j[0] = 0;
            }
            panel.repaint();
        });
        timer.start();
    }

    /**
     * Specifies the current sorting algorithm.
     *
     * @param currentSort - the desired SortType
     */
    public void setCurrentSort(SortType currentSort) {
        this.currentSort = currentSort;
    }

    /**
     * Helper method that swaps the location of two columns
     * in a List.
     *
     * @param columns - the List to be modified
     * @param idx1 - the first index
     * @param idx2 - the second index
     */
    private void swapColumns(List<Column> columns, int idx1, int idx2) {
        Column temp = columns.get(idx1);
        columns.set(idx1, columns.get(idx2));
        columns.set(idx2, temp);
    }

    /**
     * Specifies whether sorting is in progress
     *
     * @param sorting - a boolean specifying the SortingManager state.
     */
    public void setSorting(boolean sorting) {
        this.sorting = sorting;
    }
}
