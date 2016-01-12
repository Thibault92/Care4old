package com.example.tinyl.care4old;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainPagePro extends AppCompatActivity {

    private static final String TAG = "junk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_pro);

        Log.i(TAG, "in content_main_page_pro");
        Intent intent = getIntent();
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        // get the query out of the intent
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);
            doSearchQuery(searchQuery);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "in onNewIntent");
        setIntent(intent);
        handleIntent(intent);
    }

    private void doSearchQuery(String query) {
        Log.i(TAG, "in doSearchQuery, query string: " + query);
//        Searching Query here
    }

    public void doDialogSearchQuery(View view) {
        onSearchRequested();
    }

    public void openPatient(View view)
    {
        Intent intent = new Intent(MainPagePro.this, PatientPage.class);
        startActivity(intent);
    }

    public void openPatientPerso(View view)
    {
        Intent intent = new Intent(MainPagePro.this, PatientPagePerso.class);
        startActivity(intent);
        finish();
    }

    public void quitApp(View view)
    {
        finish();
    }

}
