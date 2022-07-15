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
                        toast = Toast.makeText(getApplicationContext(), "Ingreso con éxito.", Toast.LENGTH_SHORT);
                        break;
                    case(401):
                        toast = Toast.makeText(getApplicationContext(), "Email y/o contraseña inválidos.", Toast.LENGTH_SHORT);
                        break;
                    default:
                        toast = Toast.makeText(getApplicationContext(), "Error inesperado - "+reg.getCodigo()+" - "+reg.getMensaje(), Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        };

        //Validación de nombre
        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0 && !Pattern.compile("[^a-zA-Zá-úÁ-Ú\\s]").matcher(s).find()) //ACÁ AGREGAR CONDICIONES PARA NOMBRE
                {
                    //nombre válido, activar botón Registrar y poner campo en verde
                    nombreValid = true;
                    if (emailValid && passValid && apellidoValid) {
                        Botones.activar(registrar,clickListenerRegistrar);
                    }
                } else {
                    //nombre inválido, apagar botón Registrar y poner campo en rojo
                    nombreValid = false;
                    Botones.desactivar(registrar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Validación de apellido
        apellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0 && !Pattern.compile("[^a-zA-Zá-úÁ-Ú\\s]").matcher(s).find()) //ACA AGREGAR CONDICIONES PARA APELLIDO (mayus, minus, etc.)
                {
                    //apellido válido, activar botón Registrar y poner campo en verde
                    apellidoValid = true;
                    if (emailValid && nombreValid && passValid) {
                        Botones.activar(registrar,clickListenerRegistrar);
                    }
                } else {
                    //apellido inválido, apagar botón Registrar y poner campo en rojo
                    apellidoValid = false;
                    Botones.desactivar(registrar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Validación de email
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches())
                {
                    //email válido, activar botón Registrar y poner campo en verde
                    emailValid = true;
                    if (passValid && nombreValid && apellidoValid)
                        Botones.activar(registrar,clickListenerRegistrar);
                } else {
                    //email inválido, apagar botón Registrar y poner campo en rojo
                    emailValid = false;
                    Botones.desactivar(registrar);
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
                if (s != null && s.length() > 0) //ACA AGREGAR CONDICIONES PARA CONTRASEÑA (mayus, minus, etc.)
                {
                    //pass válida, activar botón Registrar y poner campo en verde
                    passValid = true;
                    if (emailValid && nombreValid && apellidoValid) {
                        Botones.activar(registrar,clickListenerRegistrar);
                    }
                } else {
                    //pass inválida, apagar botón Registrar y poner campo en rojo
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
