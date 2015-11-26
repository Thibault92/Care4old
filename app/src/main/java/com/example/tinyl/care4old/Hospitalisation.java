package com.example.tinyl.care4old;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Hospitalisation extends AppCompatActivity {

    private TextView startDateDisplay;
    private TextView endDateDisplay;
    private Button startPickDate;
    private Button endPickDate;
    private Calendar startDate;
    private Calendar endDate;

    static final int DATE_DIALOG_ID = 0;

    private TextView activeDateDisplay;
    private Calendar activeDate;

    /**
     * Private members of the class
     */
    /*
    private TextView displayEntryDate;
    private TextView displayExitDate;
    private Button pickEntryDate;
    private Button pickExitDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    /**
     * This integer will uniquely define the dialog to be used for displaying date picker.
     */
            /*
    static final int DATE_EXIT_DIALOG_ID = 1111;
    static final int DATE_ENTRY_DIALOG_ID = 0;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitalisation);

        startDateDisplay = (TextView) findViewById(R.id.displayEntryDate);
        startPickDate = (Button) findViewById(R.id.pickEntryDate);

            /* get the current date */
        startDate = Calendar.getInstance();

            /* add a click listener to the button   */
        startPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(startDateDisplay, startDate);
            }
        });

            /* capture our View elements for the end date function */
        endDateDisplay = (TextView) findViewById(R.id.displayExitDate);
        endPickDate = (Button) findViewById(R.id.pickExitDate);

            /* get the current date */
        endDate = Calendar.getInstance();

            /* add a click listener to the button   */
        endPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(endDateDisplay, endDate);
            }
        });

            /* display the current date (this method is below)  */
        updateDisplay(startDateDisplay, startDate);
        updateDisplay(endDateDisplay, endDate);
    }

    private void updateDisplay(TextView dateDisplay, Calendar date) {
        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                        .append(date.get(Calendar.YEAR)).append(" "));

    }

    public void showDateDialog(TextView dateDisplay, Calendar date) {
        activeDateDisplay = dateDisplay;
        activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();
        }
    };

    private void unregisterDateDisplay() {
        activeDateDisplay = null;
        activeDate = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }



/*
        // Capture our View elements
        displayExitDate = (TextView) findViewById(R.id.displayExitDate);
        displayEntryDate = (TextView) findViewById(R.id.displayEntryDate);
        pickExitDate = (Button) findViewById(R.id.pickExitDate);
        pickEntryDate = (Button) findViewById(R.id.pickEntryDate);

       //  Listener for click event of the button
        pickEntryDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_ENTRY_DIALOG_ID);
            }
        });

        pickExitDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_EXIT_DIALOG_ID);
            }
        });
    }


     // Callback received when the user "picks" a date in the dialog

    private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year,
                              int monthOfYear, int dayOfMonth) {
            pYear = year;
            pMonth = monthOfYear;
            pDay = dayOfMonth;
            updateDisplay();
            displayToast();
        }
    };


     // Updates the date in the TextView

    private void updateDisplay() {
        displayExitDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(pMonth + 1).append("/")
                        .append(pDay).append("/")
                        .append(pYear).append(" "));

        displayEntryDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(pMonth + 1).append("/")
                        .append(pDay).append("/")
                        .append(pYear).append(" "));
    }


     // Displays a notification when the date is updated

    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Date choisie: ").append(displayExitDate.getText()), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, new StringBuilder().append("Date choisie: ").append(displayEntryDate.getText()), Toast.LENGTH_SHORT).show();

    }


    // Create a new dialog for date picker

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ENTRY_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);

            case DATE_EXIT_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
        }
        return null;
    } */
}