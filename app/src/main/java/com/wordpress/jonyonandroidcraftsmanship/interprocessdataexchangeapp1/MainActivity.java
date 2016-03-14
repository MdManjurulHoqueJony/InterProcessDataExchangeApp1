package com.wordpress.jonyonandroidcraftsmanship.interprocessdataexchangeapp1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText etMessage = null;
    private TextView tvStatus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        etMessage = (EditText) findViewById(R.id.etMessage);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
    }

    public void saveFile(View view) {
        File file = null;
        String text1 = etMessage.getText().toString();
        FileOutputStream fileOutputStream = null;

        try {
            file = getFilesDir();
            fileOutputStream = openFileOutput("jony.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(text1.getBytes());
            tvStatus.setTextColor(Color.GREEN);
            tvStatus.setText(text1 + "\n written to \n" + file.getAbsolutePath()+"/jony.txt");
        } catch (FileNotFoundException e) {
            tvStatus.setTextColor(Color.RED);
            tvStatus.setText(e.toString());
        } catch (IOException e) {
            tvStatus.setTextColor(Color.RED);
            tvStatus.setText(e.toString());
        }finally {
            if (fileOutputStream!=null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    tvStatus.setTextColor(Color.RED);
                    tvStatus.setText(e.toString());
                }
            }
        }
    }
}
