package com.example.mutiplycalculator;

public class Calculator {
    private long value;

    Calculator(){
        value = 1;
    }

    long getValue(){
        return value;
    }

    void multiplyBy(int number){
        value = value*number;
    }

    void reset(){
        value =1;
    }
}
