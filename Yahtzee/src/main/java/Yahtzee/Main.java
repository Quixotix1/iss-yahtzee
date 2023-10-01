
package Yahtzee;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author pres1680
 */
public class Main extends Game {
    static Game game = new Game();
    static Checker check = new Checker();
    int playerTurn = 0; //0 indicates player one's turn
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int turns = 0;
        int playerTurn = 0;
        
        ArrayList<Integer> dice = new ArrayList<Integer>();
        int[] p1Score = new int[13];
        int[] p2Score = new int[13];
        boolean[] p1Taken = new boolean[13];
        boolean[] p2Taken = new boolean[13];
        boolean[] poss = new boolean[13];
        

        int[][] score = {p1Score, p2Score};
        boolean[][] taken = {p1Taken, p2Taken};
        
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                score[i][j] = 0;
                taken[i][j] = false;
            }
        }
        
        
        for (int i = 0; i < 26; i++) // gameplay loop
        {
            dice = game.run();
            poss = check.checkPossibilities(dice, taken[playerTurn]);
            boolean trash = trashCheck(poss);
            
            while (true) { //validate the scoring options
                System.out.print("Rolls: ");
                for(int die : dice) {
                    System.out.printf("%d ", die);
                }
                System.out.println("\nSelect a row: ");
                printCard(score, taken);
                Scanner in = new Scanner(System.in);
                Scoring scoreThing = new Scoring();
                int decision = in.nextInt() - 1;
                if (decision > 12 || !poss[decision]) { // chooses wrong rows
                    System.out.println("Invalid Row");
                    continue;
                } else if (!trash) {
                    if (decision == 11) { // yahtzee 
                        score[playerTurn][decision] = scoreThing.yahtzee(score[playerTurn][decision]);
                    } else {
                        score[playerTurn][decision] = scoreThing.scorer(decision, dice);
                    }
                }
                taken[playerTurn][decision] = true;
                playerTurn = (playerTurn + 1) % 2;
                game.resetHeldDie();
                break;
            }
            printCard(score, taken);
        }
        int p1Total = 0;
        int p2Total = 0;
        for (int i = 0; i < 6; i++) {
            p1Total += score[0][i];
        }
        for (int i = 0; i < 6; i++) {
            p1Total += score[1][i];
        }
        if(p1Total >= 63) {
            p1Total += 35;
        }
        if(p2Total >= 63) {
            p2Total += 35;
        }
        for (int i = 6; i < 13; i++) {
            p1Total += score[0][i];
        }
        for (int i = 6; i < 13; i++) {
            p1Total += score[1][i];
        }
        System.out.println("Player 1 Total: " + p1Total);
        System.out.println("Player 2 Total: " + p2Total);
    }
    public static boolean trashCheck (boolean[] poss) { // check if 
        int trashCount = 0;
        for (boolean x : poss) {
            if (x) {
                break;
            } else {
                trashCount++;
            }
        }
        if (trashCount == 13) {
            return true;
        }
        return false;
    }
    public static void printCard(int[][] scores, boolean[][] taken) {
        String[] names = {"1) Ones", "2) Twos", "3) Threes", "4) Fours", "5) Fives", "6) Sixes", "7) Three of a kind", "8) Four of a kind",
            "9) Full House", "10) Small Straight", "11) Large Straight", "12) Yahtzee", "13) Chance"};
        int lenArray = names.length;
        int catColWidth = 19;
        int pColWidth = "P1 Score".length();
        System.out.println("\nCategory           |P1 Score|P2 Score");
        for (int i = 0; i < lenArray; i++) {
            String rowName = names[i];
            System.out.print(rowName);
            for (int j = 0; j < catColWidth - rowName.length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|");
            if (taken[0][i]) {
                String score = String.valueOf(scores[0][i]);
                System.out.print(score);
                for (int j = 0; j < pColWidth - score.length(); j++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            } else {
                System.out.print("        |");
            }
            if (taken[1][i]) {
                String score = String.valueOf(scores[1][i]);
                System.out.print(score);
            }
            System.out.println();
        }
    }  
}
