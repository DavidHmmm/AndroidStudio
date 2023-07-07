package com.David_202102259.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.core.database.sqlite.SQLiteDatabaseKt;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "project.db";

    public DBHelper(Context context) {
        super(context, "project.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
        sqLiteDatabase.execSQL("create table buku(kode TEXT primary key, judul TEXT, pengarang TEXT, penerbit TEXT, isbn TEXT)");
        sqLiteDatabase.execSQL("create table biodata(nim TEXT primary key, nama TEXT, jeniskelamin TEXT, alamat TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists buku");
        sqLiteDatabase.execSQL("drop table if exists biodata");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if (result ==0) return false;
        else
            return true;
    }

    public Boolean insertDataMhs (String nim,String nama, String jeniskelamin, String alamat, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nim",nim);
        values.put("nama",nama);
        values.put("jeniskelamin", jeniskelamin);
        values.put("alamat", alamat);
        values.put("email", email);
        long result = db.insert("biodata", null,values);
        if (result==0) return false;
        else
            return true;
    }

    public Cursor tampilData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from biodata", null);
        return cursor;
    }

    public boolean hapusData(String nim){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from biodata where nim=?", new String[]{nim});
        if (cursor.getCount()>0) {
            long result = db.delete("biodata", "nim =?", new String[]{nim});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }   else {
            return false;
        }
    }

    public Boolean editData (String nim,String nama, String jeniskelamin, String alamat, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama",nama);
        values.put("jeniskelamin", jeniskelamin);
        values.put("alamat", alamat);
        values.put("email", email);
        Cursor cursor = db.rawQuery("Select * from biodata where nim=?", new String[]{nim});
        if (cursor.getCount()>0) {
            long result = db.update("biodata", values, "nim=?", new String[]{nim});
            if (result==1) {
                return false;
            } else {
                return true;
            }
        }   else {
            return false;
        }
    }

    public Boolean checkkodemhs (String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from biodata where nim=?", new String[] {nim});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean insertDataBku (String kode,String judul, String pengarang, String penerbit, String isbn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kode",kode);
        values.put("judul",judul);
        values.put("pengarang", pengarang);
        values.put("penerbit", penerbit);
        values.put("isbn", isbn);
        long result = db.insert("buku", null,values);
        if (result==0) return false;
        else
            return true;
    }

    public Cursor tampilDataBku(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from buku", null);
        return cursor;
    }

    public boolean hapusDataBku(String kode){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from buku where kode=?", new String[]{kode});
        if (cursor.getCount()>0) {
            long result = db.delete("buku", "kode =?", new String[]{kode});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }   else {
            return false;
        }
    }

    public Boolean editDataBku (String kode,String judul, String pengarang, String penerbit, String isbn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("judul",judul);
        values.put("pengarang", pengarang);
        values.put("penerbit", penerbit);
        values.put("isbn", isbn);
        Cursor cursor = db.rawQuery("Select * from buku where kode=?", new String[]{kode});
        if (cursor.getCount()>0) {
            long result = db.update("buku", values, "kode=?", new String[]{kode});
            if (result==1) {
                return false;
            } else {
                return true;
            }
        }   else {
            return false;
        }
    }

    public Boolean checkkodebku (String kode){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from buku where kode=?", new String[] {kode});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //check username function
    public Boolean checkusername(String username){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //check username Password function
    public Boolean checkusernamepassword    (String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
