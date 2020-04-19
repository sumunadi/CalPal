package com.example.salah.calpal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //create private ImageButton Object
    private ImageButton setting_button;
    TextView Cremaining, Premaining;
    EditText MinusCal, MinusPro;
    Button plusCal;

    String calData, proData;
    SharedPreferences prefs;
    int calint, proint, Calinput, ProInput, backButtonCount;


    //On App run
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SharePreferences to pull stored values
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        calData = prefs.getString("Cal_Total", "0");
        proData = prefs.getString("Pro_Total", "0");


        //retrieve TxtView using findviewbyID
        Cremaining = (TextView) findViewById(R.id.RemainingCal);
        Premaining = (TextView) findViewById(R.id.RemainingPro);

        //Retrieve EditButton using findviewbyID

        MinusCal = (EditText) findViewById(R.id.EditCal);
        MinusPro = (EditText) findViewById(R.id.EditPro);

        //Retrieve Button using findViewbyID
        plusCal = (Button) findViewById(R.id.plusCal);


        //Set Protein and Calories either from settings or update from result
        Cremaining.setText(calData);
        Premaining.setText(proData);



        try {
            plusCal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences.Editor editor = prefs.edit();

                    //Subtract Calories from Input calories, then set total to calData and save and set text
                    if (MinusCal.getText().toString().matches("")) {

                        Toast.makeText(getApplicationContext(), "You did not enter Calories to Deduct", Toast.LENGTH_SHORT).show();
                        return;

                    } else if (MinusPro.getText().toString().matches("")) {

                        Toast.makeText(getApplicationContext(), "You did not enter Protein to Deduct", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //parse, subrtract and restore value back into SharePref
                        //--CALORIES
                        Calinput = Integer.parseInt(MinusCal.getText().toString());
                        calint = Integer.parseInt(calData);
                        calint -= Calinput;
                        calData = String.valueOf(calint);
                        editor.putString("Cal_Total", calData);
                        editor.commit();
                        calData = prefs.getString("Cal_Total", "0");
                        Cremaining.setText(calData);
                        MinusCal.getText().clear();

                        //--PROTEIN
                        ProInput = Integer.parseInt(MinusPro.getText().toString());
                        proint = Integer.parseInt(proData);
                        proint -= ProInput;
                        proData = String.valueOf(proint);
                        editor.putString("Pro_Total", proData);
                        editor.commit();
                        proData = prefs.getString("Pro_Total", "0");
                        Premaining.setText(proData);
                        MinusPro.getText().clear();

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        //set the image button using findviewbyID
        setting_button = (ImageButton) findViewById(R.id.settingsb);
        //set on lick listener for button
        setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this will listen for when button is clicked then tell it what to do.
                openSettingPage();
            }
        });


    }

    /**
     * Back button listener.
     * Will close the application if the back button pressed twice.
     */
    @Override
    public void onBackPressed() {

        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            //TOast is to popup text within Android. pass in what you want to popup
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    @Override
    public void onResume() {

        super.onResume();
        //App comes back from pause state aka different activity screen
        calData = prefs.getString("Cal_Total", "0");
        Cremaining.setText(calData);

        proData = prefs.getString("Pro_Total", "0");
        Premaining.setText(proData);
    }

    public void openSettingPage() {
        //call the activity you want to call
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }


}
