package com.example.tinyl.care4old;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class PsychologicalTest extends AppCompatActivity {

    private Spinner spinner1=null;
    private Spinner spinner2=null;
    private Spinner spinner3=null;
    private Spinner spinner4=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychological_test);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.ajust_postural);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        spinner2 = (Spinner) findViewById(R.id.rep_posturale);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        spinner3 = (Spinner) findViewById(R.id.or_sensor);
        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        spinner4 = (Spinner) findViewById(R.id.stab_march);
        spinner4.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.ajust_postural);
        spinner2 = (Spinner) findViewById(R.id.rep_posturale);
        spinner3 = (Spinner) findViewById(R.id.or_sensor);
        spinner4 = (Spinner) findViewById(R.id.stab_march);

    }

    public void getValues(){

    }





}
