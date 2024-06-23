package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private double first = 0;
    private double second = 0;
    private boolean isAddition = false;
    private boolean isSubtraction = false;
    private boolean isMultiplication = false;
    private boolean isDivision = false;
    private boolean isPercent = false;
    private double result= 0;
    private Boolean isOperationClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button_switch).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("result", result);
            startActivity(intent);
        });

        textView = findViewById(R.id.text_view);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onNumberClick(View view) {
        String textButton = ((MaterialButton) view).getText().toString();
        if (textButton.equals("AC")) {
            textView.setText("0");
            first = 0;
            second = 0;
            resetOperationFlags();
        } else if (textButton.equals(".")) {
            if (!textView.getText().toString().contains(".")) {
                textView.append(".");
            }

        } else if (textView.getText().toString().equals("0") || isOperationClicked) {
            textView.setText(textButton);
        } else {
            textView.append(textButton);
        }
        isOperationClicked = false;
    }

    public void onOperationClick(View view) {
        String inputValue = textView.getText().toString();
        boolean inputIsDouble = inputValue.contains(".");

        if (view.getId() == R.id.plus) {
            first = Double.parseDouble(inputValue);
            isAddition = true;
        } else if (view.getId() == R.id.minus) {
            first = Double.parseDouble(inputValue);
            isSubtraction = true;
        } else if (view.getId() == R.id.multiply) {
            first = Double.parseDouble(inputValue);
            isMultiplication = true;
        } else if (view.getId() == R.id.divide) {
            first = Double.parseDouble(inputValue);
            isDivision = true;
        } else if (view.getId() == R.id.percent) {
            first = Double.parseDouble(inputValue);
            isPercent = true;

        } else if (view.getId() == R.id.equality) {

            second = Double.parseDouble(inputValue);


            if (isAddition) {
                result = first + second;
            } else if (isSubtraction) {
                result = first - second;
            } else if (isMultiplication) {
                result = first * second;
            } else if (isPercent) {
                result = first / 100;
            } else if (isDivision) {
                if (second != 0) {
                    result = first / second;
                } else {
                    textView.setText("Error");
                    first = 0;
                    second = 0;
                    resetOperationFlags();
                    return;
                }
            }



            if (!inputIsDouble && !Double.toString(first).contains(".") && !Double.toString(second).contains(".")) {
                if (result == (int) result) {
                    textView.setText(String.format("%d", (int) result));
                } else {
                    textView.setText(String.format("%.1f", result));
                }
            } else {

                textView.setText(String.format("%s", result - (int) result == 0 ? String.format("%.0f", result) : result));
            }


         /*   Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("result", result);
            startActivity(intent);*/

            first = 0;
            second = 0;
            resetOperationFlags();
        }
        isOperationClicked = true;
    }



    private void resetOperationFlags() {
        isAddition = false;
        isSubtraction = false;
        isMultiplication = false;
        isDivision = false;
        isPercent = false;
    }
}
