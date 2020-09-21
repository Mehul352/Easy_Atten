package com.example.easy_atten;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {
    private EditText et_user_id,et_name,et_roll_no,et_dept,et_phone,et_email,et_password;
    ProgressDialog pd;
    Button b5;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13, cb14,cb15,cb16,cb17,cb18,cb19,cb20,cb21,cb22,cb23,cb24,cb25,cb26,cb27,cb28,cb29,cb30;
    FirebaseDatabase firebaseDatabase;
    StudentInformation studentInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        et_user_id=findViewById(R.id.editText5);
        et_name=findViewById(R.id.editText6);
        et_roll_no=findViewById(R.id.editText7);
        et_dept=findViewById(R.id.editText8);
        et_phone=findViewById(R.id.editText9);
        et_email=findViewById(R.id.editText10);
        et_password=findViewById(R.id.editText11);
        b5=findViewById(R.id.button5);
        studentInformation = new StudentInformation();


        firebaseAuth=FirebaseAuth.getInstance();
        pd=new ProgressDialog(this);
        if(firebaseAuth.getCurrentUser()!=null){
            Toast.makeText(AddStudentActivity.this, "Already signIn", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }


        cb1=findViewById(R.id.checkBox1);
        cb2=findViewById(R.id.checkBox2);
        cb3=findViewById(R.id.checkBox3);
        cb4=findViewById(R.id.checkBox4);
        cb5=findViewById(R.id.checkBox5);
        cb6=findViewById(R.id.checkBox6);
        cb7=findViewById(R.id.checkBox7);
        cb8=findViewById(R.id.checkBox8);

       //*********
        cb9=findViewById(R.id.checkBox9);
        cb10=findViewById(R.id.checkBox10);
        cb11=findViewById(R.id.checkBox11);
        cb12=findViewById(R.id.checkBox12);
        cb13=findViewById(R.id.checkBox13);
        cb14=findViewById(R.id.checkBox14);
        cb15=findViewById(R.id.checkBox15);
        cb16=findViewById(R.id.checkBox16);
        cb17=findViewById(R.id.checkBox17);
        cb18=findViewById(R.id.checkBox18);
        cb19=findViewById(R.id.checkBox19);
        cb20=findViewById(R.id.checkBox20);
        cb21=findViewById(R.id.checkBox21);
        cb22=findViewById(R.id.checkBox22);
        cb23=findViewById(R.id.checkBox23);
        cb24=findViewById(R.id.checkBox24);
        cb25=findViewById(R.id.checkBox25);
        cb26=findViewById(R.id.checkBox26);
        cb27=findViewById(R.id.checkBox27);
        cb28=findViewById(R.id.checkBox28);
        cb29=findViewById(R.id.checkBox29);
        cb30=findViewById(R.id.checkBox30);



    }

    public void fnRegister(View view) {
        Log.e("Entering ","In fn");

        String userid=et_user_id.getText().toString().trim();
        String name=et_name.getText().toString().trim();
        String rollno=et_roll_no.getText().toString().trim();
        String dept=et_dept.getText().toString().trim();
        String phone=et_phone.getText().toString().trim();
        String email=et_email.getText().toString().trim();
        String password=et_password.getText().toString().trim();

        //checking checkboxes

        ArrayList<Integer> subject=new ArrayList<Integer>(30);
        for(int i=0;i<30;i++)
            subject.add(0);
        if(!cb1.isChecked())
            subject.add(0,-1);
        if(!cb2.isChecked())
            subject.add(1,-1);
        if(!cb3.isChecked())
            subject.add(2,-1);
        if(!cb4.isChecked())
            subject.add(3,-1);
        if(!cb5.isChecked())
            subject.add(4,-1);
        if(!cb6.isChecked())
            subject.add(5,-1);
        if(!cb7.isChecked())
            subject.add(6,-1);
        if(!cb8.isChecked())
            subject.add(7,-1);
        if(!cb9.isChecked())
            subject.add(8,-1);
        if(!cb10.isChecked())
            subject.add(9,-1);
        if(!cb11.isChecked())
            subject.add(10,-1);
        if(!cb12.isChecked())
            subject.add(11,-1);
        if(!cb13.isChecked())
            subject.add(12,-1);
        if(!cb14.isChecked())
            subject.add(13,-1);
        if(!cb15.isChecked())
            subject.add(14,-1);
        if(!cb16.isChecked())
            subject.add(15,-1);
        if(!cb17.isChecked())
            subject.add(16,-1);
        if(!cb18.isChecked())
            subject.add(17,-1);
        if(!cb19.isChecked())
            subject.add(18,-1);
        if(!cb20.isChecked())
            subject.add(19,-1);
        if(!cb21.isChecked())
            subject.add(20,-1);
        if(!cb22.isChecked())
            subject.add(21,-1);
        if(!cb23.isChecked())
            subject.add(22,-1);
        if(!cb24.isChecked())
            subject.add(23,-1);
        if(!cb25.isChecked())
            subject.add(24,-1);
        if(!cb26.isChecked())
            subject.add(25,-1);
        if(!cb27.isChecked())
            subject.add(26,-1);
        if(!cb28.isChecked())
            subject.add(27,-1);
        if(!cb29.isChecked())
            subject.add(28,-1);
        if(!cb30.isChecked())
            subject.add(29,-1);

        int sum=0;
        for(int i=0;i<30;i++)
            sum=sum+subject.get(i);
        if(sum==-30){
            Toast.makeText(AddStudentActivity.this, "Please Select subjects", Toast.LENGTH_SHORT).show();
            return;
        }
        String arr[]=new String[]{ userid,name,rollno,dept,phone,email,password };
        for(int i=0;i<7;i++){
            if(TextUtils.isEmpty(arr[i])){
                Toast.makeText(AddStudentActivity.this, "Block cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(password.length()<6){
            Toast.makeText(AddStudentActivity.this, "Password should contain min. 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.e("Entering ", "In fn2");
        Toast.makeText(AddStudentActivity.this, "Validation Successful", Toast.LENGTH_SHORT).show();

        pd.setMessage("Registering User...");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();

        Log.e("Entering ", "In fn3");
        try{

            final StudentInformation stdinfo=new StudentInformation(name,rollno,dept,phone,email,password,subject);
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(AddStudentActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        saveInformation(stdinfo);
                        pd.dismiss();
                    } else {
                        Toast.makeText(AddStudentActivity.this, "Unable to register", Toast.LENGTH_SHORT).show();
                        Log.e("Exception is", task.getException().toString());
                        pd.dismiss();
                    }
                }
            });
        }catch(Exception e){
            Log.e("Exception is ",e.toString());
        }


    }
    public void saveInformation(StudentInformation stdinfo){

        try {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child(user.getUid()).setValue(stdinfo);
            //Toast.makeText(AddStudentActivity.this, "Information Stored", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("Exception is",e.toString());
        }

        firebaseAuth.signOut();
        //Toast.makeText(AddStudentActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
        //Log.e("Signed","out");

    }
}