package com.mycompany.yahtzee;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author pres1680
 */

public class Main extends Game {
    static Game game = new Game();
    static Checker check = new Checker();
    static Scoring scoreThing = new Scoring();
    
    static int[] p1Score = new int[13];
    static int[] p2Score = new int[13];
    static int[][] score = {p1Score, p2Score};
    static boolean noYahtzee = false;
    
    static boolean[] p1Taken = new boolean[13];
    static boolean[] p2Taken = new boolean[13];
    static boolean[][] taken = {p1Taken, p2Taken};
    
    public static void main(String[] args) {
        
        int turns = 0;
        int playerTurn = 0;
        
        ArrayList<Integer> dice = new ArrayList<Integer>();
        
        boolean[] p1Poss = new boolean[13];
        boolean[] p2Poss = new boolean[13];
      
        
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                score[i][j] = 0;
                taken[i][j] = false;
            }
        }
        
        
        while (true)//run the game
        {
            if (turns >= 26){//ends the game when all turns are taken
                break;
            }
            
            if (playerTurn == 0)
            {
               System.out.println("Player 1");
               dice = game.run();
               p1Poss = check.checkPossibilities(dice, taken[0], noYahtzee);
               taken[0] = finishTurn(p1Poss, score[0], dice, taken[0]);
               turns += 1;
               playerTurn = 1;
            }
            else
            {
                System.out.println("Player 2");
                dice = game.run();
                p2Poss = check.checkPossibilities(dice, taken[1], noYahtzee);
                taken[1] = finishTurn(p2Poss, score[1], dice, taken[1]);
               
                turns += 1;
                playerTurn = 0;
            }
            
            
        }
        clearScreen();
        System.out.println("Game Over! Lets see the scores...");//print out final results and declare winner
        //player 1
        //top
        System.out.println("Player 1: \n1 - " + score[0][0] + "\n2 - " + score[0][1] + "\n3 - " + score[0][2] + "\n4 - " + score[0][3] + "\n5 - " + score[0][4] + "\n6 - " + score[0][5]);
        int p1TopTotal = (score[0][0] + score[0][1] + score[0][2] + score[0][3] + score[0][4] + score[0][5]);
        System.out.println("Total: " + p1TopTotal);
        if (p1TopTotal >= 63){
           System.out.println("Bonus: 35");
           System.out.println("Total: " + (p1TopTotal + 35));
        }
        else{
           System.out.println("Bonus: 0");
           System.out.println("Total: " + p1TopTotal); 
        }
        //bottom
        System.out.println("3 Of A Kind - " + score[0][6] + "\n4 Of A Kind - " + score[0][7] + "\nFull House - " + score[0][8] + "\nSmall Straight - " + score[0][9] + "\nLarge Straight - " + score[0][10] + "\nYahtzee! - " + score[0][11] + "\nChance - " + score[0][12]);
        int p1BottomTotal = (score[0][6] + score[0][7] + score[0][8] + score[0][9] + score[0][10] + score[0][11] + score[0][12]);
        System.out.println("Total: " + p1BottomTotal);
        System.out.println("Total(top): " + p1TopTotal);
        int p1GrandTotal = (p1TopTotal + p1BottomTotal);
        System.out.println("GRAND TOTAL: " + p1GrandTotal);
        
        //player 2
        //top
        System.out.println("\nPlayer 2: \n1 - " + score[1][0] + "\n2 - " + score[1][1] + "\n3 - " + score[1][2] + "\n4 - " + score[1][3] + "\n5 - " + score[1][4] + "\n6 - " + score[1][5]);
        int p2TopTotal = (score[1][0] + score[1][1] + score[1][2] + score[1][3] + score[1][4] + score[1][5]);
        System.out.println("Total: " + p2TopTotal);
        if (p2TopTotal >= 63){
           System.out.println("Bonus: 35");
           System.out.println("Total: " + (p2TopTotal + 35));
        }
        else{
           System.out.println("Bonus: 0");
           System.out.println("Total: " + p2TopTotal); 
        }
        //bottom
        System.out.println("3 Of A Kind - " + score[1][6] + "\n4 Of A Kind - " + score[1][7] + "\nFull House - " + score[1][8] + "\nSmall Straight - " + score[1][9] + "\nLarge Straight - " + score[1][10] + "\nYahtzee! - " + score[1][11] + "\nChance - " + score[1][12]);
        int p2BottomTotal = (score[1][6] + score[1][7] + score[1][8] + score[0][9] + score[1][10] + score[1][11] + score[1][12]);
        System.out.println("Total: " + p2BottomTotal);
        System.out.println("Total(top): " + p2TopTotal);
        int p2GrandTotal = (p2TopTotal + p2BottomTotal);
        System.out.println("GRAND TOTAL: " + p2GrandTotal);
        
        if (p1GrandTotal > p2GrandTotal){
            System.out.println("Player 1 Wins! ");
        }
        else if (p1GrandTotal < p2GrandTotal){
            System.out.println("Player 2 Wins!");
        }
        else if (p1GrandTotal == p2GrandTotal){
            System.out.println("Tie Game");
        }
    }
    
    public static boolean[] finishTurn(boolean[] possibilities, int[] score, ArrayList<Integer> dice, boolean[] taken)
    {
        Scanner scanner = new Scanner(System.in);
        String[] options = {"ones","twos","threes","fours","fives","sixes","Three Of A Kind","Four Of A Kind","Full House(3 of one #, 2 of another)","Small Straight(4 #'s in a row)","Large Straight(5 #'s in a row)","Yahtzee(pick me!)","Chance(add up all the dice)"}; 
        int decision = 0;
        ArrayList<Integer> validDecision = new ArrayList<Integer>();
        
        
       //if they have nothing
        int ifNone = 0;
        System.out.println("Select one: ");
        for (int i = 0; i < possibilities.length; i++){
            if (possibilities[i] == true){
                System.out.print((i + 1) + ": " + options[i] + "\n");
                validDecision.add(i); 
            }
            else {
                ifNone += 1;
            }
        }
        if (ifNone < 13){
            
            while (true) //loops until a valid decision has been entered
            {
                if (scanner.hasNextInt())
                {
                    decision = scanner.nextInt() - 1;
                    
                    if (validDecision.contains(decision))
                    {
                        break;
                    }
                }
                scanner.nextLine();
                System.out.println("Please enter a valid decision");
            }
            validDecision.clear();
            

            if (decision < 6) //123456
            {
                score[decision] = scoreThing.justNumbers(dice, decision + 1);
            }
            else if (decision < 8 || decision == 12) //3/4oak, chance
            {
                score[decision] = scoreThing.addAll(dice);//all three have the same output so they are all in the same method 
            }
            else if (decision < 11) //full house, lstraight/ smstraight
            {
                score[decision] = scoreThing.checker(decision);//this will check btwn the three since they're simpler
            }
            else //yahtzee
            {
                score[decision] = scoreThing.yahtzee(score);
            }
        }
        else{
            System.out.println("Pick out of these options to recieve a score of 0");
            for (int i = 0; i < taken.length; i++){
              if (taken[i] == false){
                  System.out.println((i + 1) + ": " + options[i]);
                  validDecision.add(i);
              }  
            }
            
            while (true)
            {
                if (scanner.hasNextInt())
                {
                    decision = scanner.nextInt() - 1;
                    if (validDecision.contains(decision))
                    {
                        break;
                    }
                }
                scanner.nextLine();
                System.out.println("Please enter a valid decision");
            }
        }
        score[decision] = 0;
        taken[decision] = true;//make sure that what they've chosen is now not possible
        if (decision == 11)
        {
            noYahtzee = true;
        }
        clearScreen();
        return taken;
    }
    
    public static void clearScreen()
    {
        for (int i = 0; i < 10; i++) //totally legit console clear (other methods don't work in netbeans)
        {
            System.out.println();
        }
    }
}
