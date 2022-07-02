package com.example.celiaquia.registro;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.celiaquia.R;

public class registroView extends Activity {
    EditText email, password,nombre,apellido;
    boolean emailValidado = false, passValidada = false, nombreValidado = false, apellidoValidado = false;

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
        setContentView(R.layout.register);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        email = findViewById(R.id.emailr);
        password = findViewById(R.id.passwordr);

        Button registrar = findViewById(R.id.registrarse);
        View.OnClickListener clickListenerRegistrar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro reg= new registro();
                reg.registrar(nombre,apellido,email,password);

                Toast toast;
                switch (reg.getCodigo()){
                    case(200):
                        toast = Toast.makeText(getApplicationContext(), "Ingreso con éxito", Toast.LENGTH_SHORT);
                        break;
                    default:
                        toast = Toast.makeText(getApplicationContext(), "Error inesperado - "+reg.getCodigo()+" - "+reg.getMensaje(), Toast.LENGTH_SHORT);
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
                    if (passValidada && nombreValidado && apellidoValidado)
                        prenderBoton(registrar,clickListenerRegistrar);
                } else {
                    //email invalido, apagar boton ingresar y poner campo en rojo
                    emailValidado = false;
                    apagarBoton(registrar);
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
                    if (emailValidado && nombreValidado && apellidoValidado) {
                        prenderBoton(registrar,clickListenerRegistrar);
                    }
                } else {
                    //pass invalida, apagar boton ingresar y poner campo en rojo
                    passValidada = false;
                    apagarBoton(registrar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Validación de nombre
        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) //ACÁ AGREGAR CONDICIONES PARA NOMBRE
                {
                    //nombre valido, activar boton ingresar y poner campo en verde
                    nombreValidado = true;
                    if (emailValidado && passValidada && apellidoValidado) {
                        prenderBoton(registrar,clickListenerRegistrar);
                    }
                } else {
                    //nombre invalido, apagar boton ingresar y poner campo en rojo
                    nombreValidado = false;
                    apagarBoton(registrar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Validación de apellido
        apellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) //ACÁ AGREGAR CONDICIONES PARA APELLIDO (mayus, minus, etc.)
                {
                    //apellido valido, activar boton ingresar y poner campo en verde
                    apellidoValidado = true;
                    if (emailValidado && nombreValidado && passValidada) {
                        prenderBoton(registrar,clickListenerRegistrar);
                    }
                } else {
                    //apellido invalido, apagar boton ingresar y poner campo en rojo
                    apellidoValidado = false;
                    apagarBoton(registrar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
