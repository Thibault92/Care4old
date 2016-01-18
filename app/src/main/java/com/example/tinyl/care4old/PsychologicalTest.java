package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

;


public class PsychologicalTest extends AppCompatActivity {

    private Spinner[] mySpinners = new Spinner[4];

    private Map<Integer, Integer> spinnerValue = new HashMap<>();

    private TextView total = null;

    private TextView mDateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;

    private Button resetData;
    private Button saveData;

    EditText grecoGlobal;
    EditText grecoImmediat;
    EditText grecoDiff;

    String[] orientationSensorielleNotes;
    String[] posturalNotes;
    String[] marchStabilityNotes;

    String Total, GrecoGlobal, GrecoImmediat, GrecoDiffere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychological_test);

        chargeView();
        chargeListeners();

    }

    private void chargeView(){
        total = (TextView) findViewById(R.id.total_score);

// Display date of the test
        mDateDisplay = (TextView) findViewById(R.id.displayTestDate);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

// Save spinner values and calculate it automatically
        orientationSensorielleNotes = getResources().getStringArray(R.array.Notes_6);
        posturalNotes = getResources().getStringArray(R.array.Notes_8);
        marchStabilityNotes = getResources().getStringArray(R.array.Notes_10);



        grecoGlobal = (EditText) findViewById(R.id.sc_global_score);
        grecoImmediat = (EditText) findViewById(R.id.sc_ra_imm);
        grecoDiff = (EditText) findViewById(R.id.sc_ra_diff);

        resetData = (Button) findViewById(R.id.reset);
        saveData      = (Button) findViewById(R.id.save);

    }

    private void chargeListeners(){


        updateStartDisplay();

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

        grecoGlobal.addTextChangedListener(textWatcher);
        grecoImmediat.addTextChangedListener(textWatcher);
        grecoDiff.addTextChangedListener(textWatcher);

        resetData.setOnClickListener(reset);

        saveData.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v)
            {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            sendRegistration();
                        } catch (IOException e) {
                            Log.d("exception", e.toString());
                        }
                    }
                }).start();
            }
        });
    }



    private void updateStartDisplay() {
        mDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mYear).append(" "));


    }

// Permet dde récupérer chaque valeur
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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void sendPatient(View view)
    {
        Intent intent = new Intent(PsychologicalTest.this, PatientPage.class);
        startActivity(intent);
        finish();
    }

    private View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_psychological_test);
            chargeView();
            chargeListeners();

        }
    };


    private boolean sendRegistration() throws IOException {
        String url = "http://care4old.ajoubert.com/psycho";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = null;

        urlParameters = urlParameters + "&date_psycho="+this.mDateDisplay.getText().toString();
        urlParameters = urlParameters + "&minibesttest_score="+this.total.getText().toString();
        urlParameters = urlParameters + "&greco_global="+this.grecoGlobal.getText().toString();
        urlParameters = urlParameters + "&greco_immediat="+this.grecoImmediat.getText().toString();
        urlParameters = urlParameters + "&greco_differe="+this.grecoDiff.getText().toString();



        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        Log.d("POST Connection result", response.toString());
        return Objects.equals(response.toString(), "success");
    }


}