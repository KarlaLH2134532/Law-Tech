package com.example.law_teach;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;

public class MainActivity7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // Obtener la URI de la imagen del intento
        String imageUriString = getIntent().getStringExtra("imageUri");

        // Convertir la URI de String a URI
        Uri imageUri = Uri.parse(imageUriString);

        // Redimensionar la imagen a 300x300
        Bitmap bitmap = null;
        try {
            bitmap = decodeUri(imageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Cargar la imagen redimensionada en el ImageButton
        ImageButton imx = findViewById(R.id.imx);
        imx.setImageBitmap(bitmap);

        Button agregarrr = findViewById(R.id.agregarrr);
        Button bbbb = findViewById(R.id.bbbb);
        Button bttss = findViewById(R.id.bttss);
        Button btt2s = findViewById(R.id.btt2s);
        ImageButton back = findViewById(R.id.back);

        // Recuperar el comentario guardado y mostrarlo si existe
        String comentarioGuardado = recuperarComentario();
        if (!comentarioGuardado.isEmpty()) {
            TextInputEditText commentInput = findViewById(R.id.textInputEditText);
            commentInput.setText(comentarioGuardado);
            commentInput.setEnabled(false); // Deshabilitar la edición por defecto
        }

        agregarrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText commentInput = findViewById(R.id.textInputEditText);
                commentInput.setEnabled(true); // Habilitar la edición al presionar Agregar
            }
        });

        bbbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar el texto actual en SharedPreferences
                TextInputEditText commentInput = findViewById(R.id.textInputEditText);
                String comment = commentInput.getText().toString().trim();
                guardarComentario(comment);
                // Deshabilitar la edición del texto después de guardar
                commentInput.setEnabled(false);
            }
        });

        bttss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7.this, MainActivity4.class);
                intent.putExtra("imageUri", imageUri.toString());
                startActivity(intent);
            }
        });

        btt2s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7.this, MainActivity8.class);
                intent.putExtra("imageUri", imageUri.toString());
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    // Método para guardar el comentario en SharedPreferences
    private void guardarComentario(String comentario) {
        SharedPreferences sharedPreferences = getSharedPreferences("comentarios", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("comentario_guardado", comentario);
        editor.apply();
    }

    // Método para recuperar el comentario de SharedPreferences
    private String recuperarComentario() {
        SharedPreferences sharedPreferences = getSharedPreferences("comentarios", MODE_PRIVATE);
        return sharedPreferences.getString("comentario_guardado", "");
    }

    // Método para redimensionar la imagen
    private Bitmap decodeUri(Uri uri) throws FileNotFoundException {
        try {
            // Decodificar la URI en un bitmap
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
            int imageWidth = options.outWidth;
            int imageHeight = options.outHeight;
            float scaleFactor = Math.min((float) 300 / imageWidth, (float) 300 / imageHeight);
            options.inJustDecodeBounds = false;
            options.inSampleSize = calculateInSampleSize(options, (int) (imageWidth * scaleFactor), (int) (imageHeight * scaleFactor));
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para calcular el tamaño de muestra para redimensionar la imagen
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 3;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 3;
            }
        }
        return inSampleSize;
    }
}
