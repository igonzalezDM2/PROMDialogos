package com.example.ariketadialogos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnDialogoConfirmacionListener {

    private Button btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoLogin dialogoLogin = new DialogoLogin();
        dialogoLogin.show(fragmentManager, "LOGIN");


        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(v -> {
            DialogoSalir dialogoSalir = new DialogoSalir();
            dialogoSalir.show(fragmentManager, "SALIR");
        });
    }

    @Override
    public void onPossitiveButtonClick() {
        finish();
    }

    @Override
    public void onNegativeButtonClick() {
        finish();
    }
}