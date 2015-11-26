package com.example.tinyl.care4old;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfessionnalAccount extends AppCompatActivity {

    String nom = null;
    String prenom = null;
    String metier = null;
    String adresse = null;

    Button ok = null;

    EditText name = null;
    EditText firstname = null;
    EditText profession = null;
    EditText adress = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professionnal_account);


        ok = (Button)findViewById(R.id.valider);
        name = (EditText)findViewById(R.id.name);
        firstname = (EditText)findViewById(R.id.fname);
        profession = (EditText)findViewById(R.id.profession);
        adress = (EditText)findViewById(R.id.adrC);


        ok.setOnClickListener(okListener);

    }

    private View.OnClickListener okListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            nom = name.getText().toString();
            prenom = firstname.getText().toString();
            metier = profession.getText().toString();
            adresse = adress.getText().toString();

        }
    };


}
