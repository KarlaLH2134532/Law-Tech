package com.example.law_teach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity6 extends AppCompatActivity {
    Button pt1, bs3, bs2, bs1, bs;
    boolean buttonsVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        pt1 = findViewById(R.id.pt1);
        bs1 = findViewById(R.id.bs1);
        bs2 = findViewById(R.id.bs2);
        bs3 = findViewById(R.id.bs3);
        bs = findViewById(R.id.bs);

        ImageButton backButton = findViewById(R.id.imageButton6);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity6.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonsVisibility();
            }
        });

        // Inicialmente, ocultar los botones y pt1
        toggleButtonsVisibility();
    }

    private void toggleButtonsVisibility() {
        if (buttonsVisible) {
            buttonsVisible = false;
            // Ocultar los botones y pt1
            bs1.setVisibility(View.GONE);
            bs2.setVisibility(View.GONE);
            bs3.setVisibility(View.GONE);
            bs.setVisibility(View.GONE);
            pt1.setVisibility(View.GONE);
        } else {
            buttonsVisible = true;
            // Mostrar los botones y pt1
            bs1.setVisibility(View.VISIBLE);
            bs2.setVisibility(View.VISIBLE);
            bs3.setVisibility(View.VISIBLE);
            bs.setVisibility(View.VISIBLE);
            pt1.setVisibility(View.VISIBLE);
        }
    }
}
