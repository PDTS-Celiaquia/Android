package com.example.celiaquia.logIn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.celiaquia.R;
import com.example.celiaquia.registro.registroView;
import com.example.celiaquia.util.Botones;

public class logInView extends Activity {

    private EditText email, password;
    private boolean emailValid = false, passValid = false;

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
                        toast = Toast.makeText(getApplicationContext(), "Ingreso con �xito", Toast.LENGTH_SHORT);
                        break;
                    case(401):
                        toast = Toast.makeText(getApplicationContext(), "Email y/o contrase�a inv�lidos.", Toast.LENGTH_SHORT);
                        break;
                    default:
                        toast = Toast.makeText(getApplicationContext(), "Error inesperado - "+login.getCodigo()+" - "+login.getMensaje(), Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        };

        //Validaci�n de email
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches())
                {
                    //email v�lido, activar bot�n Ingresar y poner campo en verde
                    emailValid = true;
                    if (passValid)
                        Botones.activar(ingresar,clickListenerIngresar);
                } else {
                    //email inv�lido, apagar bot�n Ingresar y poner campo en rojo
                    emailValid = false;
                    Botones.desactivar(ingresar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Validaci�n de contrase�a
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) //AC� AGREGAR CONDICIONES PARA CONTRASE�A (mayus, minus, etc.)
                {
                    //pass v�lida, activar bot�n Ingresar y poner campo en verde
                    passValid = true;
                    if (emailValid)
                        Botones.activar(ingresar,clickListenerIngresar);
                } else {
                    //pass inv�lida, apagar bot�n Ingresar y poner campo en rojo
                    passValid = false;
                    Botones.desactivar(ingresar);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Cambio a pantalla de registro
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
