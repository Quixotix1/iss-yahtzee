package com.mycompany.yahtzee.kateYahtzee;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author joshg
 */
public class Game extends Checker {
    
    // def classes
    private  final Random rand = new Random();
    private  final Scanner in = new Scanner(System.in);
    
    // def variables
    private  int remainingRolls;
    private  ArrayList<Integer> die;
    private  final ArrayList<Integer> heldNums;
    
    
    public Game() {
        this.heldNums = new ArrayList<>();
    }
    
    public ArrayList<Integer> run() {
        ArrayList<Integer> allDice = new ArrayList<Integer>();
        remainingRolls = 3;
        allDice.clear();
        heldNums.clear();
        OUTER:
        while(true){
            allDice.clear();
            // random number array, output numbers
            die = new ArrayList<>();
            die.addAll(rand.ints(5 - heldNums.size(), 1, 7).boxed().toList());
            remainingRolls--;
            System.out.print("Rolls, ");
            //for(int dice : die) {
            //   System.out.printf("%d ", dice);
            //}
            
            for (int i = 0; i < 5; i++){
               allDice.add(0); 
            }
            for (int i = 0; i < die.size(); i++){
                allDice.set(i, die.get(i));
            }
            for (int i = (die.size()); i < (heldNums.size() + die.size()); i++){
                allDice.set(i,heldNums.get(i - die.size()));
            }
            for (int i = 0; i < allDice.size(); i++){
                System.out.print(allDice.get(i) + " ");
            }
            // get input
            INNER:
            while(true){
                if(remainingRolls <= 0) break OUTER;
                System.out.println("""
                                   
                                   Select an operation:
                                   1) reroll
                                   2) end turn
                                   3) modify held die""");
                String input = in.nextLine();
                
                switch(input) {
                    case "1" -> {
                        break INNER;
                    }
                    case "2" -> {
                        break OUTER;
                    }
                    case "3" -> {
                        modifyHeldDie(die, heldNums);
                        break;
                    }
                    default -> {
                        System.out.println("Please enter a valid input.");
                    }
                }
            }
        }
        return allDice;
    }
    
    public void modifyHeldDie(ArrayList<Integer> die, ArrayList<Integer> heldDie) {
        // show die and held die to the player
        System.out.print("Die: ");
        for(int dice : die) {
            System.out.print(dice + " ");
        }
        System.out.print("\nHeld Die: ");
        for(int dice : heldDie) {
            System.out.print(dice + " ");
        }
        
        System.out.println("""
                           
                           What would you like to modify?:
                           1) hold a dice
                           2) let go of a dice
                           3) back""");
        String input = in.nextLine();
        switch(input) {
            case "1" -> {
                if(die.isEmpty()) {
                    System.out.println("Error: No dice on table");
                    break;
                }
                while(true){
                    System.out.println("Which dice would you like to hold?");
                    for(int i = 0; i < die.size(); i++) {
                        System.out.printf("%d) %d%n", i + 1, die.get(i));
                    }

                    // get die, add to held, remove from die, if error then return
                    //hold multiple dice at a time
                    try {
                        int hold = Integer.parseInt(in.nextLine());
                        heldDie.add(die.get(hold - 1));
                        die.remove(hold - 1);
                    } catch(NumberFormatException e) {
                        System.out.println("Input not received successfully.");
                    }
                    System.out.println("Would you like to hold any more? (y or n)");
                    if (in.nextLine().equals("n")){
                        break;
                    }
                }
                break;
            }
            case "2" -> {
                if(heldDie.isEmpty()) {
                    System.out.println("Error: No dice being held");
                    break;
                }
 *
