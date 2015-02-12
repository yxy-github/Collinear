/*************************************************************************
 * Name: Xin Yi Yong
 *
 * Compilation:  javac Fast.java
 *
 * Description: Fast algorithm to find collinear points.
 *
 *************************************************************************/

import java.util.Arrays;

public class Fast {
    private int N;
    private Point[] points;

    public static void main(String[] args) {
        String fileName = args[0];
        //String fileName = "collinear\\rs1423.txt";
        Fast fast = new Fast();
        fast.rescale();
        fast.readFile(fileName);
        fast.findCollinear();
    }

    // Find collinear points
    private void findCollinear() {
        Point[] sorted = new Point[N - 1];
        if (N >= 3) {
            for (int i = 0; i < N; i++) {
                Point reference = points[i];
                for (int j = 0; j < N; j++) {
                    if (j < i) {
                        sorted[j] = points[j];
                    }
                    else if (j > i) {
                        sorted[j - 1] = points[j];
                    }
                }
                Arrays.sort(sorted, 0, N - 1, reference.SLOPE_ORDER);

                boolean[] slopeFlag = new boolean[N - 1];
                double[] slope = new double[N - 1];
                for (int j = 0; j < N - 1; j++) {
                    slope[j] = reference.slopeTo(sorted[j]);
                }
                for (int j = 1; j < N - 1; j++) {
                    slopeFlag[j] = slope[j] == slope[j - 1];
                }

                int k = 1;
                int count = 2;
                while (k < N - 1) {
                    if (slopeFlag[k]) {
                        count++;
                        if (!slopeFlag[k - 1]) {
                            count = 2;
                        }
                    }
                    if (count > 2) {
                        if (k < N - 2) {
                            if (!slopeFlag[k + 1]) {
                                Point[] subArray = new Point[count + 1];
                                subArray[0] = reference;
                                int m = 1;
                                for (int n = k - count + 1; n <= k; n++) {
                                    subArray[m] = sorted[n];
                                    m++;
                                }
                                generateOutput(subArray);
                                count = 2;
                            }
                        }
                        else if (k == N - 2) {
                            Point[] subArray = new Point[count + 1];
                            subArray[0] = reference;
                            int m = 1;
                            for (int n = k - count + 1; n <= k; n++) {
                                subArray[m] = sorted[n];
                                m++;
                            }
                            generateOutput(subArray);
                            count = 2;
                        }
                    }
                    k++;
                }
            }
        }
    }

    // Print and draw collinear points
    private void generateOutput(Point[] subArray) {
        if (subArray[1].compareTo(subArray[0]) >= 0) {
            subArray[0].drawTo(subArray[subArray.length - 1]);
            for (int i = 0; i < subArray.length - 1; i++) {
                System.out.print(subArray[i].toString() + "->");
            }
            System.out.println(subArray[subArray.length - 1].toString());
        }
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

    // Rescale coordinates and turn on animation mode
    private void rescale() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
    }
}
