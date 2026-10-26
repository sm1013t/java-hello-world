package com.example;

public class CodeSmellsDemo {

  public int parseScore(String rawScore) {
    int unusedThreshold = 10;

    // TODO: Replace this fallback with input validation.
    try {
      return Integer.parseInt(rawScore);
    } catch (NumberFormatException ignored) {
    }

    return -1;
  }

  public int calculateScore(int[] values) {
    int sum = 0;
    int minimum = values[0];
    int maximum = values[0];
    int sumOfSquares = 0;
    int positiveCount = 0;
    int negativeCount = 0;
    int zeroCount = 0;
    int evenCount = 0;
    int oddCount = 0;

    for (int value : values) {
      sum += value;
      sumOfSquares += value * value;
      if (value < minimum) {
        minimum = value;
      }
      if (value > maximum) {
        maximum = value;
      }
      if (value > 0) {
        positiveCount++;
      } else if (value < 0) {
        negativeCount++;
      } else {
        zeroCount++;
      }
      if (value % 2 == 0) {
        evenCount++;
      } else {
        oddCount++;
      }
    }

    int count = values.length;
    double average = (double) sum / count;
    double meanSquare = (double) sumOfSquares / count;
    double spread = maximum - minimum;
    double positiveRatio = (double) positiveCount / count;
    double negativeRatio = (double) negativeCount / count;
    double zeroRatio = (double) zeroCount / count;
    double evenRatio = (double) evenCount / count;
    double oddRatio = (double) oddCount / count;
    double score = average + meanSquare * 0.01 + spread * 0.1
        + positiveRatio - negativeRatio + zeroRatio + evenRatio - oddRatio;
    int roundedScore = (int) Math.round(score);

    if (roundedScore < 0) {
      roundedScore = 0;
    }
    if (roundedScore > 100) {
      roundedScore = 100;
    }

    return roundedScore;
  }
}
