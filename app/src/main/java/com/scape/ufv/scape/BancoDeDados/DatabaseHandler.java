package com.scape.ufv.scape.BancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scape.ufv.scape.Bases.Ativid;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_atividade";

    // Contacts table name
    private static final String TABLE_ATIVIDADE = "atividades";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "NOME";
    private static final String KEY_DATA = "Data";
    private static final String KEY_HORAIN = "Horaini";
    private static final String KEY_HORAFIM = "Horafim";
    private static final String KEY_Descricao = "Descricao";
    private static final String KEY_Tema = "Tema";
    private static final String KEY_ID_EVENTO = "ID_EVENTO";
    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ATIVIDADE + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT,"
            + KEY_Tema + " TEXT," + KEY_DATA + " TEXT," + KEY_HORAIN + " TEXT," + KEY_HORAFIM + " TEXT," + KEY_Descricao + " TEXT"+ ")";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATIVIDADE);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    public // Adding new contact
    void addContact(Ativid atividade) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, atividade.getNome()); // Contact Name
        values.put(KEY_Tema, atividade.getTema());
        values.put(KEY_DATA, atividade.getData());
        values.put(KEY_HORAIN, atividade.getHora_in());
        values.put(KEY_HORAFIM, atividade.getHora_fim());
        values.put(KEY_Descricao, atividade.getDescricao());


        // Inserting Row
        db.insert(TABLE_ATIVIDADE, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Ativid getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ATIVIDADE, new String[] { KEY_ID,
                        KEY_NAME, KEY_Tema,KEY_DATA,KEY_HORAIN,KEY_HORAFIM,KEY_Descricao }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Ativid contact = new Ativid(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Ativid> getAllContacts() {
        List<Ativid> contactList = new ArrayList<Ativid>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ATIVIDADE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ativid atividade = new Ativid();
                atividade.setId(Integer.parseInt(cursor.getString(0)));
                atividade.setNome(cursor.getString(1));
                atividade.setTema(cursor.getString(2));
                atividade.setData(cursor.getString(3));
                atividade.setHora_in(cursor.getString(4));
                atividade.setHora_fim(cursor.getString(5));
                atividade.setDescricao(cursor.getString(6));



                // Adding contact to list
                contactList.add(atividade);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(Ativid atividade) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, atividade.getNome()); // Contact Name
        values.put(KEY_Tema, atividade.getTema());
        values.put(KEY_DATA, atividade.getData());
        values.put(KEY_HORAIN, atividade.getHora_in());
        values.put(KEY_HORAFIM, atividade.getHora_fim());
        values.put(KEY_Descricao, atividade.getDescricao());


        // updating row
        return db.update(TABLE_ATIVIDADE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(atividade.getId()) });
    }

    // Deleting single contact
    public void deleteContact(Ativid atividade) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ATIVIDADE, KEY_ID + " = ?",
                new String[] { String.valueOf(atividade.getId()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ATIVIDADE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}