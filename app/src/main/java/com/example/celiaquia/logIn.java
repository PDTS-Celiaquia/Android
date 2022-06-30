package com.example.celiaquia;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class logIn{


    int codigo;

    public logIn() {
        this.codigo = 0;
    }

    public void logear(JSONObject salida) {
        Runnable hilo = () -> {
            OutputStream os = null;
            try {
                HttpsURLConnection conexion = new Conexion().conectar();
                os = conexion.getOutputStream();
                os.write(salida.toString().getBytes("UTF-8"));
                os.close();
                // read the response
                this.setCodigo(conexion.getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread run = new Thread(hilo);
        run.start();
        try{
            run.join();
        }catch(Exception ex){}
       // while(run.isAlive()){}

    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
