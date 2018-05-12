package com.io.revnosdeng.myim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Client：Connecting");
                            Socket socket = new Socket("172.18.158.241", 12345);
                            String message = textView.getText().toString();
                            try {
                                System.out.println("Client Sending: '" + message + "'");
                                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                                out.println(message);
                                out.flush();
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                socket.close();
                                System.out.println("Client : Socket closed");
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
}
