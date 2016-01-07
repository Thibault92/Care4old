package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PatientPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_page);

    }

    public void sendPsycho(View view) {
        Intent intent = new Intent(PatientPage.this, PsychologicalTest.class);
        startActivity(intent);
    }


    public void sendHospitalisation(View view)
    {
        Intent intent = new Intent(PatientPage.this, Hospitalisation.class);
        startActivity(intent);
    }

    public void sendKine(View view)
    {
        Intent intent = new Intent(PatientPage.this, KinesitherapeuticalReport.class);
        startActivity(intent);
    }

    public void sendMCheck(View view)
    {
        Intent intent = new Intent(PatientPage.this, MedicalCheck.class);
        startActivity(intent);
    }

}
