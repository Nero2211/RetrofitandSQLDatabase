package com.development.nero.cellnovotechnicalexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Product.db";
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
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
        String CREATION_TABLE = "CREATE TABLE " + TABLE_NAME +" (id TEXT, name TEXT, description TEXT, price TEXT, category_id TEXT, category_name TEXT)";

        sqLiteDatabase.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public List<Product> getRecord(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = ((android.database.sqlite.SQLiteDatabase) db).query(TABLE_NAME,
                COLUMNS,
                "name LIKE '%"+ id +"%'",
                null,
                null, null, null, null);

        if(cursor == null) {
            throw new CursorIndexOutOfBoundsException("CursorIndexOutOfBoundsException");
        }

        List<Product> records= new ArrayList<>();
        ProductsPayload productsPayload = new ProductsPayload();
        Product myRecord = null;

        while (cursor.moveToNext()) {
            myRecord = new Product();
            myRecord.setId(cursor.getString(0));
            myRecord.setName(cursor.getString(1));
            myRecord.setDescription(cursor.getString(2));
            myRecord.setPrice(cursor.getString(3));
            myRecord.setCategoryId(cursor.getString(4));
            myRecord.setCategoryName(cursor.getString(5));
            records.add(myRecord);
        }

        //product.add(myRecord);
        productsPayload.setProducts(records);

        //return myRecord;
        return records;
    }

    public void addRecords(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", product.getId());
        values.put("NAME", product.getName());
        values.put("DESCRIPTION", product.getDescription());
        values.put("PRICE", product.getPrice());
        values.put("CATEGORY_ID", product.getCategoryId());
        values.put("CATEGORY_NAME", product.getCategoryName());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Product getRecordbyID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = ((android.database.sqlite.SQLiteDatabase) db).query(TABLE_NAME,
                COLUMNS,
                "id = ?",
                new String[] {String.valueOf(id)},
                null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        List<Product> records= new ArrayList<>();
        ProductsPayload productsPayload = new ProductsPayload();
        Product myRecord = new Product();
        try {
            myRecord.setId(cursor.getString(0));
            myRecord.setName(cursor.getString(1));
            myRecord.setDescription(cursor.getString(2));
            myRecord.setPrice(cursor.getString(3));
            myRecord.setCategoryId(cursor.getString(4));
            myRecord.setCategoryName(cursor.getString(5));
        }catch (CursorIndexOutOfBoundsException e){
            myRecord = null;
        }

        records.add(myRecord);
        productsPayload.setProducts(records);

        return myRecord;
    }
}
