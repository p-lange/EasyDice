package com.personal.peter.easydice;


import java.util.ArrayList;
import java.util.List;

public class Dice {
    private int mDieSize;
    private int mDieNumber;
    private int mBonus;
    private int mPenalty;
    private List<Integer> mResults = new ArrayList<>();


    public Dice(int dieNumber, int dieSize, int bonus, int penalty) {
        mDieNumber = dieNumber;
        mDieSize = dieSize;
        mBonus = bonus;
        mPenalty = penalty;
    }


    public void rollDice() {
        for (int j = 0; j < mDieNumber; j++) { //loops through each die in a set
            int rand = (int) (Math.random() * mDieSize + 1);
            mResults.add(rand);
        }
    }

    public int getDieSize() {
        return mDieSize;
    }

    public void setDieSize(int dieSize) {
        mDieSize = dieSize;
    }

    public int getDieNumber() {
        return mDieNumber;
    }

    public void setDieNumber(int dieNumber) {
        mDieNumber = dieNumber;
    }

    public List<Integer> getResults() {
        return mResults;
    }

    public void setResults(List<Integer> results) {
        mResults = results;
    }

    public int getBonus() {
        return mBonus;
    }

    public void setBonus(int bonus) {
        mBonus = bonus;
    }

    public int getPenalty() {
        return mPenalty;
    }

    public void setPenalty(int penalty) {
        mPenalty = penalty;
    }

    public int getTotal() {
        int total = 0;
        for (int result : mResults) {
            total += result;
        }
        return total - mPenalty + mBonus;
    }

}
