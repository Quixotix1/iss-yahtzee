package Yahtzee;
import java.util.*;

/**
 *
 * @author terr3890
 */
public class UpdatedChecker extends Scoring{

    public static void main(String[] args)
    {
        
    }
 
    static boolean[] checkPossibilities(ArrayList<Integer> dice, boolean[] taken) {
        ArrayList<Integer> numVals = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0));
        
        for (int roll : dice) {
            numVals.set(roll - 1, numVals.get(roll - 1)+ 1);
        }
        boolean[] possible = {false, false, false, false, false, false, false, false, false, false, false, false, false}; //possible scores
       //, 123456, 3oak, 4oak, fhouse, sstraight, lstraight, yahtzee, chance
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
        for (int i = 1; i <= dice.size(); i++){//123456 check
           if (dice.contains(i)){
             possible[i-1] = true;
           }
        }
        for (int i = 0; i < taken.length; i++){
            if (taken[i] = true){
                possible[i] = false;
            }
        }
       
        
        if (numVals.get(2) >= 1 && numVals.get(3) >= 1) {//straights
            if ((numVals.get(0) >= 1 && (numVals.get(1) >= 1)) || (numVals.get(1) >= 1 && numVals.get(4) >= 1) || (numVals.get(4) >= 1 && numVals.get(5) >= 1)) { //small straight
                possible[9] = true; 
            }
            else if ((numVals.get(1) == 1 && numVals.get(4) == 1) && (numVals.get(0) == 1 || numVals.get(5) == 1) ){// large straight
                possible[10] = true;
            }
        }
        return possible; //return array list with possible combinations
    }
}
