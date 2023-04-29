package com.example.test.Activity;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

// CETTE CLASSE VAS PERMETTRE D'ACCEDER A LA BASE DE DONNEE CREER DANS LE FICHIER ASSETS
public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "bd_quizz.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
}
