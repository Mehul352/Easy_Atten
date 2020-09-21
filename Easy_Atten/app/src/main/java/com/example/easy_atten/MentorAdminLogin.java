package com.example.easy_atten;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MentorAdminLogin extends AppCompatActivity {

    private EditText userId,password;
    private Button btnLogin;
    private TextView counter_tv;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_admin_login);

        userId = findViewById(R.id.etUserID_admin);
        password = findViewById(R.id.etPass_admin);
        btnLogin = findViewById(R.id.btnLogin_admin);
        counter_tv = findViewById(R.id.counterTV);
    }

    public void mentorclicktologin(View view) {
        String user=userId.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(user.equals("admin123")&&pass.equals("admin123")){
            Intent intent=new Intent(MentorAdminLogin.this,CardviewUI_Mentor.class);
            startActivity(intent);
            MentorAdminLogin.this.finish();
        }
        else{
            counter--;
            counter_tv.setText("No. of attempts remaining: "+String.valueOf(counter));
            if (counter ==0){
                btnLogin.setEnabled(false);
            }
            Toast.makeText(MentorAdminLogin.this, "Wrong Input ID/Password!", Toast.LENGTH_SHORT).show();
        }
    }
}
