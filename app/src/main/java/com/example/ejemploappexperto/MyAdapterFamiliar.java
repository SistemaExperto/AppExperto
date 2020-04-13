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

public class MyAdapterFamiliar extends ArrayAdapter<Familiar> {

    Context context;
    List<Familiar> arrayListFamiliar;

    public MyAdapterFamiliar(@NonNull Context context, List<Familiar> arrayListFamiliar) {
        super(context, R.layout.custom_list_item_familiar, arrayListFamiliar);

        //super(context, R.layout.custom_list_item,arrayListFamiliar);

        this.context = context;
        this.arrayListFamiliar = arrayListFamiliar;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item_familiar,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvCorreo = view.findViewById(R.id.txt_correo);

        tvID.setText(arrayListFamiliar.get(position).getIdfamiliar());
        tvCorreo.setText(arrayListFamiliar.get(position).getCorreo());

        return view;
    }




}
