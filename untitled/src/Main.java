import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {

    Scanner inputScanner = new Scanner(System.in);
    int numberOfTestCases = Integer.parseInt(inputScanner.nextLine());
    int[] answers = new int[numberOfTestCases];
    for (int i = 0; i < numberOfTestCases; i++) {
      int numberOfSticks = Integer.parseInt(inputScanner.nextLine());
      int[] values = createArrayFromString(inputScanner.nextLine());
      answers[i] = answerQuestion(values);
    }
    printResults(answers);
  }

  private static int[] createArrayFromString(String input) {
    String[] myArray = input.split(" ");
    int[] stickValues = new int[myArray.length];
    for (int i = 0; i< myArray.length; i++) {
      stickValues[i] = Integer.parseInt(myArray[i]);
    }
    return stickValues;
  }

  private static int answerQuestion(int[] stickValues) {
    int minSoFar = Integer.MAX_VALUE;

    stickValues = IntStream.of(stickValues).sorted().toArray();
    for (int i = 1; i < stickValues.length-1; i++) {
        int middleValue = stickValues[i];
        int distance = Math.abs(stickValues[i-1] - middleValue) + Math.abs(stickValues[i+1] - middleValue);
        minSoFar = Math.min(minSoFar, distance);
    }
    return minSoFar;
  }

  private static void printResults(int[] answers)
  {
    for (int i = 0; i < answers.length; i++) {
      System.out.println(answers[i]);
    }
  }
}
