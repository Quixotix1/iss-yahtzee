package com.mycompany.yahtzee;

import java.util.*;
import java.util.ArrayList;
/**
 *
 * @author terr3890
 */
public class Checker extends Scoring{

    public static void main(String[] args)
    {
        
    }
 
    static boolean[] checkPossibilities(ArrayList<Integer> dice, boolean[] taken) {
        ArrayList<Integer> numVals = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0));
        
        for (int roll : dice) {
            numVals.set(roll - 1, numVals.get(roll - 1)+ 1);
        }
        boolean[] possible = {false, false, false, false, false, false, false, false, false, false, false, false, true}; //possible scores
       //, 123456, 3oak, 4oak, fhouse, sstraight, lstraight, yahtzee, chance(id default true because it will be taken away if it's been chosen but is never checked for)
        if (numVals.contains(5)) { //Yahtzee
            possible[11] = true;
            possible[6] = true;
            possible[7] = true;
        } else if (numVals.contains(4)) { //Four of a kind
            possible[6] = true;
            possible[7] = true;
        } else if (numVals.contains(3)) {
            possible[6] = true;
            if (numVals.contains(2)) {//full house
                possible[8] = true;
            }
        }
        for (int i = 1; i <= dice.size() + 1; i++){//123456 check
           if (dice.contains(i)){
             possible[i-1] = true;
           }
         
        }
     
       
        if (numVals.get(2) >= 1 && numVals.get(3) >= 1){
            if ((numVals.get(0) >= 1 && (numVals.get(1) >= 1)) || (numVals.get(1) >= 1 && numVals.get(4) >= 1) || (numVals.get(4) >= 1 && numVals.get(5) >= 1)) { //small straight
                possible[9] = true; 
            }
            if ((numVals.get(1) == 1 && numVals.get(4) == 1) && (numVals.get(0) == 1 || numVals.get(5) == 1) ){// large straight
                possible[10] = true;
            }
        }
        
        for (int i = 0; i < taken.length; i++){
            if (i != 11){
                if (taken[i] == true){
                    possible[i] = false;
                }
                else if (noYahtzee)
                {
                    possible[i] = false;
                }
            }
        }
        
        return possible; //return array list with possible combinations
    }
}
