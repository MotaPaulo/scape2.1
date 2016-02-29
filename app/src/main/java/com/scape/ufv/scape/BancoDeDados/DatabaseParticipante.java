package com.scape.ufv.scape.BancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scape.ufv.scape.Bases.Participante;

import java.util.ArrayList;
import java.util.List;

public class DatabaseParticipante extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "db_particpante";

    // Contacts table name
    private static final String TABLE_PARTICIPANTE = "participante";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_EVENTO = "evento";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_INSTITUICAO = "instituicao";
    private static final String KEY_INSCRICAO = "inscricao";
    private static final String KEY_SENHA = "senha";
    private static final String CREATE_PARTICIPANTE_TABLE = "CREATE TABLE " + TABLE_PARTICIPANTE + "("
            + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NOME + " TEXT," + KEY_INSCRICAO + " TEXT," + KEY_EVENTO + " TEXT," + KEY_EMAIL + " TEXT," + KEY_INSTITUICAO + " TEXT," + KEY_SENHA + " TEXT" + ");";


    public DatabaseParticipante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PARTICIPANTE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTICIPANTE);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    public // Adding new contact
    void addParticipante(Participante participante) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, participante.getNome()); // Contact Name
        values.put(KEY_INSCRICAO, participante.getInscricao());
        values.put(KEY_EVENTO, participante.getEvento());
        values.put(KEY_EMAIL, participante.getEmail());
        values.put(KEY_INSTITUICAO, participante.getInstituicao());
        values.put(KEY_SENHA, participante.getSenha());


        // Inserting Row
        db.insert(TABLE_PARTICIPANTE, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Participante getParticipante(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PARTICIPANTE, new String[] { KEY_ID,
                KEY_NOME, KEY_INSCRICAO,KEY_EVENTO,KEY_EMAIL,KEY_INSTITUICAO,KEY_SENHA }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Participante participante = new Participante(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        // return contact
        return participante;
    }

    // Getting All Contacts
    public List<Participante> getAllContacts() {
        List<Participante> partList = new ArrayList<Participante>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PARTICIPANTE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Participante participante = new Participante();
                participante.setId(Integer.parseInt(cursor.getString(0)));
                participante.setNome(cursor.getString(1));
                participante.setInscricao(cursor.getString(2));
                participante.setEvento(cursor.getString(3));
                participante.setEmail(cursor.getString(4));
                participante.setInstituicao(cursor.getString(5));
                participante.setSenha(cursor.getString(6));


                // Adding contact to list
                partList.add(participante);
            } while (cursor.moveToNext());
        }

        // return contact list
        return partList;
    }

    // Updating single contact
    public int updateContact(Participante participante) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, participante.getNome()); // Contact Name
        values.put(KEY_INSCRICAO, participante.getInscricao());
        values.put(KEY_EVENTO, participante.getEvento());
        values.put(KEY_EMAIL, participante.getEmail());
        values.put(KEY_INSTITUICAO, participante.getInstituicao());
        values.put(KEY_SENHA, participante.getInstituicao());


        // updating row
        return db.update(TABLE_PARTICIPANTE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(participante.getId()) });
    }

    // Deleting single contact
    public void deleteContact(Participante participante) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PARTICIPANTE, KEY_ID + " = ?",
                new String[] { String.valueOf(participante.getId()) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PARTICIPANTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
