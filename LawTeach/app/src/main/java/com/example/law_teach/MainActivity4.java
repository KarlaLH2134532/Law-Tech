package com.example.law_teach;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity4 extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_IMAGE_REQUEST_REMPLAZO = 2;
    private static final String IMAGE_FILE_NAME = "selected_image.jpg";
    private ImageButton imx;
    private ImageButton imageButton2, imageButton3;
    private Button btt1;
    private Button btt2;
    private ImageView remplazo;
    private Uri selectedImageUri; // URI to store the selected image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        imx = findViewById(R.id.imx);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        ImageButton backButton = findViewById(R.id.imageButton4);
        btt1 = findViewById(R.id.btt1);
        btt2 = findViewById(R.id.btt2);
        remplazo = findViewById(R.id.remplazo); // Agregado

        loadSavedImage(); // Load the saved image when MainActivity4 is created

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(PICK_IMAGE_REQUEST);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(PICK_IMAGE_REQUEST_REMPLAZO);
            }
        });

        btt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity7.class);
                intent.putExtra("imageUri", selectedImageUri.toString()); // Pass the URI to the next activity
                startActivity(intent);
            }
        });

        btt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity8.class);
                intent.putExtra("imageUri", selectedImageUri.toString()); // Pass the URI to the next activity
                startActivity(intent);
            }
        });

        imx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle image click event if needed
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    // Method to open gallery and select image
    private void openGallery(int requestCode) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, requestCode);
    }

    // Method to load the saved image from internal storage
    private void loadSavedImage() {
        File file = new File(getFilesDir(), IMAGE_FILE_NAME);
        if (file.exists()) {
            selectedImageUri = Uri.fromFile(file); // Get the URI of the saved image
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imx.setImageBitmap(bitmap);
        }
    }

    // Method to save the selected image to internal storage
    private void saveImageToInternalStorage(Uri selectedImageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                File file = new File(getFilesDir(), IMAGE_FILE_NAME);
                FileOutputStream outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData(); // Store the selected image URI
            saveImageToInternalStorage(imageUri); // Save the selected image

            if (requestCode == PICK_IMAGE_REQUEST) {
                selectedImageUri = imageUri;
                loadSavedImage(); // Load the saved image to display in imx
            } else if (requestCode == PICK_IMAGE_REQUEST_REMPLAZO) {
                selectedImageUri = imageUri;
                remplazo.setImageURI(selectedImageUri); // Set the selected image to remplazo ImageView

                // Ajustar la escala de la imagen para adaptarse al tamaño de ImageView remplazo
                remplazo.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
    }
}
