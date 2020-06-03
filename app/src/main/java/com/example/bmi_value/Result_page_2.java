package com.example.bmi_value;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Map;

public class Result_page_2 extends AppCompatActivity {


      static final String db_name="BMI";
      static final String tb_name="BMI_table";
      SQLiteDatabase db;

      public String result;

      public ListView listview;
      public EditText editText;
      public Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page_2);
       listview=findViewById(R.id.listview);
        editText=findViewById(R.id.editText3);




}

    public void search(View view) {     //按下搜尋按鈕


        result=editText.getEditableText().toString();

        cursor=get(result);
        UpdateAdapter(cursor);


    }



    public void search_all(View view) {  //按下按鈕搜尋所有

        cursor=getAll();
        UpdateAdapter(cursor);





    }
    public Cursor get(String result){
        db=openOrCreateDatabase("BMI",MODE_PRIVATE,null);  //打開已存在的資料庫
        Cursor cursor=db.rawQuery("SELECT * FROM BMI_table where name='"+result+"'",null); //搜尋editext 傳過來的值

        return cursor;


    }



    public Cursor getAll() {
        db=openOrCreateDatabase("BMI",MODE_PRIVATE,null);  //打開已存在的資料庫
        Cursor cursor=db.rawQuery("SELECT * FROM BMI_table",null); //搜尋editext 傳過來的值

      return cursor;
    }

    public void UpdateAdapter(Cursor cursor) {

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.list_view,cursor,new String[]{"name","height","weight","bmi"},new int[]{R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8},0);//把資料庫name，height，weight，bmi這些欄位資料放上來
        listview.setAdapter(adapter);

    }




}
