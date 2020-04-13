package com.example.ejemploappexperto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapterVivienda extends ArrayAdapter<Vivienda> {

    Context context;
    List<Vivienda> arrayListVivienda;

    public MyAdapterVivienda(@NonNull Context context, List<Vivienda> arrayListVivienda) {
        super(context, R.layout.custom_list_item_vivienda, arrayListVivienda);

        this.context = context;
        this.arrayListVivienda = arrayListVivienda;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item_vivienda,null,true);

        TextView tvID = view.findViewById(R.id.txt_idvivienda);
        //TextView tvCorreo = view.findViewById(R.id.txt_correo);

        tvID.setText(arrayListVivienda.get(position).getIdvivienda());
        //tvCorreo.setText(arrayListVivienda.get(position).getCorreo());

        return view;
    }


}
