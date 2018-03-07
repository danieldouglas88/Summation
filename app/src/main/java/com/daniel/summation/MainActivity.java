package com.daniel.summation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    //define variables for the widgets
    private EditText editTextNum1;
    private EditText editTextNum2;
    private Button button;
    private TextView textViewSum;


    //define the shared pref object
    private SharedPreferences savedValues;

    //define variables to be saved
    private int num1 = 0;
    private int num2 = 0;

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextNum1);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, "LOL");
        startActivity(intent);
    }

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

    public void displaySettings(View view) {
        //start settings activity
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void readSettings(View view){
        //read value that is stored in a key value pair
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String setting1 = prefs.getString("example_text", "Joe Smith");

        Toast.makeText(this, setting1, Toast.LENGTH_LONG).show();

    }
}