package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PatientPage extends AppCompatActivity {

    TextView birthday;
    TextView gender;
    TextView adress;
    TextView zip;
    TextView city;
    TextView phone;
    TextView mobilePhone;
    TextView emergency;
    TextView status;
    TextView accompaniment;
    TextView residency;
    TextView isFinancial;
    TextView isPedicure;
    TextView isMaid;
    TextView isMeal;
    TextView isAssistance;
    TextView physician;
    TextView physicianMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_page);

        chargeTextFields();

    }

    private void chargeTextFields(){

        birthday = (TextView) findViewById(R.id.birthday_bdd);
        birthday.setText("01/03/1942");

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

        emergency = (TextView) findViewById(R.id.emergency_bdd);
        emergency.setText("+42emergency");

        physician = (TextView) findViewById(R.id.physician_bdd);
        physician.setText("+42emergency");

        physicianMail = (TextView) findViewById(R.id.physician_mail_bdd);
        physicianMail.setText("+42emergency");

        status = (TextView) findViewById(R.id.status_bdd);
        status.setText("Solo");

        accompaniment = (TextView) findViewById(R.id.accompaniment_bdd);
        accompaniment.setText("PC");

        residency = (TextView) findViewById(R.id.residency_bdd);
        residency.setText("vaisseau");

        isFinancial = (TextView) findViewById(R.id.is_financial_bdd);
        isFinancial.setText("Non");

        isPedicure = (TextView) findViewById(R.id.is_pedicure_bdd);
        isPedicure.setText("Non");

        isMaid = (TextView) findViewById(R.id.is_maid_bdd);
        isMaid.setText("Non");

        isMeal = (TextView) findViewById(R.id.is_meal_bdd);
        isMeal.setText("Oui");

        isAssistance = (TextView) findViewById(R.id.is_assistance_bdd);
        isAssistance.setText("Oui");

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
        Intent intent = new Intent(PatientPage.this, MenuPatient.class);
        startActivity(intent);
        finish();
    }

}
