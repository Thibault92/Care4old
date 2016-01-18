package com.example.tinyl.care4old;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    // Connexion Data
    private EditText email;
    private EditText pass;
    private EditText passConfirm;
    private int account_type;

    // Common data
    private RadioGroup gender = null;
    private RadioButton genderButton;
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
    private RadioButton statusButton;
    private RadioGroup accompaniment = null;
    private RadioButton accompanimentButton;
    private RadioGroup residency = null;
    private RadioButton residencyButton;
    private RadioGroup isFinancial = null;
    private RadioButton isFinancialButton;
    private RadioGroup isHelp = null;
    private RadioButton isHelpButton;

    // Professional Data
    private EditText officename;

    private Button nextButton;
    private Button resetData;

    private Button patientButton;
    private Button physicianButton;

    private Button validatePatient;
    private Button resetPatientData;

    private Button validatePhysician;
    private Button resetPhysicianData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        chargeView();
        activityCommonListeners();
        // Create first form with basic infos

        //checkBasicInfoFilled();

        // Click next -> show new screen, choose doctor or patient


        // Click next -> show doc / patient specific screen

        // Click next -> generate HTTP and send request
    }

    private void chargeView(){

        email       = (EditText)findViewById(R.id.identifiantInscriptionInput);
        pass        = (EditText)findViewById(R.id.passwordInscriptionInput);
        passConfirm = (EditText)findViewById(R.id.confirmationPasswordInput);

        gender      = (RadioGroup)findViewById(R.id.genderChoice);
        genderButton= (RadioButton)findViewById(gender.getCheckedRadioButtonId());
        name        = (EditText)findViewById(R.id.familyNameInput);
        firstname   = (EditText)findViewById(R.id.firstNameInput);
        street      = (EditText)findViewById(R.id.addressInput);
        zip         = (EditText)findViewById(R.id.zipInput);
        city        = (EditText)findViewById(R.id.cityInput);
        phone       = (EditText)findViewById(R.id.homeTelInput);
        mobile      = (EditText)findViewById(R.id.mobileTelInput);

        resetData     = (Button) findViewById(R.id.raz);
        nextButton = (Button) findViewById(R.id.nextCreation);

    }

    private void activityCommonListeners(){

        email.addTextChangedListener(textWatcher);
        pass.addTextChangedListener(textWatcher);
        passConfirm.addTextChangedListener(textWatcher);

        //gender.getCheckedRadioButtonId();
        name.addTextChangedListener(textWatcher);
        firstname.addTextChangedListener(textWatcher);
        street.addTextChangedListener(textWatcher);
        zip.addTextChangedListener(textWatcher);
        city.addTextChangedListener(textWatcher);
        phone.addTextChangedListener(textWatcher);
        mobile.addTextChangedListener(textWatcher);

        resetData.setOnClickListener(reset);
        nextButton.setOnClickListener(continueCreation);

    }

    private View.OnClickListener continueCreation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(checkPatterns()){
                if (isPasswordsMatched()){
                    setContentView(R.layout.activity_registration_account_choice);
                    chargeChoiceView();
                    activityChoiceListeners();
                }
            }

        }

    };

    private void chargeChoiceView(){
        patientButton = (Button) findViewById(R.id.patientAccount);
        physicianButton = (Button) findViewById(R.id.physicianAccount);
    }

    private void activityChoiceListeners(){
        physicianButton.setOnClickListener(physician);
        patientButton.setOnClickListener(patient);
    }

    private View.OnClickListener physician = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_registration_physician);
            account_type = 1;
            chargeViewPhysician();
            chargePhysicianListener();

        }
    };

    private void chargeViewPhysician(){

        officename = (EditText)findViewById(R.id.officenameInput);
        validatePhysician = (Button)findViewById(R.id.validateCreation);
        resetPhysicianData = (Button) findViewById(R.id.raz);

    }

    private void chargePhysicianListener(){
        officename.addTextChangedListener(textWatcher);
        validatePhysician.setOnClickListener(savePhysicianData);
        resetPhysicianData.setOnClickListener(resetPhysician);
    }

    private View.OnClickListener savePhysicianData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new Thread(new Runnable() {
                public void run() {
                    try {
                        sendRegistration();
                    } catch (IOException e) {
                        Log.d("exception", e.toString());
                    }
                }
            }).start();

        }
    };

    private View.OnClickListener resetPhysician = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_registration_physician);
            account_type = 1;
            chargeViewPhysician();
            chargePhysicianListener();

        }
    };




    private View.OnClickListener patient = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_registration_account_patient);
            account_type = 0;
            chargeViewPatient();
            activityPatientListeners();

        }
    };

    private void chargeViewPatient(){
        physicianName   = (EditText)findViewById(R.id.nomMedecinInput);
        physicianMail   = (EditText)findViewById(R.id.emailMedecinInput);
        birthday        = (TextView)findViewById(R.id.birthdayPicker);
        emergency       = (EditText)findViewById(R.id.emergencyTelInput);
        status          = (RadioGroup)findViewById(R.id.statusChoice);
        statusButton    = (RadioButton)findViewById(status.getCheckedRadioButtonId());
        accompaniment   = (RadioGroup)findViewById(R.id.accompanimentChoice);
        accompanimentButton = (RadioButton)findViewById(accompaniment.getCheckedRadioButtonId());
        residency       = (RadioGroup)findViewById(R.id.residencyChoice);
        residencyButton = (RadioButton)findViewById(residency.getCheckedRadioButtonId());
        isFinancial     = (RadioGroup)findViewById(R.id.financialChoice);
        isFinancialButton = (RadioButton)findViewById(isFinancial.getCheckedRadioButtonId());
        isHelp          = (RadioGroup)findViewById(R.id.domicileChoice);
        isHelpButton = (RadioButton)findViewById(isHelp.getCheckedRadioButtonId());

        validatePatient = (Button)findViewById(R.id.validateCreation);
        resetPatientData = (Button) findViewById(R.id.raz);
    }

    private void activityPatientListeners(){

        physicianName.addTextChangedListener(textWatcher);
        physicianMail.addTextChangedListener(textWatcher);
        birthday.addTextChangedListener(textWatcher);
        emergency.addTextChangedListener(textWatcher);
        //status.getCheckedRadioButtonId();
        //accompaniment.getCheckedRadioButtonId();
        //residency.getCheckedRadioButtonId();
        //isFinancial.getCheckedRadioButtonId();
        //isHelp.getCheckedRadioButtonId();

        validatePatient.setOnClickListener(savePatientData);
        resetPatientData.setOnClickListener(resetPatient);

    }

    private View.OnClickListener savePatientData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


                new Thread(new Runnable() {
                    public void run() {
                        try {
                            sendRegistration();
                        } catch (IOException e) {
                            Log.d("exception", e.toString());
                        }
                    }
                }).start();
        }
    };

    private View.OnClickListener resetPatient = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_registration_account_patient);
            account_type = 0;
            chargeViewPatient();
            activityPatientListeners();

        }
    };


    private View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            setContentView(R.layout.activity_registration);
            chargeView();
            activityCommonListeners();

        }
    };



