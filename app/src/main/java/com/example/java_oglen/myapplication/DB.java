package com.example.java_oglen.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


public class DB extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static String dbName="kisiler.db";
    private static int dbVersion=1;

    public DB(Context context) {
        super(context,dbName, null,dbVersion);

        getWritableDatabase().close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE `kisiler` (\n" +
                "\t`kid`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`adi`\tTEXT,\n" +
                "\t`soyadi`\tTEXT,\n" +
                "\t`mail`\tTEXT,\n" +
                "\t`tel`\tTEXT\n" +
                ");";

        db.execSQL(sql);
    }
    public void addKisi(String adi, String soyadi, String mail, String tel)
    {
        db = getWritableDatabase();
        String sql = "insert into kisiler (adi,soyadi,mail,tel) values ('%s', '%s', '%s','%s' )";
        sql = String.format(sql, adi, soyadi, mail, tel);

        Log.e("x","INS : "+sql);
        db.execSQL(sql);

        db.close();
    }
    public ArrayList<Kisi> getKisiler()
    {
        db = getWritableDatabase();
        ArrayList<Kisi> al = new ArrayList<>();
        Cursor c = db.rawQuery("select * from kisiler", null);
        while (c.moveToNext())
        {
            Kisi k = new Kisi(c);
            al.add(k);
        }
        c.close();
        db.close();
        return al;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS kisiler");
        onCreate(db);

    }
}
