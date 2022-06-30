package com.example.celiaquia;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class logIn {


    int codigo;

    public logIn() {
        this.codigo = 0;
    }

    public void logear(HttpsURLConnection conexion, JSONObject salida) {
        Runnable hilo = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                OutputStream os = null;

                try {
                    os = conexion.getOutputStream();
                    os.write(salida.toString().getBytes("UTF-8"));
                    os.close();
                    // read the response
                    setCodigo(conexion.getResponseCode());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread run = new Thread(hilo);
        run.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        run.interrupt();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
