package com.example;

public class CodeSmellsPractice {

  public double calculateShippingCost(int itemCount, double orderTotal, boolean express) {
    double cost = 7.99;

    if (itemCount > 3) {
      cost += itemCount * 1.25;
    }

    if (orderTotal < 50.0) {
      if (express) {
        cost += 12.0;
      } else {
        cost += 4.5;
      }
    } else if (express) {
      cost += 8.0;
    }

    if (itemCount > 10 && orderTotal < 100.0 || express && itemCount > 5) {
      cost *= 1.1;
    }

    return Math.round(cost * 100.0) / 100.0;
  }
}
