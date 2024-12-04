import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrixB = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        int[][] result = new int[rowsA][colsB];
        ExecutorService executor = Executors.newFixedThreadPool(rowsA);
        for (int i = 0; i < rowsA; i++) {
            final int row = i; // Для использования в лямбда-выражении
            executor.submit(() -> multiplyRow(matrixA, matrixB, result, row));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Результирующая матрица:");
        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
    private static void multiplyRow(int[][] matrixA, int[][] matrixB, int[][] result, int row) {
        int colsB = matrixB[0].length;
        for (int j = 0; j < colsB; j++) {
            result[row][j] = 0;
            for (int k = 0; k < matrixA[0].length; k++) {
                result[row][j] += matrixA[row][k] * matrixB[k][j];
            }
        }
    }
}