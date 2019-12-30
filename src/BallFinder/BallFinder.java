enum coordinate {X, Y}

class Player {
    int[] position = new int[2];
    int moves = 0;
    int[][] path = new int[20][2];
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
        return this.position; 
    }
    int[] moveUp() {
        this.position[coordinate.Y.ordinal()]++;
        return this.position;
    }
    int[] nextMove(Ball ball) {
        if(this.position[coordinate.X.ordinal()] < ball.position[coordinate.X.ordinal()]) {
            this.moveRight();
        }
        else if(this.position[coordinate.Y.ordinal()] < ball.position[coordinate.Y.ordinal()]) {
            this.moveUp();
        }
        //System.out.format("init (%d, %d)%n",  this.position[coordinate.X.ordinal()], this.position[coordinate.Y.ordinal()]);
        return this.position;
    } 
    int[][] findPath(Grid grid) {
        this.path[this.moves++] = this.position; 
        System.out.format("(%d, %d) -> ",  this.position[coordinate.X.ordinal()], this.position[coordinate.Y.ordinal()]);
        while(this.position[coordinate.X.ordinal()] != grid.ball.position[coordinate.X.ordinal()] || this.position[coordinate.Y.ordinal()] != grid.ball.position[coordinate.Y.ordinal()]) {
            this.path[this.moves++] = nextMove(grid.ball);
            //int[] pos = nextMove(grid.ball);
            System.out.format("(%d, %d) -> ", this.position[coordinate.X.ordinal()], this.position[coordinate.Y.ordinal()]);
        }
        return this.path;
    }
}

class Ball {
    int[] position = new int[2];
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
        for(int idx = 0; idx < player.moves; idx++) {
            System.out.format("(%d, %d) -> ", path[idx][coordinate.X.ordinal()], path[idx][coordinate.Y.ordinal()]);
        }
    }
        public static void main(String args[]) {
        BallFinder game = new BallFinder();
        System.out.println(game.grid.ball.position[0] + ", " + game.grid.ball.position[1]);
        int[][] path = game.player.findPath(game.grid);
        System.out.println(game.player.moves + " Moves");
    }
}