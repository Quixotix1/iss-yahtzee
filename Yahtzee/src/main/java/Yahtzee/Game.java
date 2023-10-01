package Yahtzee;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void resetHeldDie() {
        heldNums.clear();
    }
    public ArrayList<Integer> run() {
        remainingRolls = 3;
        OUTER:
        while(true){
            // random number array, output numbers
            die = new ArrayList<>();
            die.addAll(rand.ints(5 - heldNums.size(), 1, 7).boxed().toList());
            remainingRolls--;
            
            // get input
            INNER:
            while(true){
                if(remainingRolls <= 0) break OUTER;
                System.out.print("Rolls, ");
                for(int dice : die) {
                    System.out.printf("%d ", dice);
                }
                System.out.print("\nHeld Die: ");
                for(int dice : heldNums) {
                    System.out.print(dice + " ");
                }
                System.out.println();
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
        for (Integer ele : heldNums) {
            die.add(ele);
        }
        return die;
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
                System.out.println("Which dice would you like to hold?");
                for(int i = 0; i < die.size(); i++) {
                    System.out.printf("%d) %d%n", i + 1, die.get(i));
                }
                
                // get die, add to held, remove from die, if error then return
                try {
                    String[] holdString = in.nextLine().split(" ", -1);
                    int lenHold = holdString.length;
                    int[] hold = new int[lenHold];
                    for (int i = 0; i < lenHold; i++) {
                        hold[i] = Integer.parseInt(holdString[i]);
                    }
                    Arrays.sort(hold);
                    ArrayList<Integer> heldIndex = new ArrayList<Integer>();
                    for (int ele : hold) {
                        if (heldIndex.contains(ele)) continue;
                        heldDie.add(die.get(ele - 1));
                        heldIndex.add(ele);
                    }
                    heldIndex.clear();
                    int removed = 0;

                    for (int i = 0; i < lenHold; i++) {
                        if (heldIndex.contains(hold[i])) continue;
                        die.remove(hold[i] - 1 - removed);
                        heldIndex.add(hold[i]);
                        removed++;
                    }
                    
                } catch(NumberFormatException e) {
                    System.out.println("Input not received successfully.");
                }
                break;
            }
            case "2" -> {
                if(heldDie.isEmpty()) {
                    System.out.println("Error: No dice being held");
                    break;
                }
                System.out.println("Which dice would you like to let go of?");
                for(int i = 0; i < heldDie.size(); i++) {
                    System.out.printf("%d) %d%n", i + 1, heldDie.get(i));
                }
                
                // get die, add to held, remove from die, if error then return
                try {
                    int remove = Integer.parseInt(in.nextLine());
                    die.add(heldDie.get(remove - 1));
                    heldDie.remove(remove - 1);
                } catch(NumberFormatException e) {
                    System.out.println("Input not received successfully.");
                }
                break;
            }
            default -> {
                return;
            }
        }
    }
}
