package com.example.easy_atten;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;

public class student_btn_clicked extends AppCompatActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    private ImageView imageView_stu;

  private EditText etEmailstu, etPasswordstu;
    Button btnLoginstu, btnSignupstu, aboutStudent;
    TextView tvForgetPasStu;
    CheckBox remember_student;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_btn_clicked);

       imageView_stu = findViewById(R.id.ivStudent);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        etEmailstu = findViewById(R.id.etEmailStudent);
        etPasswordstu = findViewById(R.id.etPasswordStudent);
        btnLoginstu = findViewById(R.id.btnLoginStudent);
//        aboutStudent = findViewById(R.id.std_about);
//        remember_student =findViewById(R.id.checkBox_rem_stu);
//        btnSignupstu = findViewById(R.id.btnSignupStudent);
        tvForgetPasStu = findViewById(R.id.tvReset_Student);
        pd=new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

//        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
//        String checkbox = preferences.getString("remember","");
//        if(checkbox.equals("true")){
//            Intent intent=new Intent(student_btn_clicked.this,StudentInformationActivity.class);
//            startActivity(intent);
//        }else if(checkbox.equals("false")){
//            Toast.makeText(this, "Please Sign In...", Toast.LENGTH_SHORT).show();
//        }

        tvForgetPasStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intSignUp =new Intent(student_btn_clicked.this,ForgetPassword_Student.class);
                startActivity(intSignUp);
            }
        });

        //imageview
        imageView_stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(student_btn_clicked.this, "Hello Student Welcome to EasyAtten!", Toast.LENGTH_SHORT).show();
                Animation animation = AnimationUtils.loadAnimation(student_btn_clicked.this,R.anim.bounce);
                imageView_stu.startAnimation(animation);
            }
        });
//        aboutStudent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(student_btn_clicked.this,CardviewStudent.class));
//            }
//        });
//********************
//        remember_student.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(buttonView.isChecked()){
//                    SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor =preferences.edit();
//                    editor.putString("remember","true" );
//                    editor.apply();
//                    Toast.makeText(student_btn_clicked.this, "Checked", Toast.LENGTH_SHORT).show();
//
//                }else if(!buttonView.isChecked()){
//                    SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor =preferences.edit();
//                    editor.putString("remember","false" );
//                    editor.apply();
//                    Toast.makeText(student_btn_clicked.this, "Unchecked", Toast.LENGTH_SHORT).show();
//
//
//                }
//            }
//        });

    }

    public void StudentLoginClick(View view) {
        final String email=etEmailstu.getText().toString().trim();
        String password=etPasswordstu.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(student_btn_clicked.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return ;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(student_btn_clicked.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return ;
        }
        SimpleArcDialog mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(new ArcConfiguration(this));
        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.COMPLETE_ARC);
        configuration.setText("Please wait...");
        mDialog.setConfiguration(configuration);
        mDialog.show();
//        pd.setMessage("Siging In");
//        pd.setCancelable(false);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pd.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "SignIn Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(student_btn_clicked.this,StudentInformationActivity.class);
                    intent.putExtra("Name","Email");
                    intent.putExtra("Email",email);
                    firebaseAuth.signOut();
                    startActivity(intent);
                    student_btn_clicked.this.finish();
                    pd.dismiss();
                }
                else{
                    Toast.makeText(student_btn_clicked.this, "Oops!! Wrong credentials", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }

}

//*******************************************************************************************************************************************************************
//        showProgress(true);
//
//        btnLoginstu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(etEmailstu.getText().toString().isEmpty() || etPasswordstu.getText().toString().isEmpty())
//                {
//                    Toast.makeText(student_btn_clicked.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    String email = etEmailstu.getText().toString().trim();
//                    String pass = etPasswordstu.getText().toString().trim();
//
//                    showProgress(true);
//                    tvLoad.setText("Busy logging you in...please...wait...");
//
//                    Backendless.UserService.login(email, pass, new AsyncCallback<BackendlessUser>() {
//                        @Override
//                        public void handleResponse(BackendlessUser response) {
//
//                            Toast.makeText(student_btn_clicked.this, "Student Logged in successfully!!!", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(student_btn_clicked.this,CardviewStudent.class));
//                            student_btn_clicked.this.finish();
//
//                        }
//
//                        @Override
//                        public void handleFault(BackendlessFault fault) {
//
//                            Toast.makeText(student_btn_clicked.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
//                            showProgress(false);
//
//                        }
//                    }, true);
//                }
//
//
//            }
//        });
//
//        btnSignupstu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(student_btn_clicked.this,student_signup.class));
//                student_btn_clicked.this.finish();
//
//            }
//        });
//        tvForgetPasStu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(etEmailstu.getText().toString().isEmpty())
//                {
//                    Toast.makeText(student_btn_clicked.this, "Please enter your email address in the email field!", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    String email = etEmailstu.getText().toString().trim();
//                    showProgress(true);
//                    tvLoad.setText("Busy sending reset instruction...please...wait...");
//
//                    Backendless.UserService.restorePassword(email, new AsyncCallback<Void>() {
//                        @Override
//                        public void handleResponse(Void response) {
//
//                            Toast.makeText(student_btn_clicked.this, "Check Your Email TO RESET Password!", Toast.LENGTH_SHORT).show();
//                            showProgress(false);
//
//                        }
//
//                        @Override
//                        public void handleFault(BackendlessFault fault) {
//
//                            Toast.makeText(student_btn_clicked.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
//                            showProgress(false);
//
//                        }
//                    });
//                }
//
//            }
//        });
//
//        tvLoad.setText("Checking login Credentials...please...wait...");
//        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
//            @Override
//            public void handleResponse(Boolean response) {
//
//                if(response)
//                {
//
//                    String userObjectId = UserIdStorageFactory.instance().getStorage().get();
//                    tvLoad.setText("Logging You in...please wait...");
//
//                    Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
//                        @Override
//                        public void handleResponse(BackendlessUser response) {
//
//                            startActivity(new Intent(student_btn_clicked.this,CardviewStudent.class));
//                            student_btn_clicked.this.finish();
//
//                        }
//
//                        @Override
//                        public void handleFault(BackendlessFault fault) {
//
//                            Toast.makeText(student_btn_clicked.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
//                            showProgress(false);
//
//                        }
//                    });
//
//                }
//                else
//                {
//                    showProgress(false);
//                }
//
//            }
//
//            @Override
//            public void handleFault(BackendlessFault fault) {
//
//                Toast.makeText(student_btn_clicked.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
//                showProgress(false);
//
//            }
//        });
//
//
//
//
//    }
//    /**
//     * Shows the progress UI and hides the login form.
//     */
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//    private void showProgress(final boolean show) {
//        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
//        // for very easy animations. If available, use these APIs to fade-in
//        // the progress spinner.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
//
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });
//
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mProgressView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//                }
//            });
//
//            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
//            tvLoad.animate().setDuration(shortAnimTime).alpha(
//                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
//                }
//            });
//        } else {
//            // The ViewPropertyAnimator APIs are not available, so simply show
//            // and hide the relevant UI components.
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
//            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//        }
//    }
//
//
//}
//
//
//
//
//
//