// Check all Edit Texts Patterns
    public boolean checkPatterns(){
        return
                checkEmailPattern(email.getText().toString()) &&
                checkPasswordPattern(pass.getText().toString()) &&
                checkOnlyTextPattern(name.getText().toString()) &&
                checkOnlyTextPattern(firstname.getText().toString()) &&
                checkOnlyTextPattern(city.getText().toString()) &&
                checkZipPattern(zip.getText().toString()) &&
                checkHomePhonePattern(phone.getText().toString()) &&
                checkMobilePhonePattern(mobile.getText().toString());
                //checkEmergencyPhone(emergencyPhone.getText().toString());
    }

    public boolean checkOnlyNumberPattern(String regex, String stringToMatch) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToMatch);
        return matcher.matches();
    }

    public boolean checkOnlyTextPattern(String onlyTextFields) {
        if(!checkOnlyNumberPattern("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)", onlyTextFields)) {
            Toast.makeText(RegistrationActivity.this, "Les champs Nom, Prénom, Ville ne doivent contenir que des lettres.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean checkZipPattern(String zip) {
        if(!checkOnlyNumberPattern("\\d{5}", zip)) {
            Toast.makeText(RegistrationActivity.this, "Retapez un code postal valide.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean checkHomePhonePattern(String telMaison) {
        if(!checkOnlyNumberPattern("^((\\+|00)33\\s?|0)[1-5](\\s?\\d{2}){4}$", telMaison)) {
            Toast.makeText(RegistrationActivity.this, "Retapez un numéro fixe valide.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean checkMobilePhonePattern(String telMobile) {
        if(!checkOnlyNumberPattern("^((\\+|00)33\\s?|0)[679](\\s?\\d{2}){4}$", telMobile)) {
            Toast.makeText(RegistrationActivity.this, "Retapez un numéro de mobile valide.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean checkEmergencyPhone(String telUrgence) {
        if(!checkOnlyNumberPattern("^((\\+|00)33\\s?|0)[1-5](\\s?\\d{2}){4}$", telUrgence)) {
            if (!checkOnlyNumberPattern("^((\\+|00)33\\s?|0)[679](\\s?\\d{2}){4}$", telUrgence)) {
                Toast.makeText(RegistrationActivity.this, "Retapez un numéro de téléphone valide.", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
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
        String url = "http://care4old.ajoubert.com/register";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = null;

        if (account_type == 0){
            urlParameters = "email="+this.email.getText().toString();
            urlParameters = urlParameters + "&pass="+this.pass.getText().toString();
            urlParameters = urlParameters + "&account="+Integer.toString(account_type);

            urlParameters = urlParameters + "&gender="+this.genderButton.getText().toString();
            urlParameters = urlParameters + "&name="+this.name.getText().toString();
            urlParameters = urlParameters + "&firstname="+this.firstname.getText().toString();
            urlParameters = urlParameters + "&street="+this.street.getText().toString();
            urlParameters = urlParameters + "&zip="+this.zip.getText().toString();
            urlParameters = urlParameters + "&city="+this.city.getText().toString();
            urlParameters = urlParameters + "&phone="+this.phone.getText().toString();
            urlParameters = urlParameters + "&mobile="+this.mobile.getText().toString();

            urlParameters = urlParameters + "&medecin_name="+this.physicianName.getText().toString();
            urlParameters = urlParameters + "&email_medecin="+this.physicianMail.getText().toString();
            urlParameters = urlParameters + "&birthday="+this.birthday.getText().toString();
            urlParameters = urlParameters + "&emergency="+this.emergency.getText().toString();
            urlParameters = urlParameters + "&status="+this.statusButton.getText().toString();
            urlParameters = urlParameters + "&accompaniment="+this.accompanimentButton.getText().toString();
            urlParameters = urlParameters + "&residency="+this.residencyButton.getText().toString();
            urlParameters = urlParameters + "&isFinancial="+this.isFinancialButton.getText().toString();
            urlParameters = urlParameters + "&isHelp="+this.isHelpButton.getText().toString();
        }
        else if (account_type == 1){
            urlParameters = "email="+this.email.getText().toString();
            urlParameters = urlParameters + "&pass="+this.pass.getText().toString();
            urlParameters = urlParameters + "&account="+Integer.toString(account_type);

            urlParameters = urlParameters + "&gender="+this.genderButton.getText().toString();
            urlParameters = urlParameters + "&name="+this.name.getText().toString();
            urlParameters = urlParameters + "&firstname="+this.firstname.getText().toString();
            urlParameters = urlParameters + "&street="+this.street.getText().toString();
            urlParameters = urlParameters + "&zip="+this.zip.getText().toString();
            urlParameters = urlParameters + "&city="+this.city.getText().toString();
            urlParameters = urlParameters + "&phone="+this.phone.getText().toString();
            urlParameters = urlParameters + "&mobile="+this.mobile.getText().toString();

            urlParameters = urlParameters + "&officename="+this.officename.getText().toString();
        }


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
        Log.d("POST Connection result", response.toString());
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
