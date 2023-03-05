package com.consoletetris.model;

import java.awt.event.KeyEvent;

//The game class with main method
public class Tetris {

    private final Field field;
    private Figure figure;
    static Tetris game;
    boolean isGameOver;

    public Tetris(int width, int height) {
        field = new Field(width, height);
        figure = null;
    }

    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public void run() throws Exception {
        //Create the KeyboardObserver object and invoke the start()
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //Creating a random figure at the top center of game field
        figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0);
        //Initiate isGameOver with false
        isGameOver = false;

        //While game is not over
        while (!isGameOver) {
            //Keyboard is active
            if (keyboardObserver.hasKeyEvents()) {
                //Get event from the queue
                KeyEvent event = keyboardObserver.getEventFromTop();
                //If event equals 'q' stop the game
                if (event.getKeyChar() == 'q') return;
                //Moving left
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    figure.left();
                    //Moving right
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    figure.right();
                    //Turn around
                else if (event.getKeyCode() == 12)
                    figure.rotate();
                    //Move to the bottom
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    figure.downMaximum();
            }

            step();             //next step
            field.print();      //print the current field state
            Thread.sleep(300);  //pause
        }

        //the end of the game
        System.out.println("Game Over");
    }

    public void step() {
        //Figure goes down by one line for one step
        figure.down();

        //If line is not empty
        if (!figure.isCurrentPositionAvailable()) {
            figure.up();                                                              //go up
            figure.landed();                                                          //land
            isGameOver = figure.getY() <= 1;                                          //Figure is crossed the top - Game is over
            field.deleteFullLines();                                                  //deleteFullLines
            figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0); //create new random figure
        }
    }
    public static void main(String[] args) throws Exception {
        game = new Tetris(10, 20); //game object
        game.run(); //starts the game
    }
}
