package com.example.easy_atten;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubjectList extends AppCompatActivity {
       ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_subject_list);
            lv =findViewById(R.id.listView);
            final String arr[]=new String[30];
            for(int i=0;i<30;i++){
                arr[0]="IT01 --- C";
                arr[1]="IT02 --- C++";
                arr[2]="IT03 --- HTML";
                arr[3]="IT04 --- CSS";
                arr[4]="IT05 --- MATHS-1";
                arr[5]="IT06 --- DBMS";
                arr[6]="IT07 --- RDBMS";
                arr[7]="IT08 --- JAVA";
                arr[8]="IT09 --- EJAVA";
                arr[9]="IT010 --- MATHS-2";
                arr[10]="IT011 --- C#";
                arr[11]="IT012 --- PYTHON";
                arr[12]="IT013 --- N/W";
                arr[13]="IT014 --- DEEP LEARNING";
                arr[14]="IT015 --- MACHINE LEARNING";
                arr[15]="IT016 --- AI";
                arr[16]="IT017 --- DATA STRUCTURE";
                arr[17]="IT018 --- KOTLIN";
                arr[18]="IT019 --- ANDROID";
                arr[19]="IT020 --- FLUTTER";
                arr[20]="IT021 --- BI";
                arr[21]="IT022 --- 8085";
                arr[22]="IT023 --- 8061";
                arr[23]="IT024 --- CYBER LAW";
                arr[24]="IT025 --- GEO INFORMATION";
                arr[25]="IT026 --- NETWORK SECURITY";
                arr[26]="IT027 --- CCNA";
                arr[27]="IT028 --- PYTHON 2";
                arr[28]="IT029 --- DATA SCIENCE";
                arr[29]="IT030 --- JAVA SCRIPT";

            }

            final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(SubjectList.this,Attendance2.class);
                    intent.putExtra("position",position+"");
                    startActivity(intent);
                  Toast toast  =  Toast.makeText(SubjectList.this,arr[position],Toast.LENGTH_SHORT);
                  toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.START, 180, 0);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(25);
                    messageTextView.setTextColor(Color.DKGRAY);
                    toast.show();
                }
            });
        }catch(Exception e){
            Log.e("Exception is" ,e.toString());
        }

    }

}



