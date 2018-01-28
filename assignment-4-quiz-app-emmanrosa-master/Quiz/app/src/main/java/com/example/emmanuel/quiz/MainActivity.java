package com.example.emmanuel.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.app.AlertDialog;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //submit the quiz
    public void submit(View view) {
        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        //
        int score = 0;

        //display result
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(name + ", You scored " + String.valueOf(calculateScore(score))+" out of 8.");
        dlgAlert.setTitle("Result");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    //clear all
    public void clearAllButtonHandler(View v)
    {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        RadioButton button1 = (RadioButton)findViewById(R.id.radio_q1_1);
        RadioButton button2 = (RadioButton)findViewById(R.id.radio_q1_2);
        RadioButton button3 = (RadioButton)findViewById(R.id.radio_q1_3);
        RadioButton button4 = (RadioButton)findViewById(R.id.radio_q2_1);
        RadioButton button5 = (RadioButton)findViewById(R.id.radio_q2_2);
        RadioButton button6 = (RadioButton)findViewById(R.id.radio_q2_3);
        RadioButton button7 = (RadioButton)findViewById(R.id.radio_q3_1);
        RadioButton button8 = (RadioButton)findViewById(R.id.radio_q3_2);
        RadioButton button9 = (RadioButton)findViewById(R.id.radio_q3_3);
        RadioButton button10 = (RadioButton)findViewById(R.id.radio_q4_1);
        RadioButton button11 = (RadioButton)findViewById(R.id.radio_q4_2);
        RadioButton button12 = (RadioButton)findViewById(R.id.radio_q4_3);
        RadioButton button13 = (RadioButton)findViewById(R.id.radio_q5_1);
        RadioButton button14 = (RadioButton)findViewById(R.id.radio_q5_2);
        RadioButton button15 = (RadioButton)findViewById(R.id.radio_q5_3);
        RadioButton button16 = (RadioButton)findViewById(R.id.radio_q6_1);
        RadioButton button17 = (RadioButton)findViewById(R.id.radio_q6_2);
        RadioButton button18 = (RadioButton)findViewById(R.id.radio_q6_3);
        RadioButton button19 = (RadioButton)findViewById(R.id.radio_q7_1);
        RadioButton button20 = (RadioButton)findViewById(R.id.radio_q7_2);
        RadioButton button21 = (RadioButton)findViewById(R.id.radio_q7_3);
        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkbox_1);
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.checkbox_2);
        CheckBox checkbox3 = (CheckBox) findViewById(R.id.checkbox_3);
        CheckBox checkbox4 = (CheckBox) findViewById(R.id.checkbox_4);
        CheckBox checkbox5 = (CheckBox) findViewById(R.id.checkbox_5);

        nameField.setText("");
        button1.setChecked(false);
        button2.setChecked(false);
        button3.setChecked(false);
        button4.setChecked(false);
        button5.setChecked(false);
        button6.setChecked(false);
        button7.setChecked(false);
        button8.setChecked(false);
        button9.setChecked(false);
        button10.setChecked(false);
        button11.setChecked(false);
        button12.setChecked(false);
        button13.setChecked(false);
        button14.setChecked(false);
        button15.setChecked(false);
        button16.setChecked(false);
        button17.setChecked(false);
        button18.setChecked(false);
        button19.setChecked(false);
        button20.setChecked(false);
        button21.setChecked(false);
        checkbox1.setChecked(false);
        checkbox2.setChecked(false);
        checkbox3.setChecked(false);
        checkbox4.setChecked(false);
        checkbox5.setChecked(false);
    }

    // Calculate the score and return the score
   // private int calculateScore (boolean q1, boolean q2, boolean q3,boolean q4, boolean q5, boolean q6, boolean q7, boolean q8_1, boolean q8_2, boolean q8_3, boolean q8_4, boolean q8_5) {
    private int calculateScore (int score){
    //keep track of the score
        //int score = 0;
        RadioButton q1 = (RadioButton) findViewById(R.id.radio_q1_2);
        boolean ans1 = q1.isChecked();
        RadioButton q2 = (RadioButton) findViewById(R.id.radio_q2_3);
        boolean ans2 = q2.isChecked();
        RadioButton q3 = (RadioButton) findViewById(R.id.radio_q3_1);
        boolean ans3 = q3.isChecked();
        RadioButton q4 = (RadioButton) findViewById(R.id.radio_q4_3);
        boolean ans4 = q4.isChecked();
        RadioButton q5 = (RadioButton) findViewById(R.id.radio_q5_1);
        boolean ans5 = q5.isChecked();
        RadioButton q6 = (RadioButton) findViewById(R.id.radio_q6_1);
        boolean ans6 = q6.isChecked();
        RadioButton q7 = (RadioButton) findViewById(R.id.radio_q7_2);
        boolean ans7 = q7.isChecked();
        CheckBox q8_1 = (CheckBox) findViewById(R.id.checkbox_1);
        boolean ans8_1 = q8_1.isChecked();
        CheckBox q8_2 = (CheckBox) findViewById(R.id.checkbox_2);
        boolean ans8_2 = q8_2.isChecked();
        CheckBox q8_3 = (CheckBox) findViewById(R.id.checkbox_3);
        boolean ans8_3 = q8_3.isChecked();
        CheckBox q8_4 = (CheckBox) findViewById(R.id.checkbox_4);
        boolean ans8_4 = q8_4.isChecked();
        CheckBox q8_5 = (CheckBox) findViewById(R.id.checkbox_5);
        boolean ans8_5 = q8_5.isChecked();

    //adding and checking for the answers and the scores
        if (ans1) {
            score++;
        }if (ans2){
            score++;
        }if (ans3){
            score++;
        }if (ans4){
            score++;
        }if (ans5){
            score++;
        }if (ans6){
            score++;
        }if (ans7){
            score++;
        }if (ans8_2 && ans8_4 && !ans8_1 && !ans8_3 && !ans8_5) {
            score++;
        }else {
            score += 0;
        }

        return score;
    }

}
