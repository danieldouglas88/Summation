package com.daniel.summation;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    //define variables for the widgets
    private EditText editTextNum1;
    private EditText editTextNum2;
    private Button button;
    private TextView textViewSum;

    private String subtotalString;

    //define the shared pref object
    private SharedPreferences savedValues;

    //define variables to be saved
    private int num1 = 0;
    private int num2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the widgets
        editTextNum1 = (EditText) findViewById(R.id.editTextNum1);
        editTextNum2 = (EditText) findViewById(R.id.editTextNum2);
        button = (Button) findViewById(R.id.button);
        textViewSum = (TextView) findViewById(R.id.textViewSum);

        //set the listener
        button.setOnClickListener((View.OnClickListener) this);

        //get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("subtotalString", subtotalString);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
    super.onResume();;
    //restore valye of the subtotal to the saved value
        subtotalString = savedValues.getString("subtotalString", "");
        textViewSum.setText(subtotalString);
        calculateAndDisplay();
    }

    public void calculateAndDisplay(){
        num1 = Integer.parseInt(editTextNum1.getText().toString());
        num2 = Integer.parseInt(editTextNum2.getText().toString());
        int sum = num1 + num2;

        textViewSum.setText(String.valueOf(sum));
    }

    @Override
    public void onClick(View view) {
        calculateAndDisplay();
    }
}