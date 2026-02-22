package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
2D Array Advanced Operations in Java
===========================================================

1) Transpose of a matrix
2) Sum of main and secondary diagonals
3) Spiral traversal of a matrix
4) Testing Examples
===========================================================
*/

public class MatrixAdvancedOperations {

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        // 1. Transpose of matrix
        int[][] transposed = transposeMatrix(matrix);
        System.out.println("Transposed Matrix:");
        printMatrix(transposed);

        // 2. Sum of diagonals
        int mainDiagonalSum = sumMainDiagonal(matrix);
        int secondaryDiagonalSum = sumSecondaryDiagonal(matrix);
        System.out.println("Sum of main diagonal: " + mainDiagonalSum);
        System.out.println("Sum of secondary diagonal: " + secondaryDiagonalSum);

        // 3. Spiral traversal
        System.out.println("Spiral traversal of matrix:");
        spiralTraversal(matrix);
    }

    // Print matrix
    public static void printMatrix(int[][] matrix) {
        for(int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Transpose matrix
    public static int[][] transposeMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] transposed = new int[m][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    // Sum of main diagonal
    public static int sumMainDiagonal(int[][] matrix) {
        int sum = 0;
        for(int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }

    // Sum of secondary diagonal
    public static int sumSecondaryDiagonal(int[][] matrix) {
        int n = matrix.length;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += matrix[i][n - 1 - i];
        }
        return sum;
    }

    // Spiral traversal
    public static void spiralTraversal(int[][] matrix) {
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while(top <= bottom && left <= right) {
            // Traverse top row
            for(int j = left; j <= right; j++) System.out.print(matrix[top][j] + " ");
            top++;
            // Traverse right column
            for(int i = top; i <= bottom; i++) System.out.print(matrix[i][right] + " ");
            right--;
            // Traverse bottom row
            if(top <= bottom) {
                for(int j = right; j >= left; j--) System.out.print(matrix[bottom][j] + " ");
                bottom--;
            }
            // Traverse left column
            if(left <= right) {
                for(int i = bottom; i >= top; i--) System.out.print(matrix[i][left] + " ");
                left++;
            }
        }
        System.out.println();
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Transpose: O(n*m) time, O(n*m) space
Diagonal sums: O(n) time, O(1) space
Spiral traversal: O(n*m) time, O(1) extra space
===========================================================
*/
