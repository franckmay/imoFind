package com.example.immofind;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.immofind.model.Sujet;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private final List<Sujet> sujetList;
    private final Context context;
    private final int layout;


    public MyAdapter(List<Sujet> sujetList, Context context, int layout) {
        this.sujetList = sujetList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return sujetList.size();
    }

    @Override
    public Object getItem(int position) {
        return sujetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class  ViewHolder {
        TextView loy, desc;
        ImageView imageview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();
        if (row==null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.loy = (TextView) row.findViewById(R.id.loyer);
            holder.desc = (TextView) row.findViewById(R.id.description);
            holder.imageview = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);
        }else {
            holder = (ViewHolder) row.getTag();
        }

        Sujet sujet = sujetList.get(position);
        holder.loy.setText(String.valueOf(sujet.getLoyer()));
        holder.desc.setText(sujet.getDescription());

        byte[] ressource = sujet.getImage();

        Bitmap bitmap = BitmapFactory.decodeByteArray(ressource, 0, ressource.length);
        holder.imageview.setImageBitmap(bitmap);



        return row;
    }
}




