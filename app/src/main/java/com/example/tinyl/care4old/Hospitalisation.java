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

public class Hospitalisation extends AppCompatActivity {

    private TextView startDateDisplay;
    private TextView endDateDisplay;

    static final int START_DATE_DIALOG_ID = 0;
    static final int END_DATE_DIALOG_ID = 1;

    private int mYear;
    private int mMonth;
    private int mDay;

    private int mYear2;
    private int mMonth2;
    private int mDay2;

    private Button resetData;
    private Button saveData;

    EditText commentary = null;

    String dateStart, dateEnd, reason;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitalisation);

        chargeViewHospital();
        chargeListeners();

    }

    private void chargeViewHospital() {
        startDateDisplay = (TextView) findViewById(R.id.displayEntryDate);
        endDateDisplay = (TextView) findViewById(R.id.displayExitDate);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final Calendar c2 = Calendar.getInstance();
        mYear2 = c2.get(Calendar.YEAR);
        mMonth2 = c2.get(Calendar.MONTH);
        mDay2 = c2.get(Calendar.DAY_OF_MONTH);

        commentary = (EditText) findViewById(R.id.raison);
        resetData = (Button) findViewById(R.id.reset);

    }

    private void chargeListeners(){

        startDateDisplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(START_DATE_DIALOG_ID);
            }
        });

        endDateDisplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(END_DATE_DIALOG_ID);
            }
        });

        updateStartDisplay();
        updateEndDisplay();

        commentary.addTextChangedListener(textWatcher);
        resetData.setOnClickListener(reset);
    }

    private void updateStartDisplay() {
        startDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mYear).append(" "));


    }

    private void updateEndDisplay() {
        endDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay2).append("-")
                        .append(mMonth2 + 1).append("-")
                        .append(mYear2).append(" "));


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

    private DatePickerDialog.OnDateSetListener endDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear2 = year;
                    mMonth2 = monthOfYear;
                    mDay2 = dayOfMonth;
                    updateEndDisplay();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case START_DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
            case END_DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        endDateSetListener,
                        mYear2, mMonth2, mDay2);
        }
        return null;
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
        Intent intent = new Intent(Hospitalisation.this, PatientPage.class);
        startActivity(intent);
        finish();
    }

    private View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_hospitalisation);
            chargeViewHospital();
            chargeListeners();

        }
    };

    public  void  GetText()  throws UnsupportedEncodingException
    {

        //String dateStart, dateEnd, reason;
        // Get user defined values
        dateStart   = startDateDisplay.getText().toString();
        dateEnd     = endDateDisplay.getText().toString();
        reason      = commentary.getText().toString();


        // Create data variable for sent values to server

        String data = URLEncoder.encode("dateStart", "UTF-8")
                + "=" + URLEncoder.encode(dateStart, "UTF-8");

        data += "&" + URLEncoder.encode("dateEnd", "UTF-8") + "="
                + URLEncoder.encode(dateEnd, "UTF-8");

        data += "&" + URLEncoder.encode("reason", "UTF-8")
                + "=" + URLEncoder.encode(reason, "UTF-8");


        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://https://care4old.ajoubert.com/hospitalisation");

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