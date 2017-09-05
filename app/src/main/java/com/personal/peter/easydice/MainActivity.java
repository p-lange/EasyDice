package com.personal.peter.easydice;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "com.personal.peter.easydice.preferences";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static final String KEY_TOTAL = "KEY_TOTAL";
    private static final String KEY_RESULTS = "KEY_RESULTS";
    private ArrayList<String> mKeys;
    private HashMap<String, String> mKeyMap;
    private HashMap<String, EditText> mTextMap;
    private AnimatorUtil animatorUtil;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        animatorUtil = new AnimatorUtil(this);
        final DiceUtil diceUtil = new DiceUtil(this, mTotalTextView, mResultTextView);

        mKeys = new ArrayList<>(6);
        mKeys.add("D4");
        mKeys.add("D6");
        mKeys.add("D8");
        mKeys.add("D10");
        mKeys.add("D12");
        mKeys.add("D20");

        mKeyMap = new HashMap<>(6);
        mKeyMap.put("D4", "KEY_D4");
        mKeyMap.put("D6", "KEY_D6");
        mKeyMap.put("D8", "KEY_D8");
        mKeyMap.put("D10", "KEY_D10");
        mKeyMap.put("D12", "KEY_D12");
        mKeyMap.put("D20", "KEY_D20");

        mTextMap = new HashMap<>(6);
        mTextMap.put("D4", mD4EditText);
        mTextMap.put("D6", mD6EditText);
        mTextMap.put("D8", mD8EditText);
        mTextMap.put("D10", mD10EditText);
        mTextMap.put("D12", mD12EditText);
        mTextMap.put("D20", mD20EditText);


        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);

        for (String key : mKeys){
            String textString = mSharedPreferences.getString(mKeyMap.get(key), "1");
            mTextMap.get(key).setText(textString);
        }

        String totalString = mSharedPreferences.getString(KEY_TOTAL, "---");
        String resultString = mSharedPreferences.getString(KEY_RESULTS, "---");
        mTotalTextView.setText(totalString);
        mResultTextView.setText(resultString);


        mD4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorUtil.animateButton(mD4Button);
                diceUtil.takeInputAndRoll(mD4EditText.getText().toString(), 4);
            }
        });

        mD6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorUtil.animateButton(mD6Button);
                diceUtil.takeInputAndRoll(mD6EditText.getText().toString(), 6);
            }
        });

        mD8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorUtil.animateButton(mD8Button);
                diceUtil.takeInputAndRoll(mD8EditText.getText().toString(), 8);
            }
        });

        mD10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorUtil.animateButton(mD10Button);
                diceUtil.takeInputAndRoll(mD10EditText.getText().toString(), 10);
            }
        });

        mD12Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorUtil.animateButton(mD12Button);
                diceUtil.takeInputAndRoll(mD12EditText.getText().toString(), 12);
            }
        });

        mD20Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorUtil.animateButton(mD20Button);
                diceUtil.takeInputAndRoll(mD20EditText.getText().toString(), 20);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor = mSharedPreferences.edit();
        for (String key : mKeys){
            mEditor.putString(mKeyMap.get(key), mTextMap.get(key).getText().toString());
        }
        mEditor.putString(KEY_TOTAL, mTotalTextView.getText().toString());
        mEditor.putString(KEY_RESULTS, mResultTextView.getText().toString());
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mResultTextView.setText("---");
        mTotalTextView.setText("---");
        animatorUtil.animateText(mTotalTextView);
        animatorUtil.animateText(mResultTextView);

        for (String key : mKeys){
            EditText text = mTextMap.get(key);
            text.setText("1");
            animatorUtil.animateText(text);
        }
        return true;
    }
}
