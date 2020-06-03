package com.example.bmi_value;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String db_name="BMI";
    static final String tb_name="BMI_table";
    SQLiteDatabase db;


    public EditText ed0;
    public EditText ed1;
    public EditText ed2;


    public String name;
    public float height;
    public float weight;
    public float bmi;


    public TextView textView2;
    public TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed0=findViewById(R.id.editText0);
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);



        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String createTable="CREATE TABLE IF NOT EXISTS "+tb_name+"(_id INTERGER PRIMARY KEY ,name VARCHER(32),"+"height FLOAT(16),"+"weight FLOAT(16),"+"bmi FLOAT(16))";//新增資料表
        db.execSQL(createTable);







    }

    public void bt1(View view) {


         if ( !("".equals(ed1.getText().toString())                                    //查詢exitext是否有值
                || "".equals(ed2.getText().toString())|| "".equals(ed0.getText().toString())))
        {
            name=ed0.getEditableText().toString();
            height = Float.parseFloat(ed1.getEditableText().toString());      // 取得 身高輸入值
            weight = Float.parseFloat(ed2.getEditableText().toString());
            height=height/100;
            bmi= (float)(Math.round((weight/(height*height))/0.01)*0.01);

            if (bmi < 18.5) {
                textView3.setText(bmi + "體重過輕");
            } else if (18.5 <= bmi && bmi < 24) {
                textView3.setText(bmi + "正常範圍");
            } else if (24 <= bmi && bmi < 27) {
                textView3.setText(bmi + "過重");
            } else if (27 <= bmi && bmi < 30) {
                textView3.setText(bmi + "輕度肥胖");
            } else if (30 <= bmi && bmi < 35) {
                textView3.setText(bmi + "中度肥胖");
            } else if (bmi >= 35) {
                textView3.setText(bmi + "重度肥胖");
            }

            addData(name, height, weight, bmi);
        }

   else {

             Toast toast = Toast.makeText(MainActivity.this,
                     "沒有填資料", Toast.LENGTH_LONG);
             toast.show();
         }






    }

    public void bt2(View view) {                                   //按下按鈕轉換頁面
      Intent intent=new Intent(this,Result_page_2.class);
      startActivity(intent);


    }



    private void addData(String name, float height, float weight,float bmi){
        ContentValues cv=new ContentValues(3);

        cv.put("name",name);
        cv.put("height",height);
        cv.put("weight",weight);
        cv.put("bmi",bmi);
        db.insert(tb_name,null,cv);

    }


}
