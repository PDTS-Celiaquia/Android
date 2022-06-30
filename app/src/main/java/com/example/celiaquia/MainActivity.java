package com.example.celiaquia;

import static java.lang.System.out;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;




import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

import com.example.celiaquia.Conexion;
import com.example.celiaquia.logIn;

public class MainActivity extends AppCompatActivity {

    //private ActivityMainBinding _binding = null
    private AppBarConfiguration appBarConfiguration;
    //private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);




        EditText email, password;
        Button ingresar,registrar;

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        ingresar = findViewById(R.id.ingresar);
        registrar = findViewById(R.id.registrarse);

        logIn login = new logIn();

//configura lo que se hace cuando se toca ingresar
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject salida = new JSONObject();

                try {
                    salida.put("email", email.getText().toString());
                    salida.put("password", password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int cod = 5;

                login.logear(salida);

                cod = login.getCodigo();
                Toast toast;
                switch (cod){
                    case(200):
                        toast = Toast.makeText(getApplicationContext(), "ingreso con exito", Toast.LENGTH_SHORT);
                        break;
                    case(401):
                        toast = Toast.makeText(getApplicationContext(), "Email y/o contraseña incorrectos", Toast.LENGTH_SHORT);
                        break;
                    default:
                        toast = Toast.makeText(getApplicationContext(), "Error inesperado "+cod, Toast.LENGTH_SHORT);
                }
                toast.show();
            }

        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
    }
}












/*
        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
