package com.example.tinyl.care4old;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class RegistrationActivity extends AppCompatActivity {

    // Connexion Data
    private EditText email;
    private EditText pass;
    private EditText passConfirm;
    private int account_type;

    // Common data
    private RadioGroup gender = null;
    private EditText name;
    private EditText firstname;
    private EditText street;
    private EditText zip;
    private EditText city;
    private EditText phone;
    private EditText mobile;

    // Patient Data
    private EditText physicianName;
    private EditText physicianMail;
    private Calendar birthdayUser = Calendar.getInstance();
    private TextView birthday;
    private EditText emergency;
    private RadioGroup status = null;
    private RadioGroup accompaniment = null;
    private RadioGroup residency = null;
    private RadioGroup isFinancial = null;
    private RadioGroup isHelp = null;

    // Professional Data
    private EditText officename;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        chargeView();
        activityCommonListeners();
        // Create first form with basic infos

        checkBasicInfoFilled();
        // Click next -> show new screen, choose doctor or patient

        // Click next -> show doc / patient specific screen

        // Click next -> generate HTTP and send request
    }

    private void checkBasicInfoFilled() {
        if(email.length() != 0 && pass.length() != 0 && passConfirm.length() != 0 && name.length() != 0 && firstname.length() != 0
                && street.length() != 0 && zip.length() != 0 && city.length() != 0 && phone.length() != 0 && mobile.length() != 0) { //continue for all
            showFirstNextButton();
            nextButton();
        }
    }

    private void chargeView(){

        email       = (EditText)findViewById(R.id.identifiantInscriptionInput);
        pass        = (EditText)findViewById(R.id.passwordInscriptionInput);
        passConfirm = (EditText)findViewById(R.id.confirmationPasswordInput);

        gender      = (RadioGroup)findViewById(R.id.genderChoice);
        name        = (EditText)findViewById(R.id.familyNameInput);
        firstname   = (EditText)findViewById(R.id.firstNameInput);
        street      = (EditText)findViewById(R.id.addressInput);
        zip         = (EditText)findViewById(R.id.zipInput);
        city        = (EditText)findViewById(R.id.cityInput);
        phone       = (EditText)findViewById(R.id.homeTelInput);
        mobile      = (EditText)findViewById(R.id.mobileTelInput);

        physicianName   = (EditText)findViewById(R.id.nomMedecinInput);
        physicianMail   = (EditText)findViewById(R.id.emailMedecinInput);
        birthday        = (TextView)findViewById(R.id.birthdayPicker);
        emergency       = (EditText)findViewById(R.id.emergencyTelInput);
        status          = (RadioGroup)findViewById(R.id.statusChoice);
        accompaniment   = (RadioGroup)findViewById(R.id.accompanimentChoice);
        residency       = (RadioGroup)findViewById(R.id.residencyChoice);
        isFinancial     = (RadioGroup)findViewById(R.id.financialChoice);
        isHelp          = (RadioGroup)findViewById(R.id.domicileChoice);

        officename = (EditText)findViewById(R.id.officenameInput);

    }

    private void activityCommonListeners(){

        email.addTextChangedListener(textWatcher);
        pass.addTextChangedListener(textWatcher);
        passConfirm.addTextChangedListener(textWatcher);

        gender.getCheckedRadioButtonId();
        name.addTextChangedListener(textWatcher);
        firstname.addTextChangedListener(textWatcher);
        street.addTextChangedListener(textWatcher);
        zip.addTextChangedListener(textWatcher);
        city.addTextChangedListener(textWatcher);
        phone.addTextChangedListener(textWatcher);
        mobile.addTextChangedListener(textWatcher);
/*
        physicianName.addTextChangedListener(textWatcher);
        physicianMail.addTextChangedListener(textWatcher);
        birthday.addTextChangedListener(textWatcher);
        emergency.addTextChangedListener(textWatcher);
        status.getCheckedRadioButtonId();
        accompaniment.getCheckedRadioButtonId();
        residency.getCheckedRadioButtonId();
        isFinancial.getCheckedRadioButtonId();
        isHelp.getCheckedRadioButtonId();

        officename.addTextChangedListener(textWatcher);*/

        //resetData.setOnClickListener(reset);

        checkPatterns();
        isPasswordsMatched();

    }

    private void showFirstNextButton() {
        Button nextButton = (Button) findViewById(R.id.nextCreation);
        nextButton.setVisibility(View.VISIBLE);
    }

    private void nextButton(){

        setContentView(R.layout.activity_registration_account_choice);

    }
    private View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_registration);
            chargeView();
            activityCommonListeners();

        }
    };

    public boolean checkPatterns() {
        return checkEmailPattern(email.getText().toString()) && checkPasswordPattern(pass.getText().toString());
    }

    public boolean checkPattern(String regex, String stringToMatch) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToMatch);
        return matcher.matches();
    }

    public boolean checkEmailPattern(String email) {
        if(!checkPattern("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", email)) {
            Toast.makeText(RegistrationActivity.this, "Retapez une adresse mail valide.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean checkPasswordPattern(String motDePasse) {
        if(!checkPattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$", motDePasse)) {
            Toast.makeText(RegistrationActivity.this, "Votre mot de passe doit contenir au moins 6 caractères dont une minuscule, une majuscule, un chiffre, sans espace.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    public boolean isPasswordsMatched() {
        if (!pass.getText().toString().equals(passConfirm.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Les 2 mots de passe rentrés ne correspondent pas, veuillez vérifier vos informations.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private DatePickerDialog.OnDateSetListener birthdayPickerListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day){
            birthdayUser.set(year, month, day);
            birthday.setText(birthdayUser.get(Calendar.DAY_OF_MONTH) + "/" + (birthdayUser.get(Calendar.MONTH) + 1) + "/" + birthdayUser.get(Calendar.YEAR));
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    private boolean sendRegistration() throws IOException {
        String url = "https://care4old.ajoubert.com/register";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "email="+this.email;
        urlParameters = urlParameters + "&pass="+this.pass;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return Objects.equals(response.toString(), "success");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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
}
