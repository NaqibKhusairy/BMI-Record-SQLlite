package com.naqib.bmi_record_sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bmi_records";
    private static final String TABLE_NAME = "records";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_BMI = "bmi";
    private static final String COLUMN_STATUS = "status";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_WEIGHT + " REAL,"
                + COLUMN_HEIGHT + " REAL,"
                + COLUMN_BMI + " REAL,"
                + COLUMN_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<BMIRecord> getAllBMIRecords() {
        List<BMIRecord> bmiRecordList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_DATE, COLUMN_WEIGHT, COLUMN_HEIGHT, COLUMN_BMI, COLUMN_STATUS},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE));
                double weight = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT));
                double height = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_HEIGHT));
                double bmi = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_BMI));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS));

                BMIRecord bmiRecord = new BMIRecord(date, weight, height, bmi, status);
                bmiRecordList.add(bmiRecord);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return bmiRecordList;
    }

    public void addRecord(String date, double weight, double height, double bmi, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_WEIGHT, weight);
        values.put(COLUMN_HEIGHT, height);
        values.put(COLUMN_BMI, bmi);
        values.put(COLUMN_STATUS, status);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}