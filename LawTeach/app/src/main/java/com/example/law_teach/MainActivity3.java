package com.example.law_teach;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    Button found, perfil, historial, Bcita, Salirr, foundlike, enviar;
    boolean buttonsVisible = false;
    boolean foundLikeClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        found = (Button) findViewById(R.id.found);
        perfil = (Button) findViewById(R.id.perfil);
        historial = (Button) findViewById(R.id.historial);
        Bcita = (Button) findViewById(R.id.Bcita);
        Salirr = (Button) findViewById(R.id.Salirr);
        foundlike = findViewById(R.id.foundlike);
        enviar = findViewById(R.id.bt7);


        findViewById(R.id.bt6).setOnClickListener(this);
        perfil.setOnClickListener(this);
        Salirr.setOnClickListener(this);
        historial.setOnClickListener(this);
        Bcita.setOnClickListener(this);
        foundlike.setOnClickListener(this);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foundLikeClicked) {
                    showCalificationSentDialog();
                } else {
                    showCalificationNotSentDialog();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bt6) {
            if (buttonsVisible) {
                found.setVisibility(View.GONE);
                perfil.setVisibility(View.GONE);
                historial.setVisibility(View.GONE);
                Bcita.setVisibility(View.GONE);
                Salirr.setVisibility(View.GONE);
                buttonsVisible = false;
            } else {
                found.setVisibility(View.VISIBLE);
                perfil.setVisibility(View.VISIBLE);
                historial.setVisibility(View.VISIBLE);
                Bcita.setVisibility(View.VISIBLE);
                Salirr.setVisibility(View.VISIBLE);
                buttonsVisible = true;
            }
        } else if (v.getId() == R.id.perfil) {
            // Hacer visible el botón perfil antes de iniciar la actividad MainActivity4
            perfil.setVisibility(View.VISIBLE);
            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
            startActivity(intent);
        } else if (v.getId() == R.id.Salirr) {
            showExitDialog(); // Llama al método para mostrar el diálogo de confirmación
        } else if (v.getId() == R.id.historial) {
            historial.setVisibility(View.VISIBLE);
            Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
            startActivity(intent);
        } else if (v.getId() == R.id.Bcita) {
            Bcita.setVisibility(View.VISIBLE);
            Intent intent = new Intent(MainActivity3.this, MainActivity6.class);
            startActivity(intent);
        } else if (v.getId() == R.id.foundlike) {
            if (!foundLikeClicked) {
                // Cambiar el color del botón a azul oscuro
                foundlike.getBackground().setColorFilter(getResources().getColor(R.color.colorAzulOscuro), PorterDuff.Mode.SRC);
                foundLikeClicked = true;
            } else {
                // Restaurar el color original del botón
                foundlike.getBackground().setColorFilter(getResources().getColor(R.color.colorFoundLikeOriginal), PorterDuff.Mode.SRC);
                foundLikeClicked = false;
            }
        }
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Salir");
        builder.setMessage("¿Estás seguro de que quieres salir?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }


    private void showCalificationNotSentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Calificación no registrada");
        builder.setMessage("No se ha registrado tu calificación.");
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private void showCalificationSentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Calificación Enviada Exitosamente");
        builder.setMessage("¡Gracias por tu calificación!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Restaurar el color original del botón foundlike y deshabilitarlo...
                foundlike.getBackground().setColorFilter(getResources().getColor(R.color.colorFoundLikeOriginal), PorterDuff.Mode.SRC);
                foundlike.setEnabled(false);
            }
        });
        builder.show();
    }
}