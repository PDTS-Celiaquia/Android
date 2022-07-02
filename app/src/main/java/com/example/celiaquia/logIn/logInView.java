package com.example.celiaquia.logIn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.ColorInt;

import com.example.celiaquia.R;
import com.example.celiaquia.registro.registroView;

import java.util.StringTokenizer;

public class logInView extends Activity {

    EditText email, password;
    boolean emailValidado = false, passValidada = false;

    private void apagarBoton(Button boton) {
        boton.setClickable(false);
        boton.setBackgroundColor(getResources().getColor(R.color.grisclarito));
    }

    private void prenderBoton(Button boton, View.OnClickListener listener) {
        boton.setOnClickListener(listener);
        boton.setBackgroundColor(getResources().getColor(R.color.naranja));
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        Button ingresar = findViewById(R.id.ingresar);
        View.OnClickListener clickListenerIngresar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn login= new logIn();
                login.logear(email,password);

                Toast toast;
                switch (login.getCodigo()){
                    case(200):
                        toast = Toast.makeText(getApplicationContext(), "Ingreso con exito", Toast.LENGTH_SHORT);
                        break;
                    default:
                        toast = Toast.makeText(getApplicationContext(), "Error inesperado - "+login.getCodigo()+" - "+login.getMensaje(), Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        };

        //Validación de email
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches())
                {
                    //email valido, activar boton ingresar y poner campo en verde
                    emailValidado = true;
                    if (passValidada)
                        prenderBoton(ingresar,clickListenerIngresar);
                } else {
                    //email invalido, apagar boton ingresar y poner campo en rojo
                    emailValidado = false;
                    apagarBoton(ingresar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Validación de contraseña
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) //ACÁ AGREGAR CONDICIONES PARA CONTRASEÑA (mayus, minus, etc.)
                {
                    //pass valida, activar boton ingresar y poner campo en verde
                    passValidada = true;
                    if (emailValidado) {
                        prenderBoton(ingresar,clickListenerIngresar);

                    }
                } else {
                    //pass invalida, apagar boton ingresar y poner campo en rojo
                    passValidada = false;
                    apagarBoton(ingresar);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button registrar = findViewById(R.id.registrarse);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), registroView.class);
                startActivity(i);
            }
        });
    }
}
