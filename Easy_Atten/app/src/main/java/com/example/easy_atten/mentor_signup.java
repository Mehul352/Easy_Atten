package com.example.easy_atten;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

public class mentor_signup extends AppCompatActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    EditText etID, etEmail, etPass, etCPass;
    Button btnMentorSignup;
    TextView tvAlreadyMentor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_signup);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        etID = findViewById(R.id.etID_mSignup);
        etEmail = findViewById(R.id.etEmail_mSignup);
        etPass = findViewById(R.id.etPassword_mSignup);
        etCPass = findViewById(R.id.etCPassword_mSignup);
        btnMentorSignup = findViewById(R.id.btnSignup_mSignup);
        tvAlreadyMentor = findViewById(R.id.tvAlready_mSignup);

        tvAlreadyMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mentor_signup.this, mentor_btn_clicked.class));
                mentor_signup.this.finish();
            }
        });

        btnMentorSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etID.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() || etPass.getText().toString().isEmpty() || etCPass.getText().toString().isEmpty()) {
                    Toast.makeText(mentor_signup.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                } else {
                    if (etPass.getText().toString().trim().equals(etCPass.getText().toString().trim())) {
                        String id = etID.getText().toString().trim();
                        String email = etEmail.getText().toString().trim();
                        String password = etPass.getText().toString().trim();

                        BackendlessUser user = new BackendlessUser();
                        user.setEmail(email);
                        user.setPassword(password);
                        user.setProperty("Id", id);


                        showProgress(true);
                        tvLoad.setText("Busy registering mentor...please...wait...");

                        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {

                                Toast.makeText(mentor_signup.this, "MENTOR successfully signup!", Toast.LENGTH_SHORT).show();
                                mentor_signup.this.finish();
                                startActivity(new Intent(mentor_signup.this,mentor_btn_clicked.class));
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(mentor_signup.this, "Error" + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                showProgress(false);

                            }
                        });
                        MENTOR mentor = new MENTOR();
                        mentor.setEmail(email);
                        mentor.setPassword(password);
                        mentor.setId(id);


                    } else {
                        Toast.makeText(mentor_signup.this, "Please make sure that password and confirm password is the same!", Toast.LENGTH_SHORT).show();
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


