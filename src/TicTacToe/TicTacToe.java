import java.util.*;

class Coordinates {
    int x;
    int y;
    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Player {
    String playerName;
    Character playerMark;

    Player(String playerName, Character playerMark) {
        this.playerName = playerName;
        this.playerMark = playerMark;
    }

    ArrayList<Coordinates> playerMarks = new ArrayList<Coordinates>();

    Grid markBlock(Grid board, Coordinates currentCoordinates) {
        board.grid[currentCoordinates.x][currentCoordinates.y] = this.playerMark;
        return board;
    }
}
class Grid {
    int length;
    int width;
    Character[][] grid;
    Grid() {
        this.length = 3;
        this.width = 3;
        this.grid = new Character[3][3];
    }
    Grid(int length, int width) {
        this.length = length;
        this.width  = width;
        this.grid = new Character[length][width];
    }
    boolean isEmpty(Coordinates coordinates){
        return((grid[coordinates.x][coordinates.y] == null));
    }
}
public class TicTacToe {
    Player p1, p2;
    Grid board;
    int totalMoves = 0;
    TicTacToe() {
        this.board = new Grid();
    }
    TicTacToe(int x, int y){
        this.board = new Grid(x, y);
    }
    void getUserData() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Player 1 Name: ");
        String p1Name = scan.next();
        System.out.format("%s choose your mark: ", p1Name);
        Character p1Mark = scan.next().charAt(0);
        System.out.print("Player 2 Name: ");
        String p2Name = scan.next();
        System.out.format("%s choose your mark: ", p2Name);
        Character p2Mark = scan.next().charAt(0);

        this.p1 = new Player(p1Name, p1Mark);
        this.p2 = new Player(p2Name, p2Mark);
    }

    void displayBoard(Grid board) {
        System.out.println("\n");
        for(int i = 0; i < board.length; i++) {
            System.out.print("\t");
            for(int j = 0; j < board.width; j++) {
                if(board.grid[i][j] != null){
                    System.out.format("| %s ", board.grid[i][j]);
                }
                else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|\n");
        }
    }

    boolean checkVertically(Grid board) {
        for(int i = 0; i < board.width; i++) {
            Character mark = board.grid[0][i];
            int count = 0;
            for(int j = 0; j < board.length; j++) {
                if(board.grid[j][i] == mark && mark != null) {
                    count++;
                }
            }
            if(count == board.length) {
                return true;
            }
        }
        return false;
    }

    boolean checkHorziontally(Grid board) {
        for(int i = 0; i < board.length; i++) {
            Character mark = board.grid[i][0];
            int count = 0;
            for(int j = 0; j < board.width; j++) {
                if(board.grid[i][j] == mark && mark != null) {
                    count++;
                }
            }
            if(count == board.width) {
                return true;
            }
        }
        return false;
    }

    boolean checkDiagonally(Grid board) {
        Character mark = board.grid[0][0];
        int count = 0;
        for(int i = 0; i < board.width; i++) {
            if(board.grid[i][i] == mark) {
                count++;
            }
        }
        if(count == board.width) {
            return true;
        }
        mark = board.grid[0][board.width - 1];
        count = 0;
        for(int i = 0; i < board.width; i++) {
            if(board.grid[i][board.width - (i + 1)] == mark) {
                count++;
            }
        }
        if(count == board.width) {
            return true;
        }
        return false;
    }
    
    boolean checkWinner(Grid board){
        if(checkVertically(board) || checkHorziontally(board) || checkDiagonally(board)) {
            return true;
        }
        return false;
    }

    void start() {
        Scanner scan = new Scanner(System.in);
        Player currentPlayer = this.p1;
        while(true) {
            displayBoard(this.board);
            System.out.format("%s your move: ", currentPlayer.playerName);
            Integer x, y;
            x = scan.nextInt();
            y = scan.nextInt();
            Coordinates coordinates = new Coordinates(x, y);
            if(this.board.isEmpty(coordinates)) {
                this.board = currentPlayer.markBlock(this.board, coordinates);
            }
            else {
                System.out.format("\n Block is already occupied, %s!\n", currentPlayer.playerName);
                continue;
            }
            this.totalMoves++;
            // Check Winner
            if((this.totalMoves >= (2 * this.board.length) - 1) && checkWinner(this.board)) {
                displayBoard(this.board);
                System.out.format("\n\t%s Won!\n\n", currentPlayer.playerName);
                break;
            }
            else if(this.totalMoves == (this.board.width * this.board.length)) {
                System.out.println("\tDraw Match!");
                break;
            }
            // Swap current player
            currentPlayer = (currentPlayer == this.p1) ? this.p2 : this.p1;
        }
    }
    
    public static void main(String args[]) {
        TicTacToe game = new TicTacToe();
        System.out.println("\n\tWelcome to the TIC-TAC-TOE game\n");
        game.getUserData();
        game.start();
    }
}