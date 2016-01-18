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

//Chage les layouts
    private void chargeViewHospital() {
        startDateDisplay = (TextView) findViewById(R.id.displayEntryDate);
        endDateDisplay   = (TextView) findViewById(R.id.displayExitDate);

        final Calendar c = Calendar.getInstance();
        mYear  = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay   = c.get(Calendar.DAY_OF_MONTH);

        final Calendar c2 = Calendar.getInstance();
        mYear2  = c2.get(Calendar.YEAR);
        mMonth2 = c2.get(Calendar.MONTH);
        mDay2   = c2.get(Calendar.DAY_OF_MONTH);

        commentary = (EditText) findViewById(R.id.raison);
        resetData  = (Button) findViewById(R.id.reset);
        saveData   = (Button) findViewById(R.id.save);

    }


//Charge les listeners
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

        saveData.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {
            }
        });
    }

// MAJ de la date une fois qu'elle est sélectionnée
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

//Sélectionne la date choisie dans le DatePicker
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

// Ouvre une pop-up permettant de choisir une date
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

// permet de mettre des messages par défaut dans les textView et EditText
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

//Permet de recharger la vue si besoin de réinitialisation
    private View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_hospitalisation);
            chargeViewHospital();
            chargeListeners();

        }
    };

}