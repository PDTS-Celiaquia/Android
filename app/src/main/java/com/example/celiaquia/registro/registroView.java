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
import com.example.celiaquia.util.Botones;

import java.util.regex.Pattern;

public class registroView extends Activity {
    private EditText email, password,nombre,apellido;
    private boolean emailValid = false, passValid = false, nombreValid = false, apellidoValid = false;

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
                        toast = Toast.makeText(getApplicationContext(), "Ingreso con �xito.", Toast.LENGTH_SHORT);
                        break;
                    case(401):
                        toast = Toast.makeText(getApplicationContext(), "Email y/o contrase�a inv�lidos.", Toast.LENGTH_SHORT);
                        break;
                    default:
                        toast = Toast.makeText(getApplicationContext(), "Error inesperado - "+reg.getCodigo()+" - "+reg.getMensaje(), Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        };

        //Validaci�n de nombre
        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0 && !Pattern.compile("[^a-zA-Z�-��-�\\s]").matcher(s).find()) //AC� AGREGAR CONDICIONES PARA NOMBRE
                {
                    //nombre v�lido, activar bot�n Registrar y poner campo en verde
                    nombreValid = true;
                    if (emailValid && passValid && apellidoValid) {
                        Botones.activar(registrar,clickListenerRegistrar);
                    }
                } else {
                    //nombre inv�lido, apagar bot�n Registrar y poner campo en rojo
                    nombreValid = false;
                    Botones.desactivar(registrar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Validaci�n de apellido
        apellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0 && !Pattern.compile("[^a-zA-Z�-��-�\\s]").matcher(s).find()) //ACA AGREGAR CONDICIONES PARA APELLIDO (mayus, minus, etc.)
                {
                    //apellido v�lido, activar bot�n Registrar y poner campo en verde
                    apellidoValid = true;
                    if (emailValid && nombreValid && passValid) {
                        Botones.activar(registrar,clickListenerRegistrar);
                    }
                } else {
                    //apellido inv�lido, apagar bot�n Registrar y poner campo en rojo
                    apellidoValid = false;
                    Botones.desactivar(registrar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Validaci�n de email
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches())
                {
                    //email v�lido, activar bot�n Registrar y poner campo en verde
                    emailValid = true;
                    if (passValid && nombreValid && apellidoValid)
                        Botones.activar(registrar,clickListenerRegistrar);
                } else {
                    //email inv�lido, apagar bot�n Registrar y poner campo en rojo
                    emailValid = false;
                    Botones.desactivar(registrar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Validaci�n de contrase�a
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) //ACA AGREGAR CONDICIONES PARA CONTRASE�A (mayus, minus, etc.)
                {
                    //pass v�lida, activar bot�n Registrar y poner campo en verde
                    passValid = true;
                    if (emailValid && nombreValid && apellidoValid) {
                        Botones.activar(registrar,clickListenerRegistrar);
                    }
                } else {
                    //pass inv�lida, apagar bot�n Registrar y poner campo en rojo
                    passValid = false;
                    Botones.desactivar(registrar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
