package com.example.easy_atten;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;
import static com.backendless.Backendless.Data;

public class mentor_btn_clicked extends AppCompatActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    private ImageView imageView_ment;

    EditText etEmail,etPassword;
    Button btnLoginMentor,btnSignupMentor;
    TextView tvForgetPassMentor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_btn_clicked);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLoginMentor = findViewById(R.id.btnLogin);
        btnSignupMentor = findViewById(R.id.btnSignup);
        tvForgetPassMentor = findViewById(R.id.tvReset_Mentor);
        imageView_ment = findViewById(R.id.ivMentor);

        //imageView
        imageView_ment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mentor_btn_clicked.this, "Hello Mentor Welcome to EasyAtten!", Toast.LENGTH_SHORT).show();
                Animation animation = AnimationUtils.loadAnimation(mentor_btn_clicked.this,R.anim.bounce);
                imageView_ment.startAnimation(animation);
            }
        });

        showProgress(true);

        btnLoginMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(mentor_btn_clicked.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else 
                {
                    String email = etEmail.getText().toString().trim();
                    String pass = etPassword.getText().toString().trim();

                    showProgress(true);
                    tvLoad.setText("Busy logging you in...please...wait...");

                    Backendless.UserService.login(email, pass, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {

                            Toast.makeText(mentor_btn_clicked.this, "Mentor Logged in successfully!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mentor_btn_clicked.this,MentorAdminLogin.class));
                            mentor_btn_clicked.this.finish();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(mentor_btn_clicked.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);

                        }
                    }, true);
                }


            }
        });

        btnSignupMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mentor_btn_clicked.this,mentor_signup.class));
                mentor_btn_clicked.this.finish();

            }
        });
        tvForgetPassMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().toString().isEmpty())
                {
                    Toast.makeText(mentor_btn_clicked.this, "Please enter your email address in the email field!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String email = etEmail.getText().toString().trim();
                    showProgress(true);
                    tvLoad.setText("Busy sending reset instruction...please...wait...");

                    Backendless.UserService.restorePassword(email, new AsyncCallback<Void>() {
                        @Override
                        public void handleResponse(Void response) {



                            Toast.makeText(mentor_btn_clicked.this, "Check Your Email TO RESET Password!", Toast.LENGTH_SHORT).show();
                            showProgress(false);


                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {



                            Toast.makeText(mentor_btn_clicked.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);

                        }
                    });

                }

            }
        });

        tvLoad.setText("Checking login Credentials...please...wait...");
        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {

                if(response)
                {

                    String userObjectId = UserIdStorageFactory.instance().getStorage().get();
                    tvLoad.setText("Logging You in...please wait...");

                    Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {

                            startActivity(new Intent(mentor_btn_clicked.this,MentorAdminLogin.class));
                            mentor_btn_clicked.this.finish();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(mentor_btn_clicked.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);

                        }
                    });

                }
                else
                {
                    showProgress(false);
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(mentor_btn_clicked.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

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







