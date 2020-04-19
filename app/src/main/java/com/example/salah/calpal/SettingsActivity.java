package com.example.salah.calpal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {


    EditText Protein_T, Calorie_T;
    Button setButton;

    String CalorieS, ProteinS, caltotal, prototal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Intents
        final Intent intent = new Intent(SettingsActivity.this, MainActivity.class);

        //connect variables to actual xml buttons
        setButton = (Button) findViewById(R.id.setbutton);
        Calorie_T = (EditText) findViewById(R.id.s_edit_cal);
        Protein_T = (EditText) findViewById(R.id.s_edit_prot);

        //Instantiating SharedPreferences
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = prefs.edit();


        caltotal = prefs.getString("Cal_Daily", "0");
        prototal = prefs.getString("Pro_Daily", "0");

        Calorie_T.setText(caltotal);
        Protein_T.setText(prototal);

        setButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                CalorieS = Calorie_T.getText().toString();
                ProteinS = Protein_T.getText().toString();

                //save caloriestring with an ID in sharedpreferences
                editor.putString("Cal_Total", CalorieS);
                editor.putString("Pro_Total", ProteinS);
                editor.putString("Cal_Daily", CalorieS);
                editor.putString("Pro_Daily", ProteinS);
                editor.apply();


            }
        });


    }

}



