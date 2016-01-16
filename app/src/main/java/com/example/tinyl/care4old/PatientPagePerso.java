package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PatientPagePerso extends AppCompatActivity {

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

    private String[] patientData= {"aq","azd","a","aq","azd","a","aq","azd","a","aq","azd","a","aq","azd","a","aq","azd","a"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_page_perso);

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

        oldFields();

    }

    public void openMedecin(View view)
    {
        Intent intent = new Intent(PatientPagePerso.this, PhysicianPage.class);
        startActivity(intent);
        finish();
    }

    public void quitApp(View view)
    {
        finish();
    }

    public void oldFields(){

        //String Height, Weight, Bmi, Albumin, Crp, VitaminD, Frequency, Pressure, Gir;

        birthday.setText(patientData[0]);
        gender.setText(patientData[1]);
        adress.setText(patientData[2]);
        zip.setText(patientData[3]);
        city.setText(patientData[4]);
        phone.setText(patientData[5]);
        mobilePhone.setText(patientData[6]);
        emergency.setText(patientData[7]);
        status.setText(patientData[8]);
        accompaniment.setText(patientData[9]);
        residency.setText(patientData[10]);
        isFinancial.setText(patientData[11]);
        isPedicure.setText(patientData[12]);
        isMaid.setText(patientData[13]);
        isMeal.setText(patientData[14]);
        isAssistance.setText(patientData[15]);
        physician.setText(patientData[16]);
        physicianMail.setText(patientData[17]);

    }

}
