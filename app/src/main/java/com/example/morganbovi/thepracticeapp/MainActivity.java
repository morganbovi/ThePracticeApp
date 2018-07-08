package com.example.morganbovi.thepracticeapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView answerTextView;
    EditText thisEditText;
    EditText thatEditText;
    TextView byTextView;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateButton = findViewById(R.id.calculate);
        thisEditText = findViewById(R.id.thisNumber);
        thatEditText = findViewById(R.id.thatNumber);
        byTextView = findViewById(R.id.by);
        answerTextView = findViewById(R.id.answer);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("+");
        arrayAdapter.add("-");
        arrayAdapter.add("/");
        arrayAdapter.add("X");
        arrayAdapter.add("Mason");
        arrayAdapter.add("Gage");

        byTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setNegativeButton("cancel", null)
                        .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String option = arrayAdapter.getItem(which);
                                byTextView.setText(option);
                            }
                        }).show();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int thisNumber;
                int thatNumber;

                try {
                    thisNumber = Integer.valueOf(thisEditText.getText().toString());
                    thatNumber = Integer.valueOf(thatEditText.getText().toString());
                } catch (Exception e){
                    catAlert("Invalid Input!");
                    return;
                }

                int answer = 0;

                switch (byTextView.getText().toString()) {
                     case "+": {
                        answer = thisNumber + thatNumber;
                        break;
                    }
                    case "-": {
                        answer = thisNumber - thatNumber;
                        if (answer < 0){
                            catAlert("This is under 0 so cannot process");
                            answer = 0;
                        }
                        break;
                    }
                    case "X": {
                        answer = thisNumber * thatNumber;
                        break;
                    }
                    case "/": {

                        if (thisNumber == 0 || thatNumber == 0) {
                            catAlert("\"numbers cannot be 0\"");
                            break;
                        } else {
                            answer = thisNumber / thatNumber;
                            break;
                        }
                    }
                    case "Gage": {
                        catAlert("I cannot Gage a number! Gage farted!!!");
                        break;
                    }
                    default: {
                        catAlert("I am not programed for this!");
                    }
                }

                answerTextView.setText(String.valueOf(answer));

            }
        });

    }

    public void catAlert(String message){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Cat Alert")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
