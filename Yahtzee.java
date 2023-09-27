/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

//package cs.yahtzee;
import java.util.*;

/**
 *
 * @author terr3890
 */
public class Yahtzee {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    static boolean[] checkPossibilities(int[] dice) {
        ArrayList<Integer> numVals = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0));
        
        for (int roll : dice) {
            numVals.set(roll - 1, numVals.get(roll - 1)+ 1);
        }
        boolean[] possible = new boolean[7];
        possible[7] = true;
        if (numVals.contains(5)) {
            possible[5] = true;
            possible[0] = true;
            possible[1] = true;
        } else if (numVals.contains(4)) {
            possible[0] = true;
            possible[1] = true;
        } else if (numVals.contains(3)) {
            possible[0] = true;
            if (numVals.contains(2)) {
                possible[2] = true;
            }
        }
        
        if (numVals.get(2) >= 1 && numVals.get(3) >= 1) {
            if ((numVals.get(0) >= 1 && (numVals.get(1) >= 1)) || (numVals.get(1) >= 1 && numVals.get(4) >= 1) || (numVals.get(4) >= 1 && numVals.get(5) >= 1)) {
                possible[3] = true; 
                if (numVals.get(1) == 1 && numVals.get(4) == 1) {
                    possible[4] = true;
                }
            }
        }
        return possible;
    }
}
