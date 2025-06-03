package sortingVis;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Panel that contains the columns to be sorted. The
 * columns are stored in an ArrayList.
 */
public class VisualPanel extends JPanel {
    private int columnCount;
    private final ArrayList<Column> columns;

    private final SortingManager sortingManager = new SortingManager(this);

    /**
     * Creates a new VisualPanel and adds columns to ArrayList.
     *
     * @param columnCount - the amount of columns to include
     */
    public VisualPanel(int columnCount) {
        setPreferredSize(new Dimension(500, 500));
        setBackground(new Color(51, 55, 61));

        this.columnCount = columnCount;

        columns = new ArrayList<>();
        initColumns();
    }

    /**
     * Creates columns specified by the count and panel dimension.
     */
    private void initColumns() {
        columns.clear();
        int columnHeight = (500 - 15) / columnCount;
        for (int i = 1; i <= columnCount; i++) {
            Column column = new Column(i * columnHeight);
            columns.add(column);
        }

        Collections.shuffle(columns);
    }

    /**
     * Calculates dimensions and draws columns onto panel.
     *
     * @param g the Graphics object to draw with
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(columns.getFirst().getColor());
        int columnWidth = getWidth() / columns.size();
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            double height = column.getHeight();
            double x = i * columnWidth;
            double y = getHeight() - height;
            Rectangle2D.Double rect = new Rectangle2D.Double(x, y, columnWidth, height);
            g2.setColor(column.getColor());
            g2.fill(rect);
        }
    }

    /**
     * Sorts the ArrayList of columns.
     * Calls the method sort from SortingManager.
     */
    public void sort() {
        sortingManager.sort(columns);
        sortingManager.setSorting(true);
    }

    /**
     * Specifies the current sorting algorithm.
     *
     * @param currentSort - the SortType to be set to
     */
    public void setCurrentSort(SortType currentSort) {
        sortingManager.setCurrentSort(currentSort);
    }

    /**
     * Resets the colors of the columns, and shuffles columns.
     */
    public void reset() {
        sortingManager.setSorting(false);
        columns.forEach(c -> c.setState(Column.State.NORMAL));
        Collections.shuffle(columns);
        repaint();
    }

    /**
     * Updates the amount of columns.
     *
     * @param count - the count to be set to.
     */
    public void updateColumnCount(int count) {
        this.columnCount = count;
        initColumns();
        repaint();
    }
}
