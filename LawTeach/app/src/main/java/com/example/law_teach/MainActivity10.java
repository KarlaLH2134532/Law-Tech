package com.example.law_teach;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity10 extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout passwordTextInputLayout;
    private TextInputEditText passwordEditText;
    Button bs,bs1,bs2,bs3,bs4,bs5,bs6,bs7,bs8,bs9;
    ImageButton lll;
    boolean buttonsVisible = false;
    private TextInputLayout confirmPasswordTextInputLayout;
    private TextInputEditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        bs = (Button) findViewById(R.id.bs);
        bs1 = (Button) findViewById(R.id.bs1);
        bs2 = (Button) findViewById(R.id.bs2);
        bs3 = (Button) findViewById(R.id.bs3);
        bs4 = (Button) findViewById(R.id.bs4);
        bs5 = (Button) findViewById(R.id.bs5);
        bs6 = (Button) findViewById(R.id.bs6);
        bs7 = (Button) findViewById(R.id.bs7);
        bs8 = (Button) findViewById(R.id.bs8);
        bs9 = (Button) findViewById(R.id.bs9);
        lll = (ImageButton) findViewById(R.id.lll);

        findViewById(R.id.desplegar).setOnClickListener(this);
        bs.setOnClickListener(this);
        bs1.setOnClickListener(this);
        bs2.setOnClickListener(this);
        bs3.setOnClickListener(this);
        bs4.setOnClickListener(this);
        bs5.setOnClickListener(this);
        bs6.setOnClickListener(this);
        bs7.setOnClickListener(this);
        bs8.setOnClickListener(this);
        bs9.setOnClickListener(this);

        // Referencias a los elementos de entrada de texto
        passwordTextInputLayout = findViewById(R.id.commentTextInputLayout9);
        passwordEditText = findViewById(R.id.textInputEditText9);
        confirmPasswordTextInputLayout = findViewById(R.id.commentTextInputLayout10);
        confirmPasswordEditText = findViewById(R.id.textInputEditText11);

        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para regresar a la actividad anterior (MainActivity2)
                Intent intent = new Intent(MainActivity10.this, MainActivity2.class);
                // Iniciar la actividad utilizando el Intent creado
                startActivity(intent);
                // Finalizar la actividad actual (MainActivity10)
                finish();
            }
        });

        // Obtener referencia al botón btcuatro
        Button btcuatroButton = findViewById(R.id.btcuatro);
        // Agregar OnClickListener al botón btcuatro
        btcuatroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a MainActivity2
                Intent intent = new Intent(MainActivity10.this, MainActivity2.class);
                // Iniciar la actividad MainActivity2
                startActivity(intent);
            }
        });

        // Agregar un TextWatcher al TextInputEditText de la contraseña para la validación en tiempo real
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validarContraseña();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Agregar un TextWatcher al TextInputEditText de la confirmación de contraseña para la validación en tiempo real
        confirmPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validarContraseña();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.desplegar) {
            if (buttonsVisible) {
                bs.setVisibility(View.GONE);
                bs1.setVisibility(View.GONE);
                bs2.setVisibility(View.GONE);
                bs3.setVisibility(View.GONE);
                bs4.setVisibility(View.GONE);
                bs5.setVisibility(View.GONE);
                bs6.setVisibility(View.GONE);
                bs7.setVisibility(View.GONE);
                bs8.setVisibility(View.GONE);
                bs9.setVisibility(View.GONE);
                lll.setVisibility(View.GONE);
                buttonsVisible = false;
            } else {
                bs.setVisibility(View.VISIBLE);
                bs1.setVisibility(View.VISIBLE);
                bs2.setVisibility(View.VISIBLE);
                bs3.setVisibility(View.VISIBLE);
                bs4.setVisibility(View.VISIBLE);
                bs5.setVisibility(View.VISIBLE);
                bs6.setVisibility(View.VISIBLE);
                bs7.setVisibility(View.VISIBLE);
                bs8.setVisibility(View.VISIBLE);
                bs9.setVisibility(View.VISIBLE);
                lll.setVisibility(View.VISIBLE);
                buttonsVisible = true;
            }
        }
    }

    // Método para validar la contraseña y su confirmación
    private void validarContraseña() {
        String contraseña = passwordEditText.getText().toString();
        String confirmarContraseña = confirmPasswordEditText.getText().toString();

        if (!contraseña.equals(confirmarContraseña)) {
            confirmPasswordTextInputLayout.setError("Las contraseñas no coinciden");
        } else {
            confirmPasswordTextInputLayout.setError(null);
        }

        // Verificar si la contraseña cumple con los criterios (mayúsculas, minúsculas y números)
        boolean contieneMayusculas = !contraseña.equals(contraseña.toLowerCase());
        boolean contieneMinusculas = !contraseña.equals(contraseña.toUpperCase());
        boolean contieneNumeros = contraseña.matches(".*\\d.*");

        // Mostrar mensaje de recomendación para la contraseña
        if (!(contieneMayusculas && contieneMinusculas && contieneNumeros)) {
            passwordTextInputLayout.setError("Usa mayúsculas, minúsculas y números");
        } else {
            passwordTextInputLayout.setError(null);
        }
    }
}


