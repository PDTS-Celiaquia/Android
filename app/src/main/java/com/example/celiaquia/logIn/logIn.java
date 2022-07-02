package com.example.celiaquia.logIn;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.celiaquia.Conexion;
import com.example.celiaquia.R;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.celiaquia.R;

public class logIn extends Activity {


    int codigo;


    public logIn() {
        this.codigo = 0;
    }

    public void logear(EditText email, EditText password){



        JSONObject salida = new JSONObject();
        try {
            salida.put("email", email.getText().toString());
            salida.put("password", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
