package com.example.test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test.R;

public class Activity_Accueuille extends AppCompatActivity {
    private Button PlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueuille);

        PlayButton = findViewById(R.id.LogButton);

        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PlayActivity = new Intent(getApplicationContext(), QuizzActivity.class);
                startActivity(PlayActivity);
                finish();
            }
        });
    }
}