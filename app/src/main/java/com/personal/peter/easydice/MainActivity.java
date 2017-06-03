package com.personal.peter.easydice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    String resultsString;
    @BindView(R.id.resultTextView)
    TextView mResultTextView;
    @BindView(R.id.totalTextView)
    TextView mTotalTextView;

    @BindView(R.id.d4Button)
    Button mD4Button;
    @BindView(R.id.d6Button)
    Button mD6Button;
    @BindView(R.id.d8Button)
    Button mD8Button;
    @BindView(R.id.d10Button)
    Button mD10Button;
    @BindView(R.id.d12Button)
    Button mD12Button;
    @BindView(R.id.d20Button)
    Button mD20Button;

    @BindView(R.id.d4EditText)
    EditText mD4EditText;
    @BindView(R.id.d6EditText)
    EditText mD6EditText;
    @BindView(R.id.d8EditText)
    EditText mD8EditText;
    @BindView(R.id.d10EditText)
    EditText mD10EditText;
    @BindView(R.id.d12EditText)
    EditText mD12EditText;
    @BindView(R.id.d20EditText)
    EditText mD20EditText;

    @BindView(R.id.rollArrayLabel)
    TextView mRollArrayLabel;

    int dieNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mD4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputAndRoll(mD4EditText.getText().toString(), 4);
            }
        });


        mD6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputAndRoll(mD6EditText.getText().toString(), 6);
            }
        });

        mD8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputAndRoll(mD8EditText.getText().toString(), 8);
            }
        });

        mD10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputAndRoll(mD10EditText.getText().toString(), 10);
            }
        });

        mD12Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputAndRoll(mD12EditText.getText().toString(), 12);
            }
        });

        mD20Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInputAndRoll(mD20EditText.getText().toString(), 20);
            }
        });

    }

    private void rollAndSet(Dice die) {
        die.rollDice();
        mTotalTextView.setText(die.getTotal() + "");
        mResultTextView.setText(getResultsString(die.getResults()));

    }

    private String getResultsString(List<Integer> results) {
        List<String> stringList = new ArrayList<>();
        for (int value : results) stringList.add(Integer.toString(value));
        StringBuilder result = new StringBuilder();
        for (String string : stringList) {
            result.append(string);
            result.append(", ");
        }
        return result.length() > 0 ? result.substring(0, result.length() - 2) : "";
    }


    private boolean isValidInput(int input) {
        return (input > 0 && input < 61);
    }

    private void displayToast() {
        Toast.makeText(this, "Must be between 1 and 60 dice.", Toast.LENGTH_SHORT).show();
    }

    private void takeInputAndRoll(String string, int dieSize) {
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


}
