import java.util.*;
class SimpleDotGame{
    int[] locationCells;
    int numberOfHits;
    String checkYourself(String userGuess){
        int userGuessInt  = Integer.valueOf(Integer.parseInt(userGuess));
        for(int i=0; i<locationCells.length; i++){
            if(locationCells[i] == userGuessInt) {
                numberOfHits++;
                if(numberOfHits == locationCells.length) {
                    return("kill");
                }
                else {
                    return("hit");
                }
            }
        }
        return("miss");
    }

    void setLocationCells(int[] cellLocations) {
        locationCells = cellLocations;
    }
}

public class P04{
    public static void main(String args[]){
        int[] location = new int[]{1,2,3};
        Scanner scan = new Scanner(System.in);
        SimpleDotGame game = new SimpleDotGame();
        game.setLocationCells(location);
        String state;
        int counter = 0;
        while(true) {
            String userGuess = scan.next();
            state = game.checkYourself(userGuess);
            System.out.println(state);
            counter++;
            if(state == "kill") {
                break;
            }
        }
        System.out.println("You took " + counter + " guesses");
        scan.close();
    }
}