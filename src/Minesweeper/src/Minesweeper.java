import java.util.*;

public class Minesweeper {

    private Grid grid = new Grid();
    private Player player = new Player();
    private String mineFieldLayout;

    private void getMineFieldLayout() {
        System.out.print("Enter the minefield layout: ");
        Scanner scan = new Scanner(System.in);
        this.mineFieldLayout = scan.nextLine();
    }

    private void addMines() {
        String[] fieldLayout = this.mineFieldLayout.split(",", grid.getSize());
        for(int idx = 0; idx < this.grid.getSize(); idx++) {
            char[] rows = fieldLayout[idx].toCharArray();
            for(int rowIdx = 0; rowIdx < this.grid.getSize(); rowIdx++) {
                Coordinate coordinate = new Coordinate(idx, rowIdx);
                Cell obj;
                if(rows[rowIdx] == 'm') {
                    obj = new MineCell();
                }
                else {
                    obj = new Cell();
                }
                grid.setObject(coordinate, obj);
            }
        }
    }

    private void loadBoard() {
        getMineFieldLayout();
        //radomizeMineFieldLayout();
        addMines();
    }

    private void displayGrid() {
        for(int idx = 0; idx < this.grid.getSize(); idx++) {
            System.out.print("\t");
            for(int j = 0; j < this.grid.getSize(); j++) {
                Coordinate coordinate = new Coordinate(idx, j);
                if(this.grid.getObjectStatus(coordinate).equals("closed")) {
                    System.out.print("X ");
                }
                else if(this.grid.getObjectStatus(coordinate).equals("flagged")) {
                    System.out.print("F ");
                }
                else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    private Coordinate getCoordinate(String input) {
        String num1 = input.substring(input.indexOf('(') + 1, input.indexOf(','));
        String num2 = input.substring(input.indexOf(',') + 1, input.indexOf(')'));
        int x = Integer.parseInt(num1);
        int y = Integer.parseInt(num2);
        return new Coordinate(x, y);
    }
    private char getOperation(String input) {
        return input.charAt(0);
    }
    private String getUserOption() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter option: ");
        return(scan.nextLine());
    }
    private boolean isGameFinished() {
        int count = 0;
        for(int idx = 0; idx < this.grid.getSize(); idx++) {
            for(int idy = 0; idy < this.grid.getSize(); idy++) {
                Coordinate coordinate = new Coordinate(idx, idy);
                if(this.grid.isMine(coordinate) && this.grid.isFlagged(coordinate)) {
                    count++;
                }
                else if(!this.grid.isMine(coordinate) && this.grid.isOpened(coordinate)) {
                    count++;
                }
            }
        }
        return(count == this.grid.getSize()*this.grid.getSize());
    }

    private void play() {
        while(!isGameFinished()) {
            displayGrid();
            String input = this.getUserOption();
            Coordinate coordinate = this.getCoordinate(input);
            char operation = this.getOperation(input);
            if(this.grid.isOpened(coordinate)) {
                System.out.println("\nYou have already opened the block.");
                continue;
            }
            if (operation == 'o') {
                if (this.grid.isMine(coordinate)) {
                    System.out.println("\nYou have opened a mine.");
                    break;
                }
                else {
                    this.grid.openObject(coordinate);
                }
            }
            else {
                if(this.grid.isFlagged(coordinate)) {
                    System.out.println("\nYou have already flagged.");
                }
                else {
                    this.grid.flagObject(coordinate);
                }
            }
        }
        if(isGameFinished()) {
            displayGrid();
            System.out.format("\nCongratulation you have won the game, %s.", player.getName());
        }
    }

    private void start() {
        loadBoard();
        play();
    }
    public static void main(String args[]) {
        System.out.println("\n\tWelcome to Minesweeper!\n");
        Minesweeper game = new Minesweeper();
        game.start();
    }
}
