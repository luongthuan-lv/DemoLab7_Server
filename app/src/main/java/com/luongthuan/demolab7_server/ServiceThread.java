package com.luongthuan.demolab7_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServiceThread extends Thread {

    private int clientNumber;
    private Socket socketServer;

    public ServiceThread(Socket socket, int clientNumber){
        this.socketServer = socket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        super.run();

        try{
            BufferedReader is = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketServer.getOutputStream()));

            while (true){

                String line = is.readLine();

                os.write(">>" + line);

                 os.newLine();

                os.flush();

            }
        }catch (Exception e){

        }
    }
}
