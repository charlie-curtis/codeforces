package Sept3022;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class A {

  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);

    int numberOfTestCases = Integer.parseInt(sc.nextLine());

    long[] answers = new long[numberOfTestCases];
    for (int i = 0; i < numberOfTestCases; i++) {
      sc.nextLine();
      String[] typeString = sc.nextLine().split(" ");
      String[] damageString = sc.nextLine().split(" ");
      int[] types = new int[typeString.length];
      int[] damages = new int[typeString.length];
      for (int j = 0; j <damages.length; j++) {
        types[j] = Integer.parseInt(typeString[j]);
        damages[j] = Integer.parseInt(damageString[j]);
      }
      answers[i] = determineMaxDamage(types, damages);
    }

    for (int i = 0; i < answers.length; i++) {
      System.out.println(answers[i]);
    }

  }

  private static long determineMaxDamage(int[] types, int[] damages)
  {
    ArrayList<Integer> fireDamages = new ArrayList<>();
    ArrayList<Integer> frostDamages = new ArrayList<>();

    for (int i = 0; i < types.length; i++) {
      if (types[i] == 0) {
        //add to fire
        fireDamages.add(damages[i]);
      } else{
        frostDamages.add(damages[i]);
      }
    }

    fireDamages.sort(Comparator.naturalOrder());
    frostDamages.sort(Comparator.naturalOrder());

    //which move do i start with? the move with the least elements in the array? or the move with the lowest initial value?

    LastMove lastMove = null;
    boolean isFirstMove = true;
    long sum = 0;
    while (fireDamages.size() != 0 || frostDamages.size() != 0) {
      if(isFirstMove) {
        boolean shouldStartFire = false;
        if (fireDamages.size() == frostDamages.size()) {
          //if both are the same size, then start with the lesser value of the two smallest
          int fireDamage = fireDamages.get(0);
          int frostDamage = frostDamages.get(0);
          shouldStartFire = fireDamage < frostDamage;
        } else {
          shouldStartFire = fireDamages.size() > frostDamages.size();
        }
        if (shouldStartFire) {
          sum+= fireDamages.get(0);
          fireDamages.remove(0);
          lastMove = LastMove.FIRE;
        } else {
          sum+= frostDamages.get(0);
          frostDamages.remove(0);
          lastMove = LastMove.FROST;
        }
        isFirstMove = false;
      } else {
          //we have the multiplier. Go for the biggest damage of the opposite type. If that isn't available,
          //go for the smallest of this type
        if (lastMove == LastMove.FIRE) {
          if (frostDamages.size() != 0) {
            sum+= 2*frostDamages.get(frostDamages.size()-1);
            frostDamages.remove(frostDamages.size()-1);
            lastMove = LastMove.FROST;
          } else {
            sum+= fireDamages.get(0);
            fireDamages.remove(0);
            lastMove = LastMove.FIRE;
          }
        } else {
          if (fireDamages.size() != 0) {
            sum+= 2*fireDamages.get(fireDamages.size()-1);
            fireDamages.remove(fireDamages.size()-1);
            lastMove = LastMove.FIRE;
          } else {
            sum+= frostDamages.get(0);
            frostDamages.remove(0);
            lastMove = LastMove.FROST;
          }
        }
      }
    }

    return sum;

  }

  enum LastMove {
    FIRE,
    FROST
  }
}
