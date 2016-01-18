package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Care4Old extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care4_old);

    }

//Les deux fonctions sont liées au onClick dans activity_care4old.xml, et permettent d'ouvrir de nouvelles activités en fonction du bouton cliqué.
    public void openMainPage(View view){
        //Déclare la nouvelle activité a lancer
        Intent intent = new Intent(Care4Old.this, MainPagePro.class);
        //Lance la nouvelle activité
        startActivity(intent);
    }
    public void sendRegister(View view){
        Intent intent = new Intent(Care4Old.this, RegistrationActivity.class);
        startActivity(intent);
    }

}
