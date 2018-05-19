package com.example.soft1614080902227activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Remote_0f_Air extends AppCompatActivity {
    private TextView wd_textview;
    private TextView fh_textview;
    private ImageView ms_imageview;
    private int dushu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_0f__air);



        wd_textview = findViewById(R.id.wendu);
        fh_textview = findViewById(R.id.textView);
        ms_imageview = findViewById(R.id.zhileng);
        dushu=Integer.valueOf(wd_textview.getText().toString());
        Button upbt_Click = (Button)findViewById(R.id.up);
        Button downbt_Click = (Button)findViewById(R.id.down);
        Button kbt_Click = (Button) findViewById(R.id.kai);
        Button gbt_Click = (Button) findViewById(R.id.guan);
        upbt_Click.setOnClickListener(new MyOnClickListener());
        downbt_Click.setOnClickListener(new MyOnClickListener());
        kbt_Click.setOnClickListener(new MyOnClickListener1());
        gbt_Click.setOnClickListener(new MyOnClickListener1());

    }
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            if(v.getId()==R.id.up){
                String str=wd_textview.getText().toString();
                dushu=Integer.parseInt(str);
                dushu++;
                str=String.valueOf(dushu);
                wd_textview.setText(str);

            }else if(v.getId()==R.id.down){
                String str=wd_textview.getText().toString();
                dushu=Integer.parseInt(str);
                dushu--;
                str=String.valueOf(dushu);
                wd_textview.setText(str);

            }

        }
    }

    class MyOnClickListener1 implements View.OnClickListener{
        public void onClick(View v){
            if(v.getId()==R.id.kai){
                UserDBHelper dbHelper1 = new UserDBHelper(Remote_0f_Air.this,"user_ino",null,1);
                SQLiteDatabase sqLiteDatabase1 = dbHelper1.getReadableDatabase();
                Cursor cursor = sqLiteDatabase1.query("user_ino",new String[]{"name","number"},"name=?",new String[]{"wendu"},null,null,null);
                String name= null;
                int number = 0;
                while (cursor.moveToNext()){
                    name=cursor.getString(cursor.getColumnIndex("name"));
                    number=cursor.getInt(cursor.getColumnIndex("number"));
                }
                int number1= 6;
                String str2 = String.valueOf(number);
                wd_textview.setText(str2);
                cursor.close();
                Button kbt_Click = (Button) findViewById(R.id.kai);
                Button gbt_Click = (Button) findViewById(R.id.guan);
                kbt_Click.setVisibility(View.INVISIBLE);
                gbt_Click.setVisibility(View.VISIBLE);
                //按开始时把温度数字显示设置为可见
                wd_textview.setVisibility(View.VISIBLE);
                fh_textview.setVisibility(View.VISIBLE);
                ms_imageview.setVisibility(View.VISIBLE);
                sqLiteDatabase1.close();
            }else if(v.getId()==R.id.guan){
                UserDBHelper dbHelper2 = new UserDBHelper(Remote_0f_Air.this,"user_ino",null,1);
                SQLiteDatabase sqLiteDatabase2 = dbHelper2.getWritableDatabase();
                ContentValues values2 = new ContentValues();
                values2.put("number",dushu);
                sqLiteDatabase2.update("user_ino",values2,"name=?",new String[]{"wendu"});
                sqLiteDatabase2.close();
                Button kbt_Click = (Button) findViewById(R.id.kai);
                Button gbt_Click = (Button) findViewById(R.id.guan);
                kbt_Click.setVisibility(View.VISIBLE);
                gbt_Click.setVisibility(View.INVISIBLE);
                //按关闭时把温度数字显示设置为不可见
                wd_textview.setVisibility(View.INVISIBLE);
                fh_textview.setVisibility(View.INVISIBLE);
                ms_imageview.setVisibility(View.INVISIBLE);
            }
        }
    }
}
