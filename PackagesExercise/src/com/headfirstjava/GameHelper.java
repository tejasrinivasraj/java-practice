package com.headfirstjava;

class Ball{
    private int x_coordinate;
    private int y_coordinate;
    Ball(){
    }
    Ball(int x_coordinate, int y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }
    void setCoordinates(int x_coordinate, int y_coordinate){
        if(x_coordinate < 0) {
            System.out.println("Error");
        }
        else{
            this.x_coordinate = x_coordinate;
        }
        if(y_coordinate < 0){
            System.out.println("Error");
        }
        else{
            this.y_coordinate = y_coordinate;
        }
    }
    int[] getCoordinates(){
        int[] coordinates = new int[2];
        coordinates[0] = this.x_coordinate;
        coordinates[1] = this.y_coordinate;
        return coordinates;
    }
}

class BallFinderGame {
    int randomX = 9;
    int randomY = 9;
    Ball ball = new Ball(randomX, randomY);

    int[] getNextMove(int currentX, int currentY){
        int[] nextCoordinates = new int[2];
        if(currentX < ball.getCoordinates()[0]) {
            nextCoordinates[0] = ++currentX;
            nextCoordinates[1] = currentY;
            return nextCoordinates;
        }
        if(currentY < ball.getCoordinates()[1]) {
            nextCoordinates[0] = currentX;
            nextCoordinates[1] = ++currentY;
            return nextCoordinates;
        }
        nextCoordinates = ball.getCoordinates();
        return nextCoordinates;
    }
}

public class GameHelper {
    public static void main(String args[]){
        BallFinderGame game = new BallFinderGame();
        int[] ballCoordinates = new int[2];
        int currentX=0;
        int currentY=0;
        ballCoordinates = game.ball.getCoordinates();
        System.out.format("(%d,%d)%n", ballCoordinates[0], ballCoordinates[1]);
        while(currentX != ballCoordinates[0] || currentY != ballCoordinates[1]) {
            int[] nextCoordinates = new int[2];
            nextCoordinates = game.getNextMove(currentX, currentY);
            System.out.format("(%d,%d) -> ", currentX, currentY);
            currentX = nextCoordinates[0];
            currentY = nextCoordinates[1];
        }
        System.out.format("(%d,%d);", ballCoordinates[0], ballCoordinates[1]);
    }
}