package com.consoletetris.model;

public class Field {
    private int width;
    private int height;
    private int[][] matrix;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
        matrix = new int[height][width];
    }

    void print() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0)
                    System.out.print(" ");
                else
                    System.out.print("X");
            }
            System.out.println();
        }
    }

    public void removeFullLines() {

    }

    public Integer getValue(int x, int y) {
        return null;
    }

    public void setValue(int x, int y, int value) {

    }
}
