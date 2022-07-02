package com.example.celiaquia.registro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.celiaquia.R;

public class registroView extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button registrar = findViewById(R.id.registrarse);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email, password,nombre,apellido;
                nombre = findViewById(R.id.nombre);
                apellido = findViewById(R.id.apellido);
                email = findViewById(R.id.emailr);
                password = findViewById(R.id.passwordr);

                registro reg= new registro();
                reg.registrar(nombre,apellido,email,password);

                Toast toast;
                switch (reg.getCodigo()){
                    case(200):
                        toast = Toast.makeText(getApplicationContext(), "Ingreso con éxito", Toast.LENGTH_SHORT);
                        break;
                    case(401):
                        toast = Toast.makeText(getApplicationContext(), reg.getMensaje(), Toast.LENGTH_SHORT);
                        break;
                    default:
                        toast = Toast.makeText(getApplicationContext(), "Error inesperado - "+reg.getCodigo()+" - "+reg.getMensaje(), Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });
    }
}
