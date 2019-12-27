package PackagesExercise.src.com.headfirstjava;

enum coordinate {X, Y}

class Player {
    int[] position;
    int moves = 0;
    int[][] path;
    Player() {
        this.position[coordinate.X.ordinal()] = 0;
        this.position[coordinate.Y.ordinal()] = 0;
    }
    Player(int X, int Y) {
        this.position[coordinate.X.ordinal()] = X;
        this.position[coordinate.Y.ordinal()] = Y;
    }
    int[] moveRight() {
        this.position[coordinate.X.ordinal()]++;
        return position; 
    }
    int[] moveUp() {
        this.position[coordinate.Y.ordinal()]++;
        return position;
    }
    int[] nextMove(Ball ball) {
        if(this.position[coordinate.X.ordinal()] < ball.position[coordinate.X.ordinal()]) {
            this.moveRight();
        }
        else if(this.position[coordinate.Y.ordinal()] < ball.position[coordinate.Y.ordinal()]) {
            this.moveUp();
        }
        return this.position;
    } 
    int[][] findPath(Grid grid) {
        this.path[this.moves++] = this.position; 
        while(this.position[coordinate.X.ordinal()] != grid.ball.position[coordinate.X.ordinal()] || this.position[coordinate.Y.ordinal()] != grid.ball.position[coordinate.Y.ordinal()]) {
            this.path[this.moves++] = nextMove(grid.ball);
        }
        return this.path;
    }
}

class Ball {
    int[] position;
    Ball() {
        this.position[coordinate.X.ordinal()] = (int)(Math.random()*10);
        this.position[coordinate.Y.ordinal()] = (int)(Math.random()*10);
    }
    Ball(int X, int Y) {
        this.position[coordinate.X.ordinal()] = X;
        this.position[coordinate.Y.ordinal()] = Y;
    }
}

class Grid {
    Ball ball = new Ball();
}

public class BallFinder {
    Grid grid = new Grid();
    Player player = new Player();
    void displayPath(int[][] path) {
        for(int[] position: path) {
            System.out.format("(%d, %d)-> ", position[coordinate.X.ordinal()], position[coordinate.Y.ordinal()]);
        }
    }
    public static void main(String args[]) {
        BallFinder game = new BallFinder();
        int[][] path = game.player.findPath(game.grid);
        game.displayPath(path);
    }
    

}