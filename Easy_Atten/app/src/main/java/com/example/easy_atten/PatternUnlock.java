package com.example.easy_atten;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class PatternUnlock extends AppCompatActivity implements PatternLockViewListener {

    PatternLockView patternLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_unlock);

        patternLockView = (PatternLockView)findViewById(R.id.pattern_lock_view);

        patternLockView.addPatternLockListener(this);
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {

    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        //6304258
        if(PatternLockUtils.patternToString(patternLockView,pattern).equalsIgnoreCase("03678"))
        {
            patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
            Toast.makeText(this, "Pattern Correct", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PatternUnlock.this,mentor_btn_clicked.class));
            PatternUnlock.this.finish();

        }else
            {
                patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                Toast.makeText(this, "Pattern Incorrect", Toast.LENGTH_SHORT).show();
            }

    }

    @Override
    public void onCleared() {

    }
}
