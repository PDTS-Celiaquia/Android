package com.example.celiaquia.logIn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.celiaquia.R;
import com.example.celiaquia.logIn.logIn;

public class logInView extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        Button ingresar = findViewById(R.id.ingresar);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email, password;
                email = findViewById(R.id.email);
                password = findViewById(R.id.password);

                logIn login= new logIn();
                login.logear(email,password);

                Toast toast;
                switch (login.getCodigo()){
                    case(200):
                        toast = Toast.makeText(getApplicationContext(), "ingreso con exito", Toast.LENGTH_SHORT);
                        break;
                    case(401):
                        toast = Toast.makeText(getApplicationContext(), "Email y/o contraseña incorrectos", Toast.LENGTH_SHORT);
                        break;
                    default:
                        toast = Toast.makeText(getApplicationContext(), "Error inesperado "+login.getCodigo(), Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });
    }


}
