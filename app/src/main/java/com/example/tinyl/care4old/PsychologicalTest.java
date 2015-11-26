package com.example.tinyl.care4old;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class PsychologicalTest extends AppCompatActivity {

   // private String[] orientationSensorielleNotes;
   // private String[] posturalNotes;
   // private String[] marchStabilityNotes;

    private Spinner[] mySpinners = new Spinner[4];

    private Map<Integer, Integer> spinnerValue = new HashMap<>();

    private TextView total = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychological_test);
        total = (TextView) findViewById(R.id.total_score);

        String[] orientationSensorielleNotes = getResources().getStringArray(R.array.Notes_6);
        String[] posturalNotes = getResources().getStringArray(R.array.Notes_8);
        String[] marchStabilityNotes = getResources().getStringArray(R.array.Notes_10);

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

            spinnerValue.put(spinner.getId(), value);
            resultCalculate();

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    };

    public void setListenerToSpinners() {

        for (int i = 0; i <mySpinners.length; i++) {
            mySpinners[i].setOnItemSelectedListener(valueSelectedListener);
        }

    }

    public void resultCalculate() {

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : spinnerValue.entrySet()) {
            result += entry.getValue();
        }
        total.setText(String.valueOf(result));
    }


// get the selected dropdown list value
    public void addListenerOnButton() {

        mySpinners[0] = (Spinner) findViewById(R.id.ajust_postural_score);
        mySpinners[1] = (Spinner) findViewById(R.id.rep_posturale_score);
        mySpinners[2] = (Spinner) findViewById(R.id.or_sensor_score);
        mySpinners[3] = (Spinner) findViewById(R.id.stab_march_score);

    }

}