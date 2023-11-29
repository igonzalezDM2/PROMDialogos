package com.example.ariketadialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoSalir extends DialogFragment {

    private OnDialogoConfirmacionListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_salir, null));
        builder.setPositiveButton("Sí", (dialog, which) -> {
            listener.onPossitiveButtonClick();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.cancel();
        });

        Dialog dialogo =  builder.create();
        dialogo.setCancelable(false);
        dialogo.setCanceledOnTouchOutside(false);
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
}
