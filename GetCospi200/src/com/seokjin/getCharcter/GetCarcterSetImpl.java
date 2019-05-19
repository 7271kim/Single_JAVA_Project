package com.seokjin.getCharcter;

public class GetCarcterSetImpl implements GetCarcterSet {

    @Override
    public String cuttingBackString(String incomeString, String cuttingChar) {
        String result = "";
        result = incomeString.substring(incomeString.lastIndexOf(cuttingChar) + 1);
        return result;
    }

}
