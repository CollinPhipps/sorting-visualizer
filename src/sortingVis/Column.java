package sortingVis;

import java.awt.*;

/**
 * Represents a column. Contains the fields height and state.
 */
public class Column implements Comparable<Column> {
    private final double height;
    private State state;

    /**
     * enum containing the different states a column can have.
     */
    public enum State {
        NORMAL,
        ACTIVE,
        SORTED
    }

    /**
     * Creates a new Column, specified by a height.
     *
     * @param height the height
     */
    public Column(double height) {
        this.height = height;
        this.state = State.NORMAL;
    }

    /**
     * Returns the height of the Column.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns a color based on the state of the Column.
     *
     * @return the current State
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the current state of the Column.
     *
     * @param state - the State to be set to
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Returns a color based on the state of the Column.
     *
     * @return a Color object
     */
    public Color getColor() {
        return switch (state) {
            case ACTIVE -> new Color(255, 165, 0);
            case SORTED -> new Color(100, 255, 100);
            default -> new Color(193, 205, 222);
        };
    }

    /**
     * Compares two Columns. Columns are sorted by height strictly.
     *
     * @param o the Column to be compared.
     * @return a negative, positive, or zero int
     */
    @Override
    public int compareTo(Column o) {
        return Double.compare(this.height, o.height);
    }
}

