package com.example.microplanredsphereandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageCaptureActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 654;
    private Bitmap imageBitmap;
    private Button buttonCapture, buttonPickImage;
    private ImageView imageView;
    private final int REQUEST_CODE = 942;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CODE);
        }
        imageView = findViewById(R.id.imageView);
        buttonCapture = findViewById(R.id.buttonCapture);
        buttonPickImage = findViewById(R.id.buttonPickImage);
        buttonCapture.setOnClickListener(v->dispatchTakePictureIntent(false));
        findViewById(R.id.buttonOkay).setOnClickListener(v->{
            if (imageBitmap==null) {
                Toast.makeText(this, "Please capture image first", Toast.LENGTH_SHORT).show();
            } else {
                //Utils.saveLatestImage(getApplicationContext(), imageBitmap);
                Intent intent = new Intent();
                intent.putExtra("currentPhotoPath", currentPhotoPath);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        findViewById(R.id.buttonCancel).setOnClickListener(v->finish());
        buttonPickImage.setOnClickListener(v->{
            dispatchTakePictureIntent(true);
        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

/*    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No Camera App found", Toast.LENGTH_SHORT).show();
        }
    }*/

    File photoFile = null;
    private void dispatchTakePictureIntent(boolean isGallery) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "Error: Could not create image file", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                if (isGallery) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                } else {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.android.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
            /*Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);*/

                File imgFile = new File(currentPhotoPath);
                UCrop.Options options = new UCrop.Options();
                options.setFreeStyleCropEnabled(true);
                UCrop.of(Uri.fromFile(imgFile),
                                Uri.fromFile(new File(getCacheDir(), "SampleCropImage.jpg")))
                        .withMaxResultSize(5000, 8000)
                        .withOptions(options)
                        .start(this);
            } else if (requestCode== PICK_IMAGE) {
                Uri selectedImage =  data.getData();
                UCrop.Options options = new UCrop.Options();
                options.setFreeStyleCropEnabled(true);
                UCrop.of(selectedImage,
                                Uri.fromFile(new File(getCacheDir(), "SampleCropImage.jpg")))
                        .withMaxResultSize(5000, 8000)
                        .withOptions(options)
                        .start(this);
            } else if (requestCode == UCrop.REQUEST_CROP) {
                final Uri resultUri = UCrop.getOutput(data);
                try {
                    InputStream in = getContentResolver().openInputStream(resultUri);
                    OutputStream out = new FileOutputStream(photoFile);
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.close();
                    in.close();
                    imageBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                    imageView.setImageBitmap(imageBitmap);
                } catch (Exception e){
                    Toast.makeText(this, "An error occurred. Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission required", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}