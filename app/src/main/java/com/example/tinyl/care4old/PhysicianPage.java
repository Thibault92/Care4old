package com.example.tinyl.care4old;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PhysicianPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_page);

    }

    public void previousPage(View view)
    {
        Intent intent = new Intent(PhysicianPage.this, PatientPagePerso.class);
        startActivity(intent);
        finish();
    }

}
