package com.example.tinyl.care4old;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tinyl on 07/12/15.
 */
/*public class MedCheckHelper extends SQLiteOpenHelper {

    public static final String TABLE_MEDCHECK = "Visite_Medicale";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "_Date";
    public static final String COLUMN_HEIGHT = "_Taille";
    public static final String COLUMN_WEIGHT = "_Poids";
    public static final String COLUMN_BMI = "_IMC";
    public static final String COLUMN_ALBUMIN = "_Aluminium";
    public static final String COLUMN_CRP = "_crp";
    public static final String COLUMN_VITD = "_vitD";

    private static final String DATABASE_NAME = "commments.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MEDCHECK + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";

    public MedCheckHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MedCheckHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDCHECK);
        onCreate(db);

}
*/