package HelloWorld;

import java.util.ArrayList;

/**
 * Created by xuch on 9/5/16.
 */
public class Solution
{
    public static void main(String[] args) {
        int cityWidth=5;
        int cityLength=7;
        int[] xCordinates ={2,4};
        int[] yCordinates={3,7};
        int[][] result = get2DLockerLocation(cityLength, cityWidth, xCordinates, yCordinates);
        printMatrix(result);
    }

    public static int[][] get2DLockerLocation(int xLength, int yWidth, int[] xCordinates, int[] yCordinates) {
        int[][] lockerDistances = new int[yWidth][xLength];
        for(int yaxis=0;yaxis<yWidth;yaxis++)
        {
            int xdistance = 0;
            int ydistance = 0;
            for(int xaxis=0; xaxis<xLength; xaxis++){
                int[] xDistances = new int[xCordinates.length];
                int[] yDistances = new int[yCordinates.length];
                for(int i=0;i<yCordinates.length;i++){
                    yDistances[i] = Math.abs(yCordinates[i] - (yaxis+1));

                }
                ydistance = minValue(yDistances);
                for(int j=0;j<xCordinates.length;j++){
                    xDistances[j] = Math.abs(xCordinates[j] - (xaxis+1));
                }
                xdistance = minValue(xDistances);
                lockerDistances[yaxis][xaxis] = xdistance + ydistance;
            }
        }
        return lockerDistances;
    }

    private static int minValue(int[] numbers) {
        if (numbers.length <= 0) return 0;
        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < result) result = numbers[i];
        }
        return result;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}