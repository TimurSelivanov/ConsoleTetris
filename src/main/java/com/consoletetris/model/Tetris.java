package com.consoletetris.model;

//the game class with main method
public class Tetris {

    private Field field;
    private Figure figure;
    static Tetris game;


    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public void run() {
        //manage all game process
    }

    public void step() {
        //manage the on step of the game
    }
    public static void main(String[] args) {
        game = new Tetris(); //game object
        game.run(); //starts the game
    }
}
