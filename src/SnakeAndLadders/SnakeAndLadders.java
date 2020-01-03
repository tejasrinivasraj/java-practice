import java.util.*;

class Dice{
    int roll() {
        return((int)(Math.random()*6) + 1);
    }
}

class Player {
    String playerName;
    int locationNumber = 0;
    Player(String playerName) {
        this.playerName = playerName;
    }
    int rollDice(Dice dice) {
        return dice.roll();
    }
    void climb(int ladderTop) {
        this.locationNumber = ladderTop;
    }
}
class Snake {
    int snakeHead;
    int snakeTail;
    Snake(int snakeHead, int snakeTail) {
        this.snakeHead = snakeHead;
        this.snakeTail = snakeTail;
    }
    Player eat(Player player) {
        player.locationNumber = this.snakeTail;
        return player;
    }
}
class Ladder {
    int ladderBottom;
    int ladderTop;
    Ladder(int ladderBottom, int ladderTop) {
        this.ladderBottom = ladderBottom;
        this.ladderTop = ladderTop;
    }
}

public class SnakeAndLadders {
    HashMap<Integer, Snake> snakes = new HashMap<>();
    HashMap<Integer, Ladder> ladders = new HashMap<>();
    int numberOfPlayers = 0;
    Player[] players;

    // requirements
    int playersFinished = 0;
    int[] completedPlayers = new int[this.numberOfPlayers];
    Dice dice = new Dice();

    SnakeAndLadders(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.players = new Player[this.numberOfPlayers];
    }

    void loadSnakes() {
        Snake snake1 = new Snake(16, 7);
        snakes.put(16, snake1);
        Snake snake2 = new Snake(52, 28);
        snakes.put(52, snake2);
        Snake snake3 = new Snake(78, 25);
        snakes.put(78, snake3);
        Snake snake4 = new Snake(93, 89);
        snakes.put(93, snake4);
        Snake snake5 = new Snake(95, 75);
        snakes.put(95, snake5);
        Snake snake6 = new Snake(99, 21);
        snakes.put(99, snake6);
    }

    void loadLadders() {
        Ladder ladder1 = new Ladder(2, 45);
        ladders.put(2, ladder1);
        Ladder ladder2 = new Ladder(4, 27);
        ladders.put(4, ladder2);
        Ladder ladder3 = new Ladder(9, 31);
        ladders.put(9, ladder3);
        Ladder ladder4 = new Ladder(47, 84);
        ladders.put(47, ladder4);
        Ladder ladder5 = new Ladder(70, 87);
        ladders.put(70, ladder5);
        Ladder ladder6 = new Ladder(71, 91);
        ladders.put(71, ladder6);
    }
    void loadBoard() {
        this.loadSnakes();
        this.loadLadders();
    }

    void getPlayersData() {
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < this.numberOfPlayers; i++) {
            System.out.format("Player%d Name: ", i + 1);
            String playerName = scan.nextLine();
            Player player = new Player(playerName);
            this.players[i] = player;
        }
    }
    void play() {
        Scanner scan = new Scanner(System.in);
        int playerIndex = 0;
        Player currentPlayer = players[playerIndex];
        while(playersFinished < this.numberOfPlayers - 1) {
            if(completedPlayers[playerIndex] == 0) {
                System.out.format("\nPress enter, %s\n", currentPlayer.playerName);
                scan.nextLine();
                System.out.format("%s, you are at %d\n", currentPlayer.playerName, currentPlayer.locationNumber);
                currentPlayer = this.players[playerIndex];
                int diceValue = currentPlayer.rollDice(dice);
                if(currentPlayer.locationNumber + diceValue > 100) {
                    System.out.format("\nMove not valid, %s!\n", currentPlayer.playerName);
                    playerIndex++;
                    playerIndex %= this.numberOfPlayers;
                    currentPlayer = players[playerIndex];
                    continue;
                }
                if(this.snakes.get(currentPlayer.locationNumber + diceValue) != null) {
                    currentPlayer.locationNumber = this.snakes.get(currentPlayer.locationNumber + diceValue).snakeTail;
                    System.out.format("\nYou are bitten by snake.\n");
                }
                else if(this.ladders.get(currentPlayer.locationNumber + diceValue) != null) {
                    currentPlayer.climb(this.ladders.get(currentPlayer.locationNumber + diceValue).ladderTop);
                    System.out.format("\nYou have climbed a ladder.\n");
                }
                else {
                    currentPlayer.locationNumber += diceValue;
                    if(currentPlayer.locationNumber == 100) {
                        System.out.format("\n\t%s you finished at %d place\n", currentPlayer.playerName, playersFinished + 1);
                        playersFinished++;
                        completedPlayers[playerIndex] = 1;
                    }
                }
                System.out.format("\n%s, you are at %d after move\n", currentPlayer.playerName, currentPlayer.locationNumber);
            }
            playerIndex++;
            playerIndex %= this.numberOfPlayers;
            currentPlayer = players[playerIndex];
        }
        System.out.println("\n\tEnd of Game!\n\n");
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\tWelcome to SnakeAndLadders\n");
        System.out.println("Number of Players: ");
        int numberOfPlayers = scan.nextInt();
        SnakeAndLadders game = new SnakeAndLadders(numberOfPlayers);
        game.getPlayersData();
        game.loadBoard();
        game.play();
    }
}