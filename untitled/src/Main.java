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

  private static int answerQuestion(int[] stickValues)
  {
    int minSoFar = Integer.MAX_VALUE;
    for (int i = 0; i < stickValues.length; i++) {
      for (int j = i + 1; j < stickValues.length; j++) {
        for (int k = j + 1; k < stickValues.length; k++) {
          int median = getMedian(stickValues[i], stickValues[j], stickValues[k]);
          int distance = Math.abs(stickValues[i] - median) + Math.abs(stickValues[j] - median) + Math.abs(stickValues[k] - median);
          minSoFar = Math.min(minSoFar, distance);
        }
      }
    }
    return minSoFar;
  }

  private static int getMedian(int i, int j, int k)
  {
    int[] sorted = IntStream.of(i, j, k).sorted().toArray();
    return sorted[1];
  }

  private static void printResults(int[] answers)
  {
    for (int i = 0; i < answers.length; i++) {
      System.out.println(answers[i]);
    }
  }
}
