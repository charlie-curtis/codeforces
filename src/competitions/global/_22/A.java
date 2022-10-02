package competitions.global._22;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.LongStream;

public class A {

  private static Scanner sc;

  public static void main(String[] args) {
    sc = new Scanner(System.in);

    int numberOfTestCases = Integer.parseInt(sc.nextLine());

    long[] answers = new long[numberOfTestCases];
    for (int i = 0; i < numberOfTestCases; i++) {
      answers[i] = executeTestCase();
    }

    LongStream.of(answers).forEach(System.out::println);
  }

  private static long executeTestCase()
  {
    int inputSize = Integer.parseInt(sc.nextLine());
    int[] types = getIntegerArray(inputSize);
    int[] damages = getIntegerArray(inputSize);
    return determineMaxDamage(types, damages);
  }

  private static int[] getIntegerArray(int inputSize)
  {
    int[] input = new int[inputSize];
    for (int i = 0; i < inputSize; i++) {
      input[i] = sc.nextInt();
    }
    sc.nextLine();
    return input;
  }

  private static long determineMaxDamage(int[] types, int[] damages) {
    ArrayList<Integer> fireDamages = new ArrayList<>();
    ArrayList<Integer> frostDamages = new ArrayList<>();

    for (int i = 0; i < types.length; i++) {
      if (types[i] == 0) {
        //add to fire
        fireDamages.add(damages[i]);
      } else {
        frostDamages.add(damages[i]);
      }
    }

    fireDamages.sort(Comparator.naturalOrder());
    frostDamages.sort(Comparator.naturalOrder());

    LastMove lastMove = null;
    boolean isFirstMove = true;
    long sum = 0;
    while (fireDamages.size() != 0 || frostDamages.size() != 0) {
      if (isFirstMove) {
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
          sum += fireDamages.get(0);
          fireDamages.remove(0);
          lastMove = LastMove.FIRE;
        } else {
          sum += frostDamages.get(0);
          frostDamages.remove(0);
          lastMove = LastMove.FROST;
        }
        isFirstMove = false;
      } else {
        //we have the multiplier. Go for the biggest damage of the opposite type. If that isn't available,
        //go for the smallest of this type
        if (lastMove == LastMove.FIRE) {
          if (frostDamages.size() != 0) {
            sum += 2 * frostDamages.get(frostDamages.size() - 1);
            frostDamages.remove(frostDamages.size() - 1);
            lastMove = LastMove.FROST;
          } else {
            sum += fireDamages.get(0);
            fireDamages.remove(0);
            lastMove = LastMove.FIRE;
          }
        } else {
          if (fireDamages.size() != 0) {
            sum += 2 * fireDamages.get(fireDamages.size() - 1);
            fireDamages.remove(fireDamages.size() - 1);
            lastMove = LastMove.FIRE;
          } else {
            sum += frostDamages.get(0);
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
