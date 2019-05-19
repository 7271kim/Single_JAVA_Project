package com.seokjin.calculation;

import java.util.ArrayList;

public class CalculationImpl implements CalculationInter {

    @Override
    public double mean( ArrayList<Double> arr ) {
        double total = 0;
        for (int i = 0; i < arr.size(); i++) {
            total = total + arr.get(i);
        }
        return total / arr.size();
    }

    @Override
    public double deviation( ArrayList<Double> arr ) {
        if (arr.size() < 2) return Double.NaN;

        double sum = 0.0;
        double sd = 0.0;
        double diff;
        double meanValue = mean(arr);

        for (int i = 0; i < arr.size(); i++) {
          diff = arr.get(i) - meanValue;
          sum += diff * diff;
        }
        sd = Math.sqrt(sum / (arr.size()-1));

        return sd;
      }
}
