package com.example.tinyl.care4old;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MedicalCheck extends AppCompatActivity {

    private TextView startDateDisplay;
    private Button startPickDate;
    private Calendar startDate;
    private Calendar endDate;

    static final int DATE_DIALOG_ID = 0;

    private TextView activeDateDisplay;
    private Calendar activeDate;

    Button envoyer = null;
    EditText poids = null;
    EditText taille = null;

    TextView result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_check);

        startDateDisplay = (TextView) findViewById(R.id.displayTestDate);
        startPickDate = (Button) findViewById(R.id.pickTestDate);

                /* get the current date */
       startDate = Calendar.getInstance();

                /* add a click listener to the button   */
        startPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(startDateDisplay, startDate);
            }
        });

                /* get the current date */
        endDate = Calendar.getInstance();


        /* display the current date (this method is below)  */
        updateDisplay(startDateDisplay, startDate);

        envoyer = (Button)findViewById(R.id.calcul);
        taille = (EditText)findViewById(R.id.sc_height);
        poids = (EditText)findViewById(R.id.sc_weight);
        result = (TextView)findViewById(R.id.sc_bmi);

        envoyer.setOnClickListener(envoyerListener);
        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);

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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //result.setText(defaut);
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    // Uniquement pour le bouton "envoyer"

    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                // On récupère la taille
                String t = taille.getText().toString();

                // On récupère le poids
                String p = poids.getText().toString();
                float tValue = Float.valueOf(t);

                // Puis on vérifie que la taille est cohérente
                    float pValue = Float.valueOf(p);

                        tValue = tValue / 100;

                    tValue = (float)Math.pow(tValue, 2);
                    float imc = pValue / tValue;
                    result.setText(String.valueOf(imc));
                }
        };
}
