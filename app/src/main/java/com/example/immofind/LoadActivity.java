package com.example.immofind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class LoadActivity extends AppCompatActivity {

    TextView name;
    private LottieAnimationView lott;
    private static int TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);


        name = findViewById(R.id.tv_logo);
        lott = findViewById(R.id.lottie);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent home = new Intent(LoadActivity.this,MainActivity.class);
                startActivity(home);
                finish();
            }
        },TIME_OUT);
    }
}