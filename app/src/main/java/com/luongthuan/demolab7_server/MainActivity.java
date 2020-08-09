package com.luongthuan.demolab7_server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = findViewById(R.id.tvContent);
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                ServerSocket listener = null;
                Log.e("A", "Waiting...");
                int clientNumber = 0;

                try {
                    listener = new ServerSocket(9999);
                } catch (IOException e) {
                    Log.e("Loi", e.getMessage());
                }

                try {
                    while (true) {
                        Socket socket = listener.accept();
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            while (true) {
                                String line = reader.readLine();
                                writer.write("HaHa: " + line);
                                writer.newLine();
                                writer.flush();
                                tvContent.setText(line);
                                Log.e("LINE", line);
                            }
                        } catch (Exception e) {
                            Log.e("aaa", e.getMessage());
                        }
                        new ServiceThread(socket, clientNumber++).start();
                    }
                } catch (IOException e) {
                    Log.e("Loi 2", e.getMessage());
                } finally {
                    try {
                        listener.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        asyncTask.execute();
    }
}