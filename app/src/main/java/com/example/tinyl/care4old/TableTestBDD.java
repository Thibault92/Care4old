package com.example.tinyl.care4old;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.util.Stack;

/**
 * Created by tinyl on 07/01/16.
 */
public class TableTestBDD extends SQLiteOpenHelper {

        private static final String TABLE_TEST = "table_test";
        private static final String COL_ID_PATIENT = "ID_Patient";
        private static final String COL_ID_TEST = "ID_test";
        private static final String COL_DATE_TEST = "Date";

    // Medical Check Fields
        private static final String COL_HEIGHT_MC = "Height";
        private static final String COL_WEIGHT_MC = "Weight";
        private static final String COL_IMC_MC = "IMC";
        private static final String COL_ALBUMIN_MC = "Albumin";
        private static final String COL_CRP_MC = "CRP";
        private static final String COL_VITD_MC = "Vitamin_D";
        private static final String COL_BMP_MC = "Beat_Per_Min";
        private static final String COL_PRESSURE_MC = "Blood_Pressure";
        private static final String COL_GIR_MC = "GIR";

    // Psychological Test Fields
        private static final String COL_MINIBEST_PT = "Minibest_Score";
        private static final String COL_GRECO_GLOBAL_PT = "Greco_Score";
        private static final String COL_GRECO_IMMEDIAT_PT = "Greco_Immediat";
        private static final String COL_GRECO_DIFFERE_PT = "Greco_Differe";

    // Kinetherapeutical Test Fields
        private static final String COL_TINETTI_POMA_KT = "Tinetti_Poma";
        private static final String COL_GETUPANDGO_KT = "Get_Up_And_Go";
        private static final String COL_SLOW_WALK = "Slow_Walk";
        private static final String COL_FAST_WALK = "Fast_Walk";

    // Hospitalisation Fields
        private static final String COL_DATE_ENTRY_H = "Date_Hospitalisation";
        private static final String COL_DATE_EXIT_H = "Sortie_Hospitalisation";
        private static final String COL_REASON = "Raison_Hospitalisation";

        private static final String CREATE_BDD = "CREATE TABLE " + TABLE_TEST + " ("
                + COL_ID_TEST + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ID_PATIENT + " INTEGER, "
                + COL_HEIGHT_MC + " INTEGER, " + COL_WEIGHT_MC + " INTEGER, "+ COL_IMC_MC + " INTEGER, "
                + COL_ALBUMIN_MC + " INTEGER, " + COL_ALBUMIN_MC + " INTEGER, " + COL_CRP_MC + " INTEGER, "
                + COL_VITD_MC + " INTEGER, " + COL_BMP_MC + " INTEGER, " + COL_PRESSURE_MC + " INTEGER, "
                + COL_GIR_MC + " INTEGER, " + COL_MINIBEST_PT + " INTEGER, " + COL_GRECO_GLOBAL_PT + " INTEGER, "
                + COL_GRECO_IMMEDIAT_PT + " INTEGER, "+ COL_GRECO_DIFFERE_PT + " INTEGER, " + COL_TINETTI_POMA_KT + " INTEGER, "
                + COL_GETUPANDGO_KT + " INTEGER, " + COL_SLOW_WALK + " INTEGER, " + COL_FAST_WALK + " INTEGER, "
                + COL_REASON + " TEXT NOT NULL);";

        public TableTestBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
            db.execSQL(CREATE_BDD);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
            //comme ça lorsque je change la version les id repartent de 0
            db.execSQL("DROP TABLE " + TABLE_TEST + ";");
            onCreate(db);
        }

    }

