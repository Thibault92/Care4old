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

public class KinesitherapeuticalReport extends AppCompatActivity {

    private Spinner[] mySpinners = new Spinner[2];

    private Map<Integer, Integer> spinnerValue = new HashMap<>();

    private TextView total = null;

    private TextView mDateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;

    private Button resetData;
    private Button saveData;

    EditText getUp = null;
    EditText slowWalk = null;
    EditText fastWalk = null;

    String[] equilibreNotes;
    String[] equilibreDynamiqueNotes;

    String Total, SlowWalk, FastWalk, GetUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinesitherapeutical_report);

        chargeViewKine();
        chargeListeners();

    }

    private void chargeViewKine(){

        total        = (TextView) findViewById(R.id.totaltinetti_score);
        mDateDisplay = (TextView) findViewById(R.id.displayTestDate);

        final Calendar c = Calendar.getInstance();
        mYear   = c.get(Calendar.YEAR);
        mMonth  = c.get(Calendar.MONTH);
        mDay    = c.get(Calendar.DAY_OF_MONTH);

        equilibreNotes          = getResources().getStringArray(R.array.Notes_16);
        equilibreDynamiqueNotes = getResources().getStringArray(R.array.Notes_12);


        slowWalk = (EditText) findViewById(R.id.lente_value);
        fastWalk = (EditText) findViewById(R.id.rapide_value);
        getUp    = (EditText) findViewById(R.id.temps_value);

        resetData = (Button) findViewById(R.id.reset);
        saveData  = (Button) findViewById(R.id.save);

    }

    private void chargeListeners(){

        updateStartDisplay();

        // Array adapter to set data in Spinner Widget
        ArrayAdapter<String> adapter16 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, equilibreNotes);
        ArrayAdapter<String> adapter12 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, equilibreDynamiqueNotes);

        addListenerOnButton();

        // Setting the array adapter containing value list to the spinner widget
        mySpinners[0].setAdapter(adapter16);
        mySpinners[1].setAdapter(adapter12);

        setListenerToSpinners();
        resultCalculate();

        slowWalk.addTextChangedListener(textWatcher);
        fastWalk.addTextChangedListener(textWatcher);
        getUp.addTextChangedListener(textWatcher);

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
        Intent intent = new Intent(KinesitherapeuticalReport.this, PatientPage.class);
        startActivity(intent);
        finish();
    }

    private View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_kinesitherapeutical_report);
            chargeViewKine();

        }
    };

    private boolean sendRegistration() throws IOException {
        String url = "http://care4old.ajoubert.com/kine";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = null;

        urlParameters = urlParameters + "&date_psycho="+this.mDateDisplay.getText().toString();
        urlParameters = urlParameters + "&minibesttest_score="+this.total.getText().toString();
        urlParameters = urlParameters + "&greco_global="+this.slowWalk.getText().toString();
        urlParameters = urlParameters + "&greco_immediat="+this.fastWalk.getText().toString();
        urlParameters = urlParameters + "&greco_differe="+this.getUp.getText().toString();



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
