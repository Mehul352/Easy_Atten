package com.example.easy_atten;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CardviewStudent extends AppCompatActivity {

        CardView logout_stu;
        CardView viewStudent;
        CardView feedback_stu;
        CardView aboutus_stu;


    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview_student);

//        logout_stu = findViewById(R.id.Logout_Student);
//        viewStudent = findViewById(R.id.viewStu_student);
        feedback_stu = findViewById(R.id.feed_student);
        aboutus_stu = findViewById(R.id.aboutUs_student);



        feedback_stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardviewStudent.this,feedback.class));
                Toast.makeText(CardviewStudent.this, "Please Send your Feedback via email!", Toast.LENGTH_SHORT).show();

            }
        });
        aboutus_stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CardviewStudent.this, "About us Clicked...", Toast.LENGTH_SHORT).show();

            }
        });


        }




        //AlertDialog...
//        public void  onBackPressed()
//        {
//        showAlertDialog();
//        }
//        private void showAlertDialog()
//        {
//            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setCancelable(false);
//            builder.setTitle("Exit");
//            builder.setMessage("Are you sure you want to leave");
//            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            });
//            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which)
//                {
//                    dialog.dismiss();
//                }
//            });
//            builder.create().show();
//        }
    }
