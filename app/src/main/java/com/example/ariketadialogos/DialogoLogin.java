package com.example.ariketadialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoLogin extends DialogFragment {

    private OnDialogoConfirmacionListener listener;

    private EditText etUser, etPass;
    private TextView tvError;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Introduzca sus datos")
                .setTitle("Inicio de sesión")
                .setPositiveButton("ENTRAR", null)
                .setNegativeButton("SALIR", (dialog, which) -> {
                    listener.onNegativeButtonClick();
                });

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        etUser = new EditText(getContext());
        etUser.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        etUser.setInputType(InputType.TYPE_CLASS_TEXT);
        etUser.setHint("Usuario");
        linearLayout.addView(etUser);

        etPass = new EditText(getContext());
        etPass.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        etPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPass.setHint("Contraseña");
        linearLayout.addView(etPass);

        tvError = new TextView(getContext());
        linearLayout.addView(tvError);


        builder.setView(linearLayout);

        AlertDialog dialogo = builder.create();
        dialogo.setCancelable(false);
        dialogo.setCanceledOnTouchOutside(false);

        dialogo.setOnShowListener((v) -> {
            dialogo
                    .getButton(AlertDialog.BUTTON_POSITIVE)
                    .setOnClickListener(v1 -> comprobarAcceso(dialogo));
        });

        return dialogo;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnDialogoConfirmacionListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" no implementó OnDialogoConfirmacionListener");
        }
    }

    private void comprobarAcceso(AlertDialog dialogo) {
        String user = etUser.getText().toString();
        String pass = etPass.getText().toString();
        if ("usuario1".equals(user) && "123456".equals(pass)) {
            dialogo.cancel();
        } else {
            etPass.setText("");
            tvError.setText("Credenciales incorrectas; vuelva a intentarlo.");
        }
    }
}
