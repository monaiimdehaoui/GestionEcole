package org.gi5.eheio.ma.gestionecole.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    public static final String DATABASE_NAME = "GestionEcole";
    public static final String ETUDIANT_TABLE_NAME = "etudiant";
    public static final String CLASS__TABLE_NAME = "class";
    public static final String PROF__TABLE_NAME = "prof";
    public static final String CLASS_PROF_TABLE_NAME = "prof_class";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("ATTACH DATABASE 'GestionEcole' As 'GestionEcole';\n" +
                "CREATE TABLE IF NOT EXISTS etudiant(etudiant_id INTEGER PRIMARY KEY AUTOINCREMENT, class_id,\n" +
                "                    Username VARCHAR,Password VARCHAR,FirstName Varchar,\n" +
                "\t\t    LastName Varchar,filier varchar);\n" +
                " CREATE INDEX class_id_index ON class(class_id);\n" +
                "CREATE TABLE  IF NOT EXISTS prof(prof_id INTEGER PRIMARY KEY AUTOINCREMENT, prof_class_id\n" +
                "                    Username VARCHAR,Password VARCHAR,FirstName Varchar,\n" +
                "\t\t    LastName Varchar,filier varchar);\n" +
                "CREATE TABLE IF NOT EXISTS prof_class(prof_class_id INTEGER PRIMARY KEY AUTOINCREMENT, class_id,\n" +
                "                    prof_id);\n" +
                "CREATE INDEX classs_id_index ON class(class_id);\n" +
                "CREATE INDEX prof_id_index ON prof(prof_id);\n" +
                "CREATE TABLE IF NOT EXISTS class(class_id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar);\n" +
                "INSERT INTO etudiant VALUES('5 eme année');\n" +
                "INSERT INTO class VALUES(1,'etu','etu','monaiim','dehaoui','genie informatique');\n");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertEtudiant (String firstname, String lastname, String username, String password,String filiere,int classe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("filiere", filiere);
        contentValues.put("classe", classe);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from etudiant where id="+id+"", null );
        return res;
    }


}
