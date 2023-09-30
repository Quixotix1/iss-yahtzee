
package com.mycompany.yahtzee;

import java.util.ArrayList;
public class Scoring {
   public static void main(String[] args){ 

   }
   
   public static boolean[] updateArrays(boolean[] possible){//clear out possible and change whatevers taken
       for (int i = 0; i < possible.length; i++){
           possible[i] = true;
       }
       return possible; 
   }
   public static int justNumbers(ArrayList<Integer> diceArray, int x){//ones,twos, etcetc
     int count = 0;
     for(int i = 0; i < 5; i++){
       if(diceArray.get(i) == x){
          count += 1; 
       } 
     }
     int score = count * x;
     return score;
   }
   public static int checker(int x){//check between full house, large straight, small straight
     if (x == 8){//full house
        return 25; 
     }  
     else if (x == 9){//small straight
        return 30;
     }
     else if (x == 10){//large straight
        return 40; 
     }
     return 0;
   }
   public static int addAll(ArrayList<Integer> diceArray){//3/4 of a kind, chance
       int score = 0;
       for(int i = 0; i < 5; i++){
           score += diceArray.get(i);
       }
       return score;
   }
   public static int yahtzee(int scores[]){//yScore should change to the yahtzee element of the score array
      int a = scores[11];
      if (a >= 50){//bonus
          a += 100;
       }
       else if (a == 0){
           a += 50;
       }
       return a;
       
   }
   
}
