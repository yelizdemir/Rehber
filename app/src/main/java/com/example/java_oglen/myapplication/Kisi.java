package com.example.java_oglen.myapplication;

import android.database.Cursor;

public class Kisi
{
    int id;
    String adi, soyadi, mail,tel;

    public Kisi(Cursor c)
    {
        id = c.getInt(0);
        adi = c.getString(1);
        soyadi = c.getString(2);
        mail = c.getString(3);
        tel = c.getString(4);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
