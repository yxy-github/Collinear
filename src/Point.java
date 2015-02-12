/*************************************************************************
 * Name: Xin Yi Yong
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {
    public final Comparator<Point> SLOPE_ORDER = new BySlope(); // Compare points by slope
    private final int x;                                        // x coordinate
    private final int y;                                        // y coordinate

    // Create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // Draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // Implement compare method
    private class BySlope implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (slopeTo(a) > slopeTo(b)) {
                return 1;
            }
            if (slopeTo(a) < slopeTo(b)) {
                return -1;
            }
            return 0;
        }
    }

    // Slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }
        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        if (this.y == that.y) {
            return 0;
        }
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    // Is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        }
        if (this.y > that.y) {
            return 1;
        }
        if (this.x < that.x) {
            return -1;
        }
        if (this.x > that.x) {
            return 1;
        }
        return 0;
    }

    // Return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point point1 = new Point(50, 50);
        Point point2 = new Point(0, 0);
        Point point3 = new Point(0, 0);
        System.out.println(point1.slopeTo(point2));
        System.out.println("Compare point 2 and point 3.");
        System.out.println(point1.SLOPE_ORDER.compare(point2, point3));
    }
}
