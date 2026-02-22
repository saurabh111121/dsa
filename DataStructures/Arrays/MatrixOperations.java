package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
2D Array / Matrix Operations in Java
===========================================================

1) Traversal
2) Calculate sum of all elements
3) Rotate matrix by 90 degrees (clockwise)
4) Testing Examples
===========================================================
*/

public class MatrixOperations {

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        // 1. Traversal (row-wise)
        System.out.println("Traversal row-wise:");
        traverseMatrix(matrix);

        // 2. Sum of all elements
        System.out.println("Sum of all elements: " + sumMatrix(matrix));

        // 3. Rotate matrix by 90 degrees clockwise
        int[][] rotated = rotateMatrix90Clockwise(matrix);
        System.out.println("Matrix after 90-degree clockwise rotation:");
        printMatrix(rotated);
    }

    // Print matrix
    public static void printMatrix(int[][] matrix) {
        for(int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Traverse matrix row-wise
    public static void traverseMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Sum of all elements
    public static int sumMatrix(int[][] matrix) {
        int sum = 0;
        for(int[] row : matrix) {
            for(int val : row) {
                sum += val;
            }
        }
        return sum;
    }

    // Rotate matrix 90 degrees clockwise
    public static int[][] rotateMatrix90Clockwise(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Traversal: O(n*m) time, O(1) extra space
Sum: O(n*m) time, O(1) extra space
Rotation: O(n*n) time, O(n*n) space (for new matrix)
===========================================================
*/

