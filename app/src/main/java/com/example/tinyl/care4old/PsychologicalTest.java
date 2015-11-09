package com.example.tinyl.care4old;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class PsychologicalTest extends AppCompatActivity {          //16

    private Spinner spinner1=null;
    private Spinner spinner2=null;
    private Spinner spinner3=null;
    private Spinner spinner4=null;

    String valueSpinner1;
    String valueSpinner2;
    String valueSpinner3;
    String valueSpinner4;

    int valueSpinnerInt1 = 0;
    int valueSpinnerInt2 = 0;
    int valueSpinnerInt3 = 0;
    int valueSpinnerInt4 = 0;

    String[] Note6;
    String[] Note8;
    String[] Note10;

    int[] spinnersValues = new int[4];

    TextView total = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychological_test);
        total = (TextView)findViewById(R.id.total);

        Note6 = getResources().getStringArray(R.array.Notes_6);
        Note8 = getResources().getStringArray(R.array.Notes_8);
        Note10 = getResources().getStringArray(R.array.Notes_10);

        addListenerOnButton();
       // addListenerOnSpinnerItemSelection();

        // Array adapter to set data in Spinner Widget
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Note6);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Note8);
        ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Note10);

        // Setting the array adapter containing value list to the spinner widget
        spinner1.setAdapter(adapter8);
        spinner2.setAdapter(adapter8);
        spinner3.setAdapter(adapter6);
        spinner4.setAdapter(adapter10);

        AdapterView.OnItemSelectedListener valueSelectedListener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> spinner, View container,int position, long id) {
                int value = Integer.parseInt((String) spinner.getItemAtPosition(position));

// Erreur
                spinnersValues[((int) id)] = value;

                for (int i = 0; i < spinnersValues.length; i++){

                    int result = spinnersValues[0] + spinnersValues[1] + spinnersValues[2]+ spinnersValues[3];
                    total.setText(String.valueOf(result));
                }

                //spinner1_int = Integer.parseInt(selectedItem);

                //int test = (int) spinner.getItemAtPosition(position);
                //total.setText(selectedItem);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        };

        // Setting ItemClick Handler for Spinner Widget
        spinner1.setOnItemSelectedListener(valueSelectedListener);
        spinner2.setOnItemSelectedListener(valueSelectedListener);
        spinner3.setOnItemSelectedListener(valueSelectedListener);
        spinner4.setOnItemSelectedListener(valueSelectedListener);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

 /*   public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.ajust_postural);
        //spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

  /*      spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item=spinner1.getSelectedItem().toString();
               // String item = (String) parent.getItemAtPosition(pos);
                spinner1_int = Integer.parseInt(item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        String ajust_postu_value = spinner1.getSelectedItem().toString();
        spinner1_int = Integer.parseInt(ajust_postu_value);

        spinner2 = (Spinner) findViewById(R.id.rep_posturale);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        String rep_posturale_value = spinner2.getSelectedItem().toString();
        spinner2_int = Integer.parseInt(rep_posturale_value);

        spinner3 = (Spinner) findViewById(R.id.or_sensor);
        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        String or_sensor_value = spinner3.getSelectedItem().toString();
        spinner3_int = Integer.parseInt(or_sensor_value);

        spinner4 = (Spinner) findViewById(R.id.stab_march);
        spinner4.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        String stab_march_value = spinner4.getSelectedItem().toString();
        spinner4_int = Integer.parseInt(stab_march_value);

    }*/

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.ajust_postural);
        spinner2 = (Spinner) findViewById(R.id.rep_posturale);
        spinner3 = (Spinner) findViewById(R.id.or_sensor);
        spinner4 = (Spinner) findViewById(R.id.stab_march);

    }


}
