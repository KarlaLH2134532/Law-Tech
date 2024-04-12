package com.example.law_teach;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button bttres = findViewById(R.id.bttres);
        Button btregresar = findViewById(R.id.btregresar);
        Button btcinco = findViewById(R.id.btcuatro); // Cambiado de btcuatro a btcinco

        // Agregar OnClickListener al botón bttres
        bttres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        // Agregar OnClickListener al botón btregresar
        btregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Agregar OnClickListener al botón btcinco
        btcinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity9.class);
                startActivity(intent);
            }
        });

        // Verificar el tipo de usuario seleccionado
        Intent intent = getIntent();
        String userType = intent.getStringExtra("userType");

        if (userType != null) {
            if (userType.equals("Usuario")) {
                Intent userIntent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(userIntent);
                finish();
            } else if (userType.equals("Abogado")) {
                Intent lawyerIntent = new Intent(MainActivity2.this, MainActivity11.class);
                startActivity(lawyerIntent);
                finish();
            }
        }
    }
}
