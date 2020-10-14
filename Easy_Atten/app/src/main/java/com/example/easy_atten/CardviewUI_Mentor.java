package com.example.easy_atten;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class CardviewUI_Mentor extends AppCompatActivity {

    CardView addNewStudent;
    CardView attendance_Mentor;
    CardView me_Mentor;
    CardView viewStudent_mentor;
    CardView feedback;
    CardView about_us;
    CardView calender;
    ImageView mentor_admin_profile;
    int TAKE_IMAGE_CODE = 10001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview_u_i__mentor);
        addNewStudent = findViewById(R.id.addnewStu_mentor);
        attendance_Mentor = findViewById(R.id.attendenceMentor);
        me_Mentor = findViewById(R.id.meMentor);
        viewStudent_mentor = findViewById(R.id.viewStu_mentor);
        feedback =findViewById(R.id.feed);
        about_us = findViewById(R.id.aboutUs);
        calender = findViewById(R.id.calender_mentor);
        mentor_admin_profile = findViewById(R.id.mentor_pic);



        me_Mentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    public void handleResponse(Void response) {
                        // user has been logged out.
                        Toast.makeText(CardviewUI_Mentor.this, "Mentor Logged Out Successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CardviewUI_Mentor.this,mentor_btn_clicked.class));
                        CardviewUI_Mentor.this.finish();
                    }

                    public void handleFault(BackendlessFault fault) {
                        // something went wrong and logout failed, to get the error code call fault.getCode()
                        Toast.makeText(CardviewUI_Mentor.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        //***********
        mentor_admin_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CardviewUI_Mentor.this, "Mentor Profile", Toast.LENGTH_SHORT).show();
            }
        });
        attendance_Mentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardviewUI_Mentor.this,SubjectList.class));
            }
        });
        addNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardviewUI_Mentor.this,AddStudentActivity.class));
                Toast.makeText(CardviewUI_Mentor.this, "Only Admin can access this page!", Toast.LENGTH_SHORT).show();
            }
        });
        viewStudent_mentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CardviewUI_Mentor.this,SelectRollNo.class));

            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardviewUI_Mentor.this,feedback.class));
                Toast.makeText(CardviewUI_Mentor.this, "Please Send your Feedback via email!", Toast.LENGTH_SHORT).show();

            }
        });
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CardviewUI_Mentor.this,AboutUs_Page.class));
            }
        });
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardviewUI_Mentor.this,calender_Activity.class));

            }
        });

    }

//    public void mentorProfilepic(View view) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(intent.resolveActivity(getPackageManager()) != null)
//        {
//            startActivityForResult(intent, TAKE_IMAGE_CODE );
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == TAKE_IMAGE_CODE)
//        {
//            switch (requestCode)
//            {
//                case RESULT_OK:
//                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//
//            }
//        }
//    }

    public void onBackPressed(){
        exitMentor();
    }
    private void exitMentor(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}

