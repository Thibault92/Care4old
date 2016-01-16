package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PhysicianPage extends AppCompatActivity {


    TextView gender;
    TextView adress;
    TextView zip;
    TextView city;
    TextView phone;
    TextView mobilePhone;
    TextView officename;
    TextView mail;
    TextView name;
    TextView firstname;

    private String[] physiData = {"azer","azer","azer","azer","azer","azer","azer","azer","azer","azer"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_page);

        chargeTextFields();
    }

    private void chargeTextFields(){

        gender = (TextView) findViewById(R.id.gender_bdd);
        gender.setText("M");

        adress = (TextView) findViewById(R.id.adress_bdd);
        adress.setText("42, impasse de la réponse");

        zip = (TextView) findViewById(R.id.zip_bdd);
        zip.setText("42442");

        city = (TextView) findViewById(R.id.city_bdd);
        city.setText("Univers");

        phone = (TextView) findViewById(R.id.phone_bdd);
        phone.setText("+42424242424");

        mobilePhone = (TextView) findViewById(R.id.mobile_phone_bdd);
        mobilePhone.setText("+6424242424");

        name = (TextView) findViewById(R.id.firstname_bdd);
        name.setText("+42emergency");

        firstname = (TextView) findViewById(R.id.name_bdd);
        firstname.setText("+42emergency");

        oldFields();
    }
    public void previousPage(View view)
    {
        Intent intent = new Intent(PhysicianPage.this, PatientPagePerso.class);
        startActivity(intent);
        finish();
    }

    public void oldFields(){

        //String Height, Weight, Bmi, Albumin, Crp, VitaminD, Frequency, Pressure, Gir;

        gender.setText(physiData[0]);
        adress.setText(physiData[1]);
        zip.setText(physiData[2]);
        city.setText(physiData[3]);
        phone.setText(physiData[4]);
        mobilePhone.setText(physiData[5]);
        mail.setText(physiData[6]);
        name.setText(physiData[7]);
        firstname.setText(physiData[8]);
        officename.setText(physiData[9]);


    }

}
