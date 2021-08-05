package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView inputTextView, resultTextView;
    ScriptEngineManager scriptEngineManager;
    ScriptEngine scriptEngine;
    boolean leftBracket = true;
    String input, formula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.input_textView);
        resultTextView = findViewById(R.id.result_textView);

        scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("rhino");


    }


    public void clearOnClick(View view) {
        inputTextView.setText("");
        resultTextView.setText("");
        Toast.makeText(this, "Clear", Toast.LENGTH_SHORT).show();
    }

    public void bracketOnClick(View view) {
        char bracket = leftBracket ? '(' : ')';
        inputTextView.setText(inputTextView.getText().toString() + bracket);
        leftBracket = !leftBracket;
    }

    public void powerOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "^");
    }

    public void divisionOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "/");
    }

    public void sevenOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "7");
    }

    public void eightOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "8");
    }

    public void nineOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "9");
    }

    public void multiplicationOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "*");
    }

    public void fourOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "4");
    }

    public void fiveOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "5");
    }

    public void sixOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "6");
    }

    public void subtractionOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "-");
    }

    public void oneOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "1");
    }

    public void twoOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "2");
    }

    public void threeOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "3");
    }

    public void additionOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "+");
    }

    public void dotOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + ".");
    }

    public void zeroOnClick(View view) {
        inputTextView.setText(inputTextView.getText().toString() + "0");
    }

    public void equalOnClick(View view) throws ScriptException {
        input = inputTextView.getText().toString();
        Double result = null;
        checkForPower();
        try {
            result = (double) scriptEngine.eval(input);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
        resultTextView.setText("" + result);
    }

    private void checkForPower() {
        ArrayList<Integer> indexOfPower = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '^') {
                indexOfPower.add(i);
            }
            for (Integer index : indexOfPower) {
                changeFormula(index);
            }
        }
    }

    private void changeFormula(Integer index) {

        String numberLeft = "",
                numberRight = "";

        for (int i = index + 1; i < inputTextView.length(); i++) {
            if (isNumeric(input.charAt(i))) {
                numberRight = numberRight + input.charAt(i);
            } else {
                break;
            }
        }
        for (int i = index - 1; i >= 0; i--) {
            if (isNumeric(input.charAt(i))) {
                numberLeft = numberLeft + input.charAt(i);
            } else {
                break;
            }
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";
        input = input.replace(original, changed);
    }

    boolean isNumeric(char c) {
        if ((c <= '9' && c >= '0') || c == '.') {
            return true;
        }
        return false;
    }


    public void undoOnClick(View view) {
        String s = inputTextView.getText().toString();
        if (!s.isEmpty())
            inputTextView.setText(s.substring(0, s.length() - 1));
    }


}