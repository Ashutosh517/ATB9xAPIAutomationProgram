package com.thetestingacademy.ex01_sampleCheck;

public class APITest004_BuilderPatternDesignPattern {

    public APITest004_BuilderPatternDesignPattern step1(){
        System.out.println("Step1");
        return this;
    }

    public APITest004_BuilderPatternDesignPattern step2(){
        System.out.println("Step2");
        return this;
    }

    public APITest004_BuilderPatternDesignPattern step3(){
        System.out.println("Step3");
        return this;
    }

    public static void main(String[] args) {
        APITest004_BuilderPatternDesignPattern bp = new APITest004_BuilderPatternDesignPattern();
        bp.step1().step2().step3();
    }
}
