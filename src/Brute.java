/*************************************************************************
 * Name: Xin Yi Yong
 *
 * Compilation:  javac Brute.java
 *
 * Description: Brute force algorithm to find collinear points.
 *
 *************************************************************************/

import java.util.Arrays;

public class Brute {
    private int N;
    private Point[] points;

    public static void main(String[] args) {
        String fileName = args[0];
        //String fileName = "collinear\\rs1423.txt";
        Brute brute = new Brute();
        brute.rescale();
        brute.readFile(fileName);
        brute.findCollinear();
    }

    // Rescale coordinates and turn on animation mode
    private void rescale() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
    }

    // Read input from file
    private void readFile(String fileName) {
        In input = new In(fileName);
        N = input.readInt();
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = input.readInt();
            int y = input.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }
        Arrays.sort(points);
    }

    // Find collinear points
    private void findCollinear() {
        if (N >= 3) {
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    for (int k = j + 1; k < N; k++) {
                        if (isCollinear(points[i], points[j], points[k])) {
                            for (int l = k + 1; l < N; l++) {
                                if (isCollinear(points[i], points[j], points[k], points[l])) {
                                    points[i].drawTo(points[l]);
                                    printOutput(points[i], points[j], points[k], points[l]);
                                }
                            }
                        }
                    }
                }
            }
            StdDraw.show(0);
        }
    }

    // Check if the four points are collinear
    private boolean isCollinear(Point a, Point b, Point c, Point d) {
        return (a.slopeTo(b) == a.slopeTo(c)) && (a.slopeTo(b) == a.slopeTo(d));
    }

    // Check if the four points are collinear
    private boolean isCollinear(Point a, Point b, Point c) {
        return a.slopeTo(b) == a.slopeTo(c);
    }

    // Print collinear points
    private void printOutput(Point a, Point b, Point c, Point d) {
        System.out.println(a.toString() + " -> " + b.toString() + " -> " + c.toString() + " -> " + d.toString());
    }
}
