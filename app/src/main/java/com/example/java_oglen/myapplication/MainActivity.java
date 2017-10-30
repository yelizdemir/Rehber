package com.example.java_oglen.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DB db;
    ArrayList<Kisi> dS = new ArrayList<>();
    ListView lv;
    LayoutInflater li;
    BaseAdapter ba;

    Button button;
    EditText ad;
    EditText soyad;
    EditText mail;
    EditText tel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ad=(EditText) findViewById(R.id.ad);
        soyad=(EditText) findViewById(R.id.soyad);
        mail=(EditText) findViewById(R.id.mail);
        tel=(EditText) findViewById(R.id.tel);
        button=(Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adi=ad.getText().toString();
                String soyadi=soyad.getText().toString();
                String maili=mail.getText().toString();
                String teli= tel.getText().toString();

                if (adi.isEmpty() || soyadi.isEmpty() || maili.isEmpty() || teli.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Boş bırakma !",Toast.LENGTH_LONG).show();
                }
                else
                {
                    DB db= new DB(MainActivity.this);
                    db.addKisi(adi,soyadi,maili,teli);
                    finish();
                }
            }
        });

        db = new DB(MainActivity.this);
        li = LayoutInflater.from(MainActivity.this);
        lv = (ListView) findViewById(R.id.listView);
        ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return dS.size();
            }

            @Override
            public Object getItem(int i) {
                return dS.get(i);
            }

            @Override
            public long getItemId(int i)
            {
                return dS.get(i).id;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup)
            {
                if (view == null)
                    view = li.inflate(R.layout.kisi_item, null);

                TextView tvAd = (TextView) view.findViewById(R.id.tvAd);
                TextView tvMail = (TextView) view.findViewById(R.id.tvMail);
                TextView tvTel= (TextView) view.findViewById(R.id.tvTel);

                Kisi k = dS.get(i);
                tvAd.setText(k.adi+" "+k.soyadi);
                tvMail.setText(""+k.mail);
                tvTel.setText(k.tel);

                return view;
            }
        };
        lv.setAdapter(ba);

    }
    protected void onResume()
    {
        super.onResume();
        dS = db.getKisiler();
        ba.notifyDataSetChanged();

        lv.setVisibility(dS.isEmpty() ? View.GONE : View.VISIBLE);
    }


    //data oku
    public void dataOku()
    {
        SQLiteDatabase oku=new DB(this).getReadableDatabase();
        Cursor cr=oku.query("kisiler",null,null,null,null,null,null);
        while(cr.moveToNext())
        {
            String adi=cr.getString(1);
            Log.e("Adi",adi);
        }

    }
}
