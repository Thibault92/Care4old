package com.example.tinyl.care4old;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class KinesitherapeuticalReport extends AppCompatActivity {

    private Spinner[] mySpinners = new Spinner[2];

    private Map<Integer, Integer> spinnerValue = new HashMap<>();

    private TextView total = null;

    private TextView mDateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinesitherapeutical_report);

        total = (TextView) findViewById(R.id.totaltinetti_score);

        mDateDisplay = (TextView) findViewById(R.id.displayTestDate);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateStartDisplay();

        String[] equilibreNotes = getResources().getStringArray(R.array.Notes_16);
        String[] equilibreDynamiqueNotes = getResources().getStringArray(R.array.Notes_12);

        // Array adapter to set data in Spinner Widget
        ArrayAdapter<String> adapter16 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, equilibreNotes);
        ArrayAdapter<String> adapter12 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, equilibreDynamiqueNotes);

        addListenerOnButton();

        // Setting the array adapter containing value list to the spinner widget
        mySpinners[0].setAdapter(adapter16);
        mySpinners[1].setAdapter(adapter12);

        setListenerToSpinners();
        resultCalculate();

    }

    private void updateStartDisplay() {
        mDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mYear).append(" "));


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

        mySpinners[0] = (Spinner) findViewById(R.id.equilibre_score);
        mySpinners[1] = (Spinner) findViewById(R.id.equilibre_dynamique_score);

    }

}
