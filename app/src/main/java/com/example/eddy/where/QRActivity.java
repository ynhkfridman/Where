package com.example.eddy.where;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.eddy.where.IntentIntegrator;
import com.example.eddy.where.IntentResult;

public class QRActivity extends AppCompatActivity {

    TextView mContentsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        mContentsTextView = (TextView)findViewById(R.id.textView_content);
        startScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            if (scanResult.getContents() != null) {
                Log.d("tag", scanResult.getContents());
                mContentsTextView.setText(scanResult.getContents());
            }
        }

    }

    public void scanButtonPressed(View view) {
        startScan();
    }

    public void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }
}
