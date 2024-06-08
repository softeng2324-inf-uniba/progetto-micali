package it.uniba.app.elements;
import java.util.Objects;

/**
 * A class representing a coordinate in a 2D grid.
 */
public class Coordinate {
    private int x;
    private int y;

    /**
     * Constructs a Coordinate with the specified x and y values.
     *
     * @param initialX the x value of the coordinate
     * @param initialY the y value of the coordinate
     */
    public Coordinate(final int initialX, final int initialY) {
        this.x = initialX;
        this.y = initialY;
    }

    /**
     * Gets the x value of the coordinate.
     *
     * @return the x value
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y value of the coordinate.
     *
     * @return the y value
     */
    public int getY() {
        return y;
    }

    /**
     * Checks if this coordinate is equal to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }

    /**
     * Returns the hash code of this coordinate.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Checks if this coordinate is adjacent to another coordinate.
     *
     * @param other the other coordinate
     * @return true if the coordinates are adjacent, false otherwise
     */
    public boolean isAdjacent(final Coordinate other) {
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        return (dx <= 1 && dy <= 1) && (dx + dy > 0);
    }

    /**
     * Returns the string representation of this coordinate in chess notation.
     *
     * @return the string representation in chess notation
     */
    @Override
    public String toString() {
        // Converts the numeric coordinates to chess coordinates (e.g., 0,0 becomes "a1")
        char column = (char) ('a' + y);
        int row = x + 1; // Adjusts the index for chess notation (1-based)
        return "" + column + row;
    }
}
