package com.consoletetris.model;

import java.awt.event.KeyEvent;

//the game class with main method
public class Tetris {

    private Field field;
    private Figure figure;
    static Tetris game;
    private boolean isGameOver;


    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public void run() throws Exception {
        //Create the KeyboardObserver object and invoke the start()
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //put the false value in isGameOver field
        isGameOver = false;
        //Creating a random figure at the top center of game field
        figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0);

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
            figure.up();                    //go up
            figure.landed();                //land
            field.deleteFullLines();        //deleteFullLines
            figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0); //create new random figure
        }
    }
    public static void main(String[] args) throws Exception {
        game = new Tetris(); //game object
        game.run(); //starts the game
    }
}
