package com.example.tinyl.care4old;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class PsychologicalTest extends AppCompatActivity {

    private String[] orientationSensorielleNotes;
    private String[] posturalNotes;
    private String[] marchStabilityNotes;

    private Spinner[] mySpinners = new Spinner[4];

    private int[] spinnersValues = new int[4];

    private int valueExtracted = 0;

    private TextView total = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychological_test);
        total = (TextView) findViewById(R.id.total);

        orientationSensorielleNotes = getResources().getStringArray(R.array.Notes_6);
        posturalNotes = getResources().getStringArray(R.array.Notes_8);
        marchStabilityNotes = getResources().getStringArray(R.array.Notes_10);

        // Array adapter to set data in Spinner Widget
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, orientationSensorielleNotes);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, posturalNotes);
        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, marchStabilityNotes);

        addListenerOnButton();

        // Setting the array adapter containing value list to the spinner widget
        mySpinners[0].setAdapter(adapter8);
        mySpinners[1].setAdapter(adapter8);
        mySpinners[2].setAdapter(adapter6);
        mySpinners[3].setAdapter(adapter10);

        setListenerToSpinners();
        resultCalculate();

    }

    AdapterView.OnItemSelectedListener valueSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> spinner, View container,int position, long id) {
            int value = Integer.parseInt((String) spinner.getItemAtPosition(position));
            valueExtracted = value;
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    };


    public void selectValue(int numQuestion, int value){
        spinnersValues[numQuestion]=value;
    }

    public void setListenerToSpinners() {

        for (int i = 0; i <mySpinners.length; i++) {
            mySpinners[i].setOnItemSelectedListener(valueSelectedListener);
            selectValue(i, valueExtracted);
        }
    }

    public void resultCalculate() {

        int result = spinnersValues[0];
        for (int i = 1; i < spinnersValues.length; i++){

            result += spinnersValues[i];
        }

            total.setText(String.valueOf(result));
    }


// get the selected dropdown list value
    public void addListenerOnButton() {

        mySpinners[0] = (Spinner) findViewById(R.id.ajust_postural);
        mySpinners[1] = (Spinner) findViewById(R.id.rep_posturale);
        mySpinners[2] = (Spinner) findViewById(R.id.or_sensor);
        mySpinners[3] = (Spinner) findViewById(R.id.stab_march);

    }

}