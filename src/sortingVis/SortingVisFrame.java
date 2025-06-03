package sortingVis;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * JFrame for the Sorting Gui. Creates all the interactive components.
 *
 * @author Collin Phipps
 */
public class SortingVisFrame extends JFrame {
    private static final Color backgroundColor = new Color(51, 55, 61);
    private final VisualPanel visPanel;
    private Dictionary<Integer, JLabel> labels;
    private static final Font font = new Font("Sanserif", Font.BOLD, 18);

    /**
     * Creates a new SortingVisFrame. Sets the look and feel,
     * and initializes GUI components.
     */
    public SortingVisFrame() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setUpSliderLabels();

        setPreferredSize(new Dimension(700, 700));
        setTitle("Sorting Visualizer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(700, 100));
        top.setBackground(backgroundColor);
        mainPanel.add(top, BorderLayout.NORTH);

        JButton sort = new JButton("Sort");
        sort.setFont(font);
        sort.addActionListener(e -> this.sort());
        sort.setPreferredSize(new Dimension(100, 50));
        top.add(sort);

        JComboBox<String> sorts = new JComboBox<>(SortType.getSortNames());
        sorts.setFont(font);
        top.add(sorts);
        sorts.addActionListener(e -> {
            int index = sorts.getSelectedIndex();
            SortType currentSort = SortType.getSortFromIndex(index);
            setCurrentSort(currentSort);
        });
        sorts.setPreferredSize(new Dimension(175, 50));

        JButton reset = new JButton("Reset");
        reset.setFont(font);
        top.add(reset);
        reset.addActionListener(e -> reset());
        reset.setPreferredSize(new Dimension(100, 50));

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(100, 700));
        left.setBackground(backgroundColor);
        mainPanel.add(left, BorderLayout.WEST);

        JSlider columnCount = new JSlider(SwingConstants.VERTICAL, 10, 100, 50);
        columnCount.setMajorTickSpacing(25);
        columnCount.setMinorTickSpacing(5);
        columnCount.setPaintLabels(true);
        columnCount.addChangeListener(e -> updateColumnCount(columnCount.getValue()));
        columnCount.setPreferredSize(new Dimension(50, 600));
        left.add(columnCount);
        columnCount.setLabelTable(labels);

        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(100, 700));
        right.setBackground(backgroundColor);
        mainPanel.add(right, BorderLayout.EAST);

        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(700, 100));
        bottom.setBackground(backgroundColor);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        visPanel = new VisualPanel(50);
        mainPanel.add(visPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
    }

    /**
     * Method called when "Sort" button is pressed.
     * Calls the sort method from VisualPanel.
     */
    private void sort() {
        visPanel.sort();
    }

    /**
     * Called when the SortType is changed through the
     * JComboBox sorts. Changes the current sorting algorithm.
     *
     * @param currentSort - the sort to be set to
     */
    private void setCurrentSort(SortType currentSort) {
        visPanel.setCurrentSort(currentSort);
    }

    /**
     * Method called when "Reset" button is pressed.
     * Called the reset method from VisualPanel.
     */
    private void reset() {
        visPanel.reset();
    }

    /**
     * Updates the column count when the JSlider
     * columnCount is changed.
     *
     * @param count - the count to be set to
     */
    private void updateColumnCount(int count) {
        visPanel.updateColumnCount(count);
        reset();
    }

    /**
     * Sets up the labels for the JSlider component.
     */
    private void setUpSliderLabels() {
        labels = new Hashtable<>();
        for (int i = 0; i <= 100; i += 20) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setForeground(Color.WHITE);
            labels.put(i, label);
        }
    }
}
