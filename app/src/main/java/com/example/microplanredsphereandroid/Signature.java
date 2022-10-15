package com.example.microplanredsphereandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.microplanredsphereandroid.utils.Utils;
import com.github.gcacace.signaturepad.views.SignaturePad;

public class Signature extends AppCompatActivity {
    private SignaturePad signaturePad;
    Button saveSignature,cancelSignature;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        title=findViewById(R.id.title);
        signaturePad = findViewById(R.id.signature_pad);
        saveSignature=findViewById(R.id.saveSignature);
        cancelSignature=findViewById(R.id.cancelSignature);

        //setting static text
        title.setText("Signature");

        //Buttons logic
        saveSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.saveLatestSignature(getApplicationContext(), signaturePad.getSignatureBitmap());
                setResult(RESULT_OK);
                finish();
            }
        });
        cancelSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Signature.this, "sdsfsf", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}