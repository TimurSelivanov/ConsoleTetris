package com.consoletetris.model;

import java.util.ArrayList;

//This class describes the game field
public class Field {
    private final int width;
    private final int height;
    //Field matrix, 1 is taken,0 is free
    private int[][] matrix;

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
        matrix = new int[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //Method return 1 or 0 depends on cell is free or taken
    public Integer getValue(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            return matrix[y][x];

        return null;
    }

    //Method set 1 or 0 in the cell
    public void setValue(int x, int y, int value) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            matrix[y][x] = value;
    }

    void print() {
        //Create array with current game state
        int[][] canvas = new int[height][width];

        //Copy matrix to array
        for (int i = 0; i < height; i++) {
            System.arraycopy(matrix[i], 0, canvas[i], 0, width);
        }

        //Copy the figure to array
        int left = Tetris.game.getFigure().getX();
        int top = Tetris.game.getFigure().getY();
        int[][] brickMatrix = Tetris.game.getFigure().getMatrix();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (top + i >= height || left + j >= width) continue;
                if (brickMatrix[i][j] == 1)
                    canvas[top + i][left + j] = 2;
            }
        }

        //Print array starting from the top
        System.out.println("---------------------------------------------------------------------------\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int index = canvas[i][j];
                if (index == 0)
                    System.out.print(" . ");
                else if (index == 1)
                    System.out.print(" X ");
                else if (index == 2)
                    System.out.print(" X ");
                else
                    System.out.print("???");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
    }

    void deleteFullLines() {
        //List for lines
        ArrayList<int[]> lines = new ArrayList<>();

        //Copy array[][] to list
        for (int i = 0; i < height; i++) {
            //Counter for the lines
            int count = 0;
            for (int j = 0; j < width; j++) {
                count += matrix[i][j];
            }

            //If the line is not full add it to list
            if (count != width)
                lines.add(matrix[i]);
        }

        //Add lines at the beginning of the list
        while (lines.size() < height) {
            lines.add(0, new int[width]);
        }

        //List to array[][]
        matrix = lines.toArray(new int[height][width]);
    }
}
