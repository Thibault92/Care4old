package com.example.tinyl.care4old;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PatientPagePerso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_page_perso);



    }

    public void quitApp(View view)
    {
        finish();
    }

}
