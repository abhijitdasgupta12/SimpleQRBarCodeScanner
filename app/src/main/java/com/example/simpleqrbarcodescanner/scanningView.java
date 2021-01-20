package com.example.simpleqrbarcodescanner;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scanningView extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);

        //Here we have replaced the value "R.layout.activity_q_r_scanner_view" with the "scannerView". This will load the scanner in the place of the xml layout of current activity.
        setContentView(scannerView);

        //Permission manage with Dexter
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse)
                    {
                        scannerView.startCamera(); //Starts camera to scan
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        index.ScanResultTextView.setText("Error: Access Permission Denied!");
                        onBackPressed(); //Returns automatically to last activity (here, index.java)
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    @Override
    public void handleResult(Result rawResult)
    {
        index.ScanResultTextView.setText(rawResult.getText()); //Gets the scan result and sets that inside the textview of "index" class
        onBackPressed(); //Returns to last opened activity automatically
    }

    //Essential pre-defined method 1: To stop camera when scanning is completed
    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    //Essential pre-defined method 2: To start camera when scanning starts
    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}