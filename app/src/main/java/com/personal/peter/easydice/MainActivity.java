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

import java.util.HashMap;


import butterknife.ButterKnife;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "com.personal.peter.easydice.preferences";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static final String KEY_TOTAL = "KEY_TOTAL";
    private static final String KEY_RESULTS = "KEY_RESULTS";
    private HashMap<String, EditText> mTextMap;
    private AnimatorUtil animatorUtil;

    TextView mResultTextView;
    TextView mTotalTextView;
    TextView mRollArrayLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animatorUtil = new AnimatorUtil(this);
        final DiceUtil diceUtil = new DiceUtil(this, mTotalTextView, mResultTextView);

        mTextMap = new HashMap<>();
        mTotalTextView = (TextView) findViewById(R.id.totalTextView);
        mResultTextView = (TextView) findViewById(R.id.resultTextView);
        mRollArrayLabel = (TextView) findViewById(R.id.rollArrayLabel);

        //initializes the buttons and edit text. Add more dice here if desired.
        Button mD4Button = (Button) findViewById(R.id.d4Button);
        EditText mD4EditText = (EditText) findViewById(R.id.d4EditText);
        Button mD6Button = (Button) findViewById(R.id.d6Button);
        EditText mD6EditText = (EditText) findViewById(R.id.d6EditText);
        Button mD8Button = (Button) findViewById(R.id.d8Button);
        EditText mD8EditText = (EditText) findViewById(R.id.d8EditText);
        Button mD10Button = (Button) findViewById(R.id.d10Button);
        EditText mD10EditText = (EditText) findViewById(R.id.d10EditText);
        Button mD12Button = (Button) findViewById(R.id.d12Button);
        EditText mD12EditText = (EditText) findViewById(R.id.d12EditText);
        Button mD20Button = (Button) findViewById(R.id.d20Button);
        EditText mD20EditText = (EditText) findViewById(R.id.d20EditText);

        buttonInitializer(mD4Button, mD4EditText, 4);
        buttonInitializer(mD6Button, mD6EditText, 6);
        buttonInitializer(mD8Button, mD8EditText, 8);
        buttonInitializer(mD10Button, mD10EditText, 10);
        buttonInitializer(mD12Button, mD12EditText, 12);
        buttonInitializer(mD20Button, mD20EditText, 20);

        //Handle saved dice results and inputs, loads them, etc
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        for (String key : mTextMap.keySet()){
            String textString = mSharedPreferences.getString(key, "1");
            mTextMap.get(key).setText(textString);
        }
        String totalString = mSharedPreferences.getString(KEY_TOTAL, "---");
        String resultString = mSharedPreferences.getString(KEY_RESULTS, "---");
        mTotalTextView.setText(totalString);
        mResultTextView.setText(resultString);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor = mSharedPreferences.edit();
        for (String key : mTextMap.keySet()){
            mEditor.putString(key, mTextMap.get(key).getText().toString());
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

        for (String key : mTextMap.keySet()){
            EditText text = mTextMap.get(key);
            text.setText("1");
            animatorUtil.animateText(text);
        }
        return true;
    }


    public void buttonInitializer(final Button button, final EditText editText, final int dieSize){

        mTextMap.put("KEY_D"+dieSize, editText);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorUtil.animateButton(button);
                DiceUtil diceUtil = new DiceUtil(MainActivity.this, mTotalTextView, mResultTextView);
                diceUtil.takeInputAndRoll(editText.getText().toString(), dieSize);
            }
        });
    }
}
