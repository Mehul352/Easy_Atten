package com.example.easy_atten;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StudentInformationActivity extends AppCompatActivity {
    StudentInformation std = new StudentInformation();

    String value="";
    String name="";
    TextView tvname,tvroll,tvdept,tvcontact,perText;
    ListView lv;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        fab = findViewById(R.id.floating_btn);
        perText = findViewById(R.id.textView13);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fn();
                startActivity(new Intent(StudentInformationActivity.this,CardviewStudent.class));
            }
        });


        std=new StudentInformation();
        std.setName("xyz");
        final ProgressDialog pd=new ProgressDialog(this,ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Fetching Information");
        pd.setCancelable(false);
        pd.show();

        Intent intent=getIntent();
        name=intent.getStringExtra("Name");
        Log.e("name is",name);
        if(name.equals("Email"))
            value=intent.getStringExtra("Email").trim();
        else
            value=intent.getStringExtra("RollNo");
        Log.e("email is", value);
        try{
            FirebaseDatabase database= FirebaseDatabase.getInstance();
            DatabaseReference databaseReference=database.getReference();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            StudentInformation std1=child.getValue(StudentInformation.class);
                            //check for subjects
                            if(name.equals("Email")&&std1.getEmail().equalsIgnoreCase(value))
                                std = std1;
                            else if(name.equals("RollNo")&&std1.getRollno().equalsIgnoreCase(value))
                                std = std1;
                        }
                        pd.dismiss();
                        fn();
                    } catch (Exception e) {
                        Log.e("Exception is", e.toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }catch(Exception e){
            Log.e("Exception is",e.toString());
        }

    }
    void fn(){
        try{
            if(std.getName().equals("xyz")&&name.equals("RollNo")){
                Toast.makeText(getApplicationContext(), "Roll No. not found", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(StudentInformationActivity.this,SelectRollNo.class);
                startActivity(intent);
                finish();
            }
        }catch(Exception ignored){

        }
        tvname=findViewById(R.id.textView14);
        tvroll=findViewById(R.id.textView17);
        tvdept=findViewById(R.id.textView18);
        tvcontact=findViewById(R.id.textView20);
        lv=findViewById(R.id.listView3);
        //Toast.makeText(StudentInformationActivity.this, "fn is called", Toast.LENGTH_SHORT).show();
        tvname.setText(std.getName());
        tvroll.setText(std.getRollno());
        tvdept.setText(std.getDepartment());
        tvcontact.setText(std.getPhone());
        ArrayList<Integer> al=std.getSubjects();
        ArrayList<String> subjectList=new ArrayList<String>();
        int count=0;
        int countTot=0;
        double sum1;
        double sum2;
        double sum3;

        for(int i=0;i<30;i++){
            int val=al.get(i);
            if(val!=-1){
                count=count+val%1000;
                String str="IT0"+(i+1)+"   ----   "+val%1000+" / ";
                sum1=val%1000;
                sum2=val/1000;
                sum3=sum1/sum2;
                sum3=sum3*100;
                val=val/1000;
                countTot=countTot+val%1000;

                str=str+val%1000+" Percentage "+new DecimalFormat("##.##").format(sum3)+"%";

                subjectList.add(str);

            }
        }
        if(countTot==0)
            countTot=1;
//     Toast tst  =  Toast.makeText(StudentInformationActivity.this, "Your Total Attendance is--"+(count*100)/countTot+"%", Toast.LENGTH_LONG);
//        tst.setGravity(Gravity.CENTER_VERTICAL | Gravity.START, 50, 40);
//        ViewGroup group = (ViewGroup) tst.getView();
//        TextView messageTextView = (TextView) group.getChildAt(0);
//        messageTextView.setTextSize(20);
//        messageTextView.setTextColor(Color.BLUE);
//        tst.show();
        perText.setText("Total Attendance - "+(count*100)/countTot+"%");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subjectList);
        lv.setAdapter(arrayAdapter);

    }
    //AlertDialog...
 /*   public void  onBackPressed()
    {
        showAlertDialog();
    }
    private void showAlertDialog()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to leave");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    */
}
