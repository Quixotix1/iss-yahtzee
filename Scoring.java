package Yahtzee;

public class Scoring {
   public static void main(String[] args){ 

   }
   
   public static boolean[][] updateArrays(boolean[] possible, boolean[] taken, int newTaken){//clear out possible and change whatevers taken
       for (int i = 0; i < possible.length; i++){
           possible[i] = true;
       }
       taken[newTaken] = false;
       boolean[][] arrays = {possible, taken};
       return arrays; 
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
   public static int checker(int x){//check between full house, etcetc
     if (x == 8){
        return 25; 
     }  
     else if (x == 9){
        return 30;
     }
     else if (x == 10){
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
   public static int[] yahtzee(int scores[]){//yScore should change to the yahtzee element of the score array
      if (scores[11] >= 50){//bonus
          scores[11] += 100;
       }
       else if (scores[11] == 0){
           scores[11] += 50;
       }
       return scores;
       
   }
   
}
