package com.example.law_teach;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity8 extends AppCompatActivity {
    ImageButton regresar;
    View rootView;
    float scale = 1.0f;
    int zoomCount = 0;
    boolean zoomedIn = false;
    float initialX, initialY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        rootView = findViewById(android.R.id.content);

        // Obtener la URI de la imagen del intento
        String imageUriString = getIntent().getStringExtra("imageUri");

        // Convertir la URI de String a URI
        Uri imageUri = Uri.parse(imageUriString);

        // Redimensionar la imagen a 300x300
        Bitmap bitmap = decodeUri(imageUri);

        // Cargar la imagen redimensionada en el ImageButton
        ImageButton imx = findViewById(R.id.imx);
        imx.setImageBitmap(bitmap);

        regresar = findViewById(R.id.imageButton4);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a MainActivity4
                Intent intent = new Intent(MainActivity8.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        // Guardar las coordenadas iniciales
        initialX = rootView.getX();
        initialY = rootView.getY();

        findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hacer zoom
                if (zoomCount < 3) {
                    scale *= 1.2f;
                    rootView.setScaleX(scale);
                    rootView.setScaleY(scale);
                    zoomCount++;
                    zoomedIn = true;
                }
            }
        });

        findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dizoom
                if (zoomedIn && zoomCount > 0) {
                    scale /= 1.2f;
                    rootView.setScaleX(scale);
                    rootView.setScaleY(scale);
                    zoomCount--;
                    if (zoomCount == 0) {
                        zoomedIn = false;
                        // Restaurar las coordenadas iniciales
                        rootView.setX(initialX);
                        rootView.setY(initialY);
                    }
                }
            }
        });

        // Habilitar el movimiento táctil de la vista principal durante el zoom
        rootView.setOnTouchListener(new View.OnTouchListener() {
            float dX, dY;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (zoomedIn) { // Permitir el desplazamiento solo cuando se ha hecho zoom
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            dX = view.getX() - event.getRawX();
                            dY = view.getY() - event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            view.animate()
                                    .x(event.getRawX() + dX)
                                    .y(event.getRawY() + dY)
                                    .setDuration(0)
                                    .start();
                            break;
                        default:
                            return false;
                    }
                    return true;
                } else {
                    return false; // Deshabilitar el desplazamiento cuando el zoom está en su tamaño original
                }
            }
        });
    }

    // Método para redimensionar la imagen
    private Bitmap decodeUri(Uri uri) {
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
