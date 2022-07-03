package com.example.celiaquia.registro;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.celiaquia.Conexion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

import javax.net.ssl.HttpsURLConnection;

public class registro extends AppCompatActivity {
    private int codigo;
    private String mensaje;

    public registro() {
        this.codigo = 0;
    }

    public void registrar(EditText nombre, EditText apellido, EditText email, EditText pass) {
        JSONObject salida = new JSONObject();
        try {
            salida.put("nombre", nombre.getText().toString());
            salida.put("apellido", apellido.getText().toString());
            salida.put("email", email.getText().toString());
            salida.put("password", pass.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Runnable hilo = () -> {
            OutputStream os = null;
            try {
                HttpsURLConnection conexion = new Conexion().conectar("registro");
                os = conexion.getOutputStream();
                os.write(salida.toString().getBytes("UTF-8"));
                os.close();
                this.mensaje = conexion.getResponseMessage();
                this.codigo = conexion.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread run = new Thread(hilo);
        run.start();
        try{
            run.join();
        }catch(Exception ex){}

    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {if (mensaje == null) return "NULL"; else return mensaje;}
}
