package com.example.celiaquia.util;

import android.view.View;
import android.widget.Button;

import com.example.celiaquia.R;

public class Botones {
    public static void desactivar(Button boton) {
        boton.setClickable(false);
        boton.setBackgroundColor(boton.getContext().getResources().getColor(R.color.grisclarito));
    }

    public static void activar(Button boton, View.OnClickListener listener) {
        boton.setOnClickListener(listener);
        boton.setBackgroundColor(boton.getContext().getResources().getColor(R.color.naranja));
    }
}
