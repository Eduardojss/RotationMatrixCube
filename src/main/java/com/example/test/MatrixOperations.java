package com.example.test;

import processing.core.PVector;

public class MatrixOperations {

    public float[][] vectorToMatrix(PVector v) {
        return new float[][] { { v.x }, { v.y }, { v.z } };
    }

    public PVector matrixToVector(float[][] m) {
        PVector v = new PVector(m[0][0], m[1][0]);
        if (m.length > 2) {
            v.z = m[2][0];
        }
        return v;
    }

    public PVector matmulv(float[][] a, PVector b) {
        float[][] m = vectorToMatrix(b);
        return matmul(a, m);
    }

    public PVector matmul(float[][] a, float[][] b) {
        int colsA = a[0].length;
        int rowsA = a.length;
        int colsB = b[0].length;
        int rowsB = b.length;

        if (colsA != rowsB) {
            System.out.println("Columns of A must match rows of B");
            return null;
        }

        float[][] result = new float[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                float sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return matrixToVector(result);
    }

    public void setup() {
        // PVector result = matmul(projection, v);
        // PVector v = matrixToVector(result);
        // System.out.println(v);
    }
}
