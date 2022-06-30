package com.example.celiaquia;

import android.os.StrictMode;
import android.util.Log;

import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.net.ssl.HttpsURLConnection;

public class Conexion {

    public HttpsURLConnection conectar() {

       // Runnable hilo = () -> {
            HttpsURLConnection hpCon = null;
            String salida = "{\"email\":\"admin@gmail.com\",\"password\":\"123456\"}";
            try {
                URL hp = new URL("https://springceliaquia.herokuapp.com/api/usuario/login");

                hpCon = (HttpsURLConnection) hp.openConnection();

                hpCon.setConnectTimeout(5000);
                hpCon.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                hpCon.setDoOutput(true);
                hpCon.setDoInput(true);


    /*
                OutputStream os = hpCon.getOutputStream();
                os.write(salida.toString().getBytes("UTF-8"));
                os.close();

                // read the response
                a = hpCon.getResponseCode();*/

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return hpCon;
        };
        //Thread run = new Thread(hilo);

        // Starting the thread
        //run.start();

    }

