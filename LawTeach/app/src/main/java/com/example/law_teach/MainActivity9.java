package com.example.law_teach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button; // Importa la clase Button
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;


import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;


public class MainActivity9 extends AppCompatActivity {
    private TextInputLayout passwordTextInputLayout;
    private TextInputEditText passwordEditText;
    private TextInputLayout confirmPasswordTextInputLayout;
    private TextInputEditText confirmPasswordEditText;
    private TextInputLayout emailTextInputLayout; // Agregado
    private TextInputEditText emailEditText; // Agregado
    private TextInputLayout phoneTextInputLayout; // Agregado
    private TextInputEditText phoneEditText; // Agregado
    private TextInputEditText nombreEditText; // Agregado
    private TextInputEditText apellidoPaternoEditText; // Agregado
    private TextInputEditText apellidoMaternoEditText; // Agregado

    private TextInputEditText DireccionEditText;

    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        // Mostrar el mensaje emergente con las opciones "Usuario" y "Abogado"
        showRoleSelectionDialog();

        ImageView backButton = findViewById(R.id.imageView10);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para regresar a la actividad MainActivity2
                Intent intent = new Intent(MainActivity9.this, MainActivity2.class);
                // Iniciar la actividad utilizando el Intent creado
                startActivity(intent);
                // Finalizar la actividad actual (MainActivity9)
                finish();
            }
        });

        // Referencias a los elementos de entrada de texto
        passwordTextInputLayout = findViewById(R.id.commentTextInputLayout9);
        passwordEditText = findViewById(R.id.textInputEditText9);
        confirmPasswordTextInputLayout = findViewById(R.id.commentTextInputLayout10);
        confirmPasswordEditText = findViewById(R.id.textInputEditText11);
        emailTextInputLayout = findViewById(R.id.commentTextInputLayout8); // Agregado
        emailEditText = findViewById(R.id.textInputEditText8); // Agregado
        phoneTextInputLayout = findViewById(R.id.commentTextInputLayout7); // Agregado
        phoneEditText = findViewById(R.id.textInputEditText7); // Agregado


        //nombreEditText = findViewById(R.id.textInputEditText9); // Agregado
       // apellidoPaternoEditText = findViewById(R.id.textInputEditText2); // Agregado
       // apellidoMaternoEditText = findViewById(R.id.commentTextInputLayout7); // Agregado
       // DireccionEditText = findViewById(R.id.textInputEditText10); // Agregado



        // Agregar un TextWatcher para la validación de la contraseña en tiempo real
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validarContraseña(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Agregar un TextWatcher para la confirmación de la contraseña en tiempo real
        confirmPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validarConfirmacionContraseña(passwordEditText.getText().toString(), s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Agregar un TextWatcher para la validación del correo electrónico en tiempo real
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validarEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Agregar un TextWatcher para la validación del número de teléfono en tiempo real
        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validarTelefono(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Obtener referencia al botón btcuatro
        Button btcuatroButton = findViewById(R.id.btcuatro1);
        // Agregar OnClickListener al botón btcuatro
        btcuatroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validar que los campos estén llenos antes de enviar los datos
                if (validarCampos()) {
                    // Llamar al método para enviar los datos a la API
                    enviarDatosAPI();
                } else {
                    // Mostrar un mensaje de error si los campos no están llenos
                    Toast.makeText(MainActivity9.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para mostrar el mensaje emergente de selección de rol
    private void showRoleSelectionDialog() {
        // Crear un diálogo de alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Con qué rol deseas registrarte?");
        // Establecer las opciones del diálogo
        builder.setItems(new CharSequence[]{"Usuario", "Abogado"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Redireccionar a la actividad correspondiente según la opción seleccionada
                switch (which) {
                    case 1:
                        // Opción "Abogado" seleccionada
                        startActivity(new Intent(MainActivity9.this, MainActivity10.class));
                        // Finalizar la actividad actual (MainActivity9)
                        finish();
                        break;
                }
            }
        });
        // Mostrar el diálogo
        builder.show();
    }

    // Método para validar que todos los campos estén llenos
    private boolean validarCampos() {
        return !passwordEditText.getText().toString().isEmpty() &&
                !confirmPasswordEditText.getText().toString().isEmpty() &&
                !emailEditText.getText().toString().isEmpty() &&
                !phoneEditText.getText().toString().isEmpty();
    }

    // Método para validar la contraseña
    private void validarContraseña(String contraseña) {
        boolean contieneMayusculas = !contraseña.equals(contraseña.toLowerCase());
        boolean contieneMinusculas = !contraseña.equals(contraseña.toUpperCase());
        boolean contieneNumeros = contraseña.matches(".*\\d.*");

        // Cambiar el color del texto según los criterios
        if (contieneMayusculas && contieneMinusculas && contieneNumeros) {
            passwordTextInputLayout.setError(null);
        } else {
            passwordTextInputLayout.setError("Usa mayúsculas, minúsculas y números");
            passwordTextInputLayout.setErrorTextColor(getResources().getColorStateList(android.R.color.holo_red_dark)); // Cambiado a color rojo oscuro
        }
    }

    // Método para validar la confirmación de la contraseña
    private void validarConfirmacionContraseña(String contraseña, String confirmacion) {
        if (!contraseña.equals(confirmacion)) {
            confirmPasswordTextInputLayout.setError("Las contraseñas no coinciden");
        } else {
            confirmPasswordTextInputLayout.setError(null);
        }
    }

    // Método para validar el formato del correo electrónico
    private void validarEmail(String email) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextInputLayout.setError(null);
        } else {
            emailTextInputLayout.setError("Inserta un correo válido");
        }
    }

    // Método para validar que solo se ingresen números en el número de teléfono
    private void validarTelefono(String telefono) {
        if (telefono.matches("[0-9]+")) {
            phoneTextInputLayout.setError(null);
        } else {
            phoneTextInputLayout.setError("Solo se pueden ingresar números");
        }
    }

    // Método para enviar los datos a la API
    private void enviarDatosAPI() {
        // Definir el rol como 2
        final String rol = "2";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idRol", rol);
            jsonObject.put("ApellidoPaterno", this.apellidoPaternoEditText.getText().toString());
            jsonObject.put("ApellidoMaterno", this.apellidoMaternoEditText.getText().toString());//materno puede meterse null
            jsonObject.put("Nombre", nombreEditText.getText().toString());
            jsonObject.put("Telefono", phoneEditText.getText().toString());
            jsonObject.put("Correo", emailEditText.getText().toString());
            jsonObject.put("Contrasena", passwordEditText.getText().toString());
            jsonObject.put("Direccion", DireccionEditText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://a21a-2806-105e-15-79cb-a00-27ff-fe1f-41e9.ngrok-free.app/law_tech/ciudadano";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity9.this, "Datos enviados correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "Error desconocido";

                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                String errorData = new String(error.networkResponse.data, "UTF-8");
                                errorMessage = errorData;
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }

                        Toast.makeText(MainActivity9.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
        }


    }




