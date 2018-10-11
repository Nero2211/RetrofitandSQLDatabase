package com.development.nero.cellnovotechnicalexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RecordsDB";
    private static final String TABLE_NAME = "Records";
    private static final String KEY_ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME = "category_name";
    private static final String[] COLUMNS = {
            KEY_ID,
            NAME,
            DESCRIPTION,
            PRICE,
            CATEGORY_ID,
            CATEGORY_NAME
    };


    public SQLiteDatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
        String CREATION_TABLE = "CREATE TABLE Records ( "
                + "id TEXT, name TEXT, description TEXT, price TEXT," +
                "category_id TEXT, category_name TEXT)";

        sqLiteDatabase.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public Records getRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = ((android.database.sqlite.SQLiteDatabase) db).query(TABLE_NAME,
                COLUMNS,
                "id = ?",
                new String[] {String.valueOf(id)},
                null, null, null, null);

        if(cursor != null) cursor.moveToFirst();

        List<Records> records= new ArrayList<>();
        MyPojo myPojo = new MyPojo();
        Records myRecord = new Records();

        myRecord.setId(cursor.getString(0));
        myRecord.setName(cursor.getString(1));
        myRecord.setDescription(cursor.getString(2));
        myRecord.setPrice(cursor.getString(3));
        myRecord.setCategoryId(cursor.getString(4));
        myRecord.setCategoryName(cursor.getString(5));

        records.add(myRecord);
        myPojo.setRecords(records);

        return myRecord;
    }

    public void addRecords(Records records){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("KEY_ID", records.getId());
        values.put("NAME", records.getName());
        values.put("DESCRIPTION", records.getDescription());
        values.put("PRICE", records.getPrice());
        values.put("CATEGORY_ID", records.getCategoryId());
        values.put("CATEGORY_NAME", records.getCategoryName());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
