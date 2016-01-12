package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PatientPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_page);

    }

    public void openPsyTest(View view) {
        Intent intent = new Intent(PatientPage.this, PsychologicalTest.class);
        startActivity(intent);
        finish();
    }


    public void openHospitalisation(View view)
    {
        Intent intent = new Intent(PatientPage.this, Hospitalisation.class);
        startActivity(intent);
        finish();
    }

    public void openKineTest (View view)
    {
        Intent intent = new Intent(PatientPage.this, KinesitherapeuticalReport.class);
        startActivity(intent);
        finish();
    }

    public void openMCheck(View view)
    {
        Intent intent = new Intent(PatientPage.this, MedicalCheck.class);
        startActivity(intent);
        finish();
    }

    public void previousPage(View view)
    {
        Intent intent = new Intent(PatientPage.this, MainPagePro.class);
        startActivity(intent);
        finish();
    }

}
