package com.example.tinyl.care4old;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Objects;

public class MedicalCheck extends AppCompatActivity {

    Button envoyer = null;
    EditText poids = null;
    EditText taille = null;

    TextView result = null;


    private TextView mDateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int START_DATE_DIALOG_ID = 0;

    private Button resetData;
    private Button saveData;

    EditText pressureScore = null;
    EditText frequency = null;
    EditText vitD = null;
    EditText CRP = null;
    EditText albumine = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_check);

        chargeViewMedCheck();
        chargeListeners();

    }



//Initialisation des variables
    private void chargeViewMedCheck(){
        envoyer = (Button)findViewById(R.id.calcul);
        taille  = (EditText)findViewById(R.id.sc_height);
        poids   = (EditText)findViewById(R.id.sc_weight);
        result  = (TextView)findViewById(R.id.sc_bmi);

        mDateDisplay = (TextView) findViewById(R.id.displayTestDate);

        final Calendar c = Calendar.getInstance();
        mYear   = c.get(Calendar.YEAR);
        mMonth  = c.get(Calendar.MONTH);
        mDay    = c.get(Calendar.DAY_OF_MONTH);

        pressureScore = (EditText) findViewById(R.id.sc_pressure);
        frequency     = (EditText) findViewById(R.id.sc_freq);
        vitD          = (EditText) findViewById(R.id.sc_vitd);
        CRP           = (EditText) findViewById(R.id.sc_crp);
        albumine      = (EditText) findViewById(R.id.sc_albumin);

        resetData     = (Button) findViewById(R.id.reset);
        saveData      = (Button) findViewById(R.id.save);

    }

//CHargement des listeners
    private void chargeListeners(){

        envoyer.setOnClickListener(envoyerListener);
        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);

        mDateDisplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(START_DATE_DIALOG_ID);
            }
        });
        updateStartDisplay();

        pressureScore.addTextChangedListener(textWatcher);
        frequency.addTextChangedListener(textWatcher);
        vitD.addTextChangedListener(textWatcher);
        CRP.addTextChangedListener(textWatcher);
        albumine.addTextChangedListener(textWatcher);

        resetData.setOnClickListener(reset);

        saveData.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v)
            {

                // ouvre un nouveau thread permettant a la connexion de se faire en arrière-plan
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

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateStartDisplay();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case START_DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener endDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateStartDisplay();
                }
            };




    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //oldFields();
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                String t = taille.getText().toString();

                String p = poids.getText().toString();
                float tValue = Float.valueOf(t);

                    float pValue = Float.valueOf(p);

                        tValue = tValue / 100;

                    tValue = (float)Math.pow(tValue, 2);
                    float imc = pValue / tValue;
                    result.setText(String.valueOf(imc));
            }
    };

    public void sendPatient(View view)
    {
        Intent intent = new Intent(MedicalCheck.this, PatientPage.class);
        startActivity(intent);
        finish();
    }

    private View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_medical_check);
            chargeViewMedCheck();
            chargeListeners();

        }
    };

    private boolean sendRegistration() throws IOException {
        String url = "http://care4old.ajoubert.com/medical_check";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = null;

            urlParameters = urlParameters + "&date_visit="+this.mDateDisplay.getText().toString();
            urlParameters = urlParameters + "&height="+this.taille.getText().toString();
            urlParameters = urlParameters + "&weight="+this.poids.getText().toString();
            urlParameters = urlParameters + "&bmi="+this.result.getText().toString();
            urlParameters = urlParameters + "&albumin="+this.albumine.getText().toString();
            urlParameters = urlParameters + "&crp="+this.CRP.getText().toString();
            urlParameters = urlParameters + "&vitamin_d="+this.vitD.getText().toString();
            urlParameters = urlParameters + "&frequency="+this.frequency.getText().toString();
            urlParameters = urlParameters + "&pressure="+this.pressureScore.getText().toString();
            //urlParameters = urlParameters + "&gir="+this.mobile.getText().toString();


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*public void oldFields(){

        //String Height, Weight, Bmi, Albumin, Crp, VitaminD, Frequency, Pressure, Gir;

        taille.setText(medicalData[0]);
        poids.setText(medicalData[1]);
        result.setText(medicalData[2]);
        albumine.setText(medicalData[3]);
        CRP.setText(medicalData[4]);
        vitD.setText(medicalData[5]);
        frequency.setText(medicalData[6]);
        pressureScore.setText(medicalData[7]);

    }*/

}
