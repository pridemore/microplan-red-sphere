package com.example.microplanredsphereandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        saveSignature=findViewById(R.id.saveSignature);
        cancelSignature=findViewById(R.id.cancelSignature);
        title.setText("Signature");

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