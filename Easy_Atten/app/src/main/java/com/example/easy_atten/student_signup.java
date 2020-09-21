package com.example.easy_atten;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class student_signup extends AppCompatActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    EditText etUsername, etEmail, etPass, etCPass;
    Button btnStudentSignup;
    TextView tvAlreadyStudent;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        etUsername = findViewById(R.id.etUsername_sSignup);
        etEmail = findViewById(R.id.etEmail_sSignup);
        etPass = findViewById(R.id.etPassword_sSignup);
        etCPass = findViewById(R.id.etCPassword_sSignup);
        btnStudentSignup = findViewById(R.id.btnSignup_sSignup);
        tvAlreadyStudent = findViewById(R.id.tvAlready_sSignup);

        tvAlreadyStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student_signup.this, student_btn_clicked.class));
                student_signup.this.finish();
            }
        });

        btnStudentSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                String uname = etUsername.getText().toString().trim();
                String cpass = etCPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)|| TextUtils.isEmpty(pass) || TextUtils.isEmpty(uname) || TextUtils.isEmpty(cpass) ) {
                    Toast.makeText(student_signup.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                } else {
                    if (etPass.getText().toString().trim().equals(etCPass.getText().toString().trim())) {

                        BackendlessUser user = new BackendlessUser();
                        user.setEmail(email);
                        user.setPassword(pass);
                        user.setProperty("username", uname);


                        showProgress(true);
                        tvLoad.setText("Busy registering student...please...wait...");

                        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {

                                Toast.makeText(student_signup.this, "STUDENT successfully signup!", Toast.LENGTH_SHORT).show();
                                student_signup.this.finish();
                                startActivity(new Intent(student_signup.this,student_btn_clicked.class));
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(student_signup.this, "Error" + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                showProgress(false);

                            }
                        });
                        STUDENT student = new STUDENT();
                        student.setEmail(email);
                        student.setPassword(pass);
                        student.setUsername(uname);


                        Backendless.Data.of(STUDENT.class).save(student, new AsyncCallback<STUDENT>() {
                            @Override
                            public void handleResponse(STUDENT response) {

//                                Intent intent = new Intent(student_signup.this,studentProfile.class);
//                                startActivity(intent);

//                                rollno = etRollno_stu.getText().toString()


                                Toast.makeText(student_signup.this, "You signedup successfully!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                                Toast.makeText(student_signup.this, "Error:"+fault.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                    } else {
                        Toast.makeText(student_signup.this, "Please make sure that password and confirm password is the same!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }



    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}


