package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_patient);

    }

    public void openPatient(View view)
    {
        Intent intent = new Intent(MenuPatient.this, PatientPage.class);
        startActivity(intent);
        finish();
    }

    public void openHistorique(View view)
    {
        //TODO
    }

    public void openEval(View view)
    {
        //TODO
    }

    public void previousPage(View view)
    {
        Intent intent = new Intent(MenuPatient.this, MainPagePro.class);
        startActivity(intent);
        finish();
    }
}
