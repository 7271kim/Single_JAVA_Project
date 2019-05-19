package com.seokjin.calculation;

import java.util.ArrayList;

public interface CalculationInter {
    double mean ( ArrayList<Double> arr); // Double 리스트 형식으로 arr 집어넣으면 mean이 나옴
    double deviation ( ArrayList<Double> arr); // Double 리스트 형식으로 arr 집어넣으면 표준편차가 나옴
}
