package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class Care4Old extends AppCompatActivity {


    String password = null;
    String login = null;

    Button inscription = null;
    Button mdp_oublie = null;
    Button connexion = null;

    EditText mdp = null;
    EditText identifiant = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care4_old);


        inscription = (Button)findViewById(R.id.inscription);
        mdp_oublie = (Button)findViewById(R.id.mdp_oublie);
        connexion = (Button)findViewById(R.id.connexion);
        mdp = (EditText)findViewById(R.id.mdp);
        identifiant = (EditText)findViewById(R.id.identifiant);

        inscription.setOnClickListener(inscriptionListener);
        mdp_oublie.setOnClickListener(mdp_oublieListener);
        connexion.setOnClickListener(connexionListener);

    }


    private View.OnClickListener connexionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            password = mdp.getText().toString();
            login = identifiant.getText().toString();


        }
    };

    private View.OnClickListener mdp_oublieListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            password = null;
            login = null;

        }
    };

    private View.OnClickListener inscriptionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            password = null;
            login = null;

        }
    };

    public void sendPsycho(View view)
    {
        Intent intent = new Intent(Care4Old.this, PsychologicalTest.class);
        startActivity(intent);
    }
    // });


    public void sendHospitalisation(View view)
    {
        Intent intent = new Intent(Care4Old.this, Hospitalisation.class);
        startActivity(intent);
    }

    public void sendKine(View view)
    {
        Intent intent = new Intent(Care4Old.this, KinesitherapeuticalReport.class);
        startActivity(intent);
    }

    public void sendPAccount(View view)
    {
        Intent intent = new Intent(Care4Old.this, ProfessionnalAccount.class);
        startActivity(intent);
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_care4_old);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_care4_old, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


   // final Button click = (Button)findViewById(R.id.psycho_test);
    //click.setOnClickListener(new View.OnClickListener() {
        public void sendPsycho(View view)
        {
            Intent intent = new Intent(Care4Old.this, PsychologicalTest.class);
            startActivity(intent);
        }
   // });


    public void sendHospitalisation(View view)
    {
        Intent intent = new Intent(Care4Old.this, Hospitalisation.class);
        startActivity(intent);
    }

    public void sendKine(View view)
    {
        Intent intent = new Intent(Care4Old.this, KinesitherapeuticalReport.class);
        startActivity(intent);
    }*/

}
