
package Yahtzee;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author pres1680
 */
public class NewMain extends Game {
    static Game game = new Game();
    static checker check = new checker();
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
        boolean[] p1Poss = new boolean[13];
        boolean[] p2Poss = new boolean[13];
        

        int[][] score = {p1Score, p2Score};
        boolean[][] taken = {p1Poss, p2Poss};
        
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                score[i][j] = 0;
                taken[i][j] = false;
            }
        }
        
        
        while (true)
        {
            dice = game.run();
            if (playerTurn == 0)
            {
               p1Poss = check.checkPossibilities(dice);
               finishTurn(p1Poss, score[0], dice);
               playerTurn = 1;
            }
            else
            {
                p2Poss = check.checkPossibilities(dice);
                finishTurn(p2Poss, score[1], dice);
                playerTurn = 0;
            }
            
            
        }
    }
    
    public static void finishTurn(boolean[] possibilities, int[] score, ArrayList<Integer> dice)
    {
        Scanner scanner = new Scanner(System.in);
        int decision;
        
        Scoring scoreThing = new Scoring();
        
        System.out.println("Select a row: ");
        decision = scanner.nextInt() - 1;
        
        if (decision < 6) //123456
        {
            scoreThing.justNumbers(dice, decision);
        }
        else if (decision < 8 || decision == 12) //3/4oak, chance
        {
            
        }
        else if (decision < 11) //full house, lstraight/ smstraight
        {
            
        }
        else //yahtzee
        {
            
        }
    }
    
}
