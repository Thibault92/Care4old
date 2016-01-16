package com.example.tinyl.care4old;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;

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

    String Height, Weight, Bmi, Albumin, Crp, VitaminD, Frequency, Pressure, Gir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_check);

        chargeViewMedCheck();
        chargeListeners();

    }




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
                try{

                    // CALL GetText method to make post method call
                    GetText();
                }
                catch(Exception ex)
                {
                    //content.setText(" url exeption! " );
                }
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

    public  void  GetText()  throws UnsupportedEncodingException
    {

        //String Height, Weight, Bmi, Albumin, Crp, VitaminD, Frequency, Pressure, Gir;
        // Get user defined values
        Height      = taille.getText().toString();
        Weight      = poids.getText().toString();
        Bmi         = result.getText().toString();
        Albumin     = albumine.getText().toString();
        Crp         = CRP.getText().toString();
        VitaminD    = vitD.getText().toString();
        Frequency   = frequency.getText().toString();
        Pressure    = pressureScore.getText().toString();

        // Create data variable for sent values to server

        String data = URLEncoder.encode("height", "UTF-8")
                + "=" + URLEncoder.encode(Height, "UTF-8");

        data += "&" + URLEncoder.encode("weight", "UTF-8") + "="
                + URLEncoder.encode(Weight, "UTF-8");

        data += "&" + URLEncoder.encode("bmi", "UTF-8")
                + "=" + URLEncoder.encode(Bmi, "UTF-8");

        data += "&" + URLEncoder.encode("albumin", "UTF-8")
                + "=" + URLEncoder.encode(Albumin, "UTF-8");

        data += "&" + URLEncoder.encode("crp", "UTF-8")
                + "=" + URLEncoder.encode(Crp, "UTF-8");

        data += "&" + URLEncoder.encode("vitamin_d", "UTF-8")
                + "=" + URLEncoder.encode(VitaminD, "UTF-8");

        data += "&" + URLEncoder.encode("frequency", "UTF-8")
                + "=" + URLEncoder.encode(Frequency, "UTF-8");

        data += "&" + URLEncoder.encode("pressure", "UTF-8")
                + "=" + URLEncoder.encode(Pressure, "UTF-8");

        /*data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(Albumin, "UTF-8");*/

        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://https://care4old.ajoubert.com/medical_check");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        // Show response on activity
        //content.setText( text  );

    }

}
