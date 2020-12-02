package com.trp;

import lombok.SneakyThrows;

import java.io.*;
import java.util.*;

public class Lab1  {
    private static int[][] matrix = {
            {100, 80, 40},
            {70, 90, 60},
            {60, 70, 80}
    };

    private static double[] probabilitiesArray = {0.5, 0.35, 0.15};

    public Lab1() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {
        start();
    }

    public static void start(){
        System.out.println("Ваальда");
        wald(matrix);

        System.out.println("Гурвіца");
        hurvicz(matrix ,0.5);

        System.out.println("Лапласа");
        laplas(matrix);

        System.out.println("Байєса-Лапласа");
        bayeslaplas(matrix, probabilitiesArray);

        System.out.println("Модальний");
        modal(matrix, probabilitiesArray);
    }

    private static void modal(int matrix[][], double[] probabilitiesArray) {
        int length = matrix.length;
        int resultArray[] = new int[length];
        int maxProbability = getMaxIndex(probabilitiesArray);
        for (int i = 0; i < length; i++) {
            resultArray[i] = matrix[i][maxProbability];
        }
        int result = getMaxIndex(resultArray);
        showResults(matrix, resultArray, ++result);
    }

    public static void showResults(int[][] matrix, double[] resultArray,
                                   int result) {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(resultArray[i]);
        }
        System.out.println("Best alternative: " + result + "\n");
    }

    public static void showResults(int[][] matrix, int[] resultArray,
                                   int result) {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(resultArray[i]);
        }
        System.out.println("Best alternative: " + result + "\n");
    }

    private static void laplas(int matrix[][]) {
        double resultArray[] = new double[matrix.length];
        double sum;
        for (int i = 0; i < matrix.length; i++) {
            sum = 0.0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j]/matrix.length;
            }
            resultArray[i] = sum;
        }
        int result = getMaxIndex(resultArray);
        showResults(matrix, resultArray, ++result);
    }


    private static void bayeslaplas(int matrix[][], double[] probabilitiesArray) {
        double resultArray[] = new double[matrix.length];
        double sum;
        for (int i = 0; i < matrix.length; i++) {
            sum = 0.0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += probabilitiesArray[j] * matrix[i][j];
            }
            resultArray[i] = sum;
        }
        int result = getMaxIndex(resultArray);
        showResults(matrix, resultArray, ++result);
    }

    private static void hurvicz(int matrix[][], double goodConditions) {
        goodConditions = 0.7;
        int length = matrix.length;
        double resultArray[] = new double[length];
        for (int i = 0; i < length; i++) {
            resultArray[i] = goodConditions * getMax(matrix[i]) +
                    (1 - goodConditions) * getMin(matrix[i]);
        }
        int result = getMaxIndex(resultArray);
        showResults(matrix, resultArray, ++result);
    }

    private static void wald(int matrix[][]) {
        int length = matrix.length;
        int minimumArray[] = new int[length];
        for (int i = 0; i < length; i++) {
            minimumArray[i] = getMin(matrix[i]);
        }
        int result = getMaxIndex(minimumArray);
        showResults(matrix, minimumArray, ++result);
    }
    private static int getMaxIndex(double[] probabilitiesArray) {
        double max = probabilitiesArray[0];
        int maxi = 0;
        for (int i = 1; i < probabilitiesArray.length; i++) {
            if (probabilitiesArray[i] > max) {
                max = probabilitiesArray[i];
                maxi = i;
            }
        }
        return maxi;
    }

    private static int getMaxIndex(int[] probabilitiesArray) {
        int max = probabilitiesArray[0];
        int maxi = 0;
        for (int i = 1; i < probabilitiesArray.length; i++) {
            if (probabilitiesArray[i] > max) {
                max = probabilitiesArray[i];
                maxi = i;
            }
        }
        return maxi;
    }
    private static int getMinIndex(double[] probabilitiesArray) {
        double min = probabilitiesArray[0];
        int mini = 0;
        for (int i = 1; i < probabilitiesArray.length; i++) {
            if(probabilitiesArray[i] < min){
                min = probabilitiesArray[i];
                mini = i;
            }
        }
        return mini;
    }
    private static int getMinIndex(int[] probabilitiesArray) {
        int min = probabilitiesArray[0];
        int mini = 0;
        for (int i = 1; i < probabilitiesArray.length; i++) {
            if(probabilitiesArray[i] < min){
                min = probabilitiesArray[i];
                mini = i;
            }
        }
        return mini;
    }

    private static int getMax(int[] matrix) {
        int max = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            if(matrix[i] > max){
                max = matrix[i];
            }
        }
        return max;
    }
    private static double getMax(double[] matrix) {
        double max = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            if(matrix[i] > max){
                max = matrix[i];
            }
        }
        return max;
    }

    private static int getMin(int[] matrix) {
        int min = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            if(matrix[i] < min){
                min = matrix[i];
            }
        }
        return min;
    }
    private static double getMin(double[] matrix) {
        double min = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            if(matrix[i] < min){
                min = matrix[i];
            }
        }
        return min;
    }
}
