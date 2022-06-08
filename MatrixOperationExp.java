import Jama.Matrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.ejml.simple.SimpleMatrix;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MatrixOperationExp {
    public static void main(String[] args) throws IOException {
        int[] sizes = new int[20];
        for (int i = 0; i < 20; i++) {
            sizes[i] = (i + 1) * 10;
        }
        int[] commonsMath3OperationTime = new int[sizes.length];
        int[] ejmlOperationTime = new int[sizes.length];
        int[] jamaOperationTime = new int[sizes.length];
        FileWriter fileWriter = new FileWriter("D:\\mul-res.csv");
        String title = "size,commonMath3,ejml,jama\n";
        fileWriter.append(title);
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            System.out.format("epoch=%d \r\n", size);
            double[][] matrixData1 = new double[size][size];
            double[][] matrixData2 = new double[size][size];
            for (int k = 0; k < size; k++) {
                for (int j = 0; j < size; j++) {
                    Random random = new Random();
                    matrixData1[k][j] = random.nextDouble();
                    matrixData2[k][j] = random.nextDouble();
                }
            }
            commonsMath3OperationTime[i] = commonsMath3Operation(matrixData1, matrixData2);
            ejmlOperationTime[i] = ejmlOperation(matrixData1, matrixData2);
            jamaOperationTime[i] = jamaOperation(matrixData1, matrixData2);
            String line = size + "," + commonsMath3OperationTime[i] + "," + ejmlOperationTime[i] + ","
                    + jamaOperationTime[i] + "\n";
            fileWriter.append(line);
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public static int commonsMath3Operation(double[][] matrixData1, double[][] matrixData2) {
        RealMatrix matrix1 = new Array2DRowRealMatrix(matrixData1);
        RealMatrix matrix2 = new Array2DRowRealMatrix(matrixData2);
        long startTime = System.currentTimeMillis();
        RealMatrix resMatrix = matrix1.multiply(matrix2);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    public static int ejmlOperation(double[][] matrixData1, double[][] matrixData2) {
        SimpleMatrix matrix1 = new SimpleMatrix(matrixData1);
        SimpleMatrix matrix2 = new SimpleMatrix(matrixData2);
        long startTime = System.currentTimeMillis();
        SimpleMatrix resMatrix = matrix1.mult(matrix2);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    public static int jamaOperation(double[][] matrixData1, double[][] matrixData2) {
        Matrix matrix1 = new Matrix(matrixData1);
        Matrix matrix2 = new Matrix(matrixData2);
        long startTime = System.currentTimeMillis();
        Matrix resMatrix = matrix1.times(matrix2);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }
}
