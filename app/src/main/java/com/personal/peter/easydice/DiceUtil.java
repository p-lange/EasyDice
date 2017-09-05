package com.personal.peter.easydice;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DiceUtil {
    private Context mContext;
    private TextView mTotalTextView;
    private TextView mResultTextView;
    private int dieNum;

    public DiceUtil(Context context, TextView totalText, TextView resultText) {
        mContext = context;
        mTotalTextView = totalText;
        mResultTextView = resultText;
    }

    private void rollAndSet(Dice die){
        AnimatorUtil animatorUtil = new AnimatorUtil(mContext);
        die.rollDice();
        mTotalTextView.setText(die.getTotal() + "");
        mResultTextView.setText(getResultsString(die.getResults()));
        animatorUtil.animateText(mTotalTextView);
        animatorUtil.animateText(mResultTextView);
    }

    private String getResultsString(List<Integer> results){
        List<String> stringList = new ArrayList<>();
        for (int value : results) stringList.add(Integer.toString(value));
        StringBuilder result = new StringBuilder();
        for (String string : stringList) {
            result.append(string);
            result.append(", ");
        }
        return result.length() > 0 ? result.substring(0, result.length() - 2) : "";
    }

    public void takeInputAndRoll(String string, int dieSize){

        try {
            dieNum = Integer.parseInt(string);
            if (isValidInput(dieNum)) {
                Dice die = new Dice(dieNum, dieSize, 0, 0);
                rollAndSet(die);
            } else displayToast();
        } catch (NumberFormatException e) {
            displayToast();
        }
    }

    private boolean isValidInput(int input) {
        return (input > 0 && input < 61);
    }

    private void displayToast() {
        Toast.makeText(mContext, "Must be between 1 and 60 dice.", Toast.LENGTH_SHORT).show();
    }

}
