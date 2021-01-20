package com.example.simpleqrbarcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class index extends AppCompatActivity
{
    public static TextView ScanResultTextView;
    Button ScanButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //Typecast
        ScanResultTextView = findViewById(R.id.QR_Code_Display_TextView);
        ScanButton = findViewById(R.id.QR_Code_Scan_Button);

        //OnClick event
        ScanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), scanningView.class));
            }
        });
    }
}