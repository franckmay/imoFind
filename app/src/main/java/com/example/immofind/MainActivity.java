package com.example.immofind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.immofind.data.ImoDBHelper;
import com.example.immofind.data.SujetContrat;
import com.example.immofind.model.Sujet;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView simpleList;
    List<Sujet> birdList;
    FloatingActionButton bouton;
    private Cursor cursor;
    private ImoDBHelper emdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emdb = new ImoDBHelper(this);
        cursor = emdb.getAllSujets();

        bouton=findViewById(R.id.floatingActionButton);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });

        simpleList = (GridView) findViewById(R.id.simpleGridView);
//        birdList.add(new Item("STUDIO Loyer Mensuel: 40 000 FCFA",R.drawable.image1));
//        birdList.add(new Item("STUDIO Loyer Mensuel: 50 000 FCFA",R.drawable.image2));
//        birdList.add(new Item("CHAMBRE Loyer Mensuel: 35 000 FCFA",R.drawable.image3));
//        birdList.add(new Item("APPARTEMENT Loyer Mensuel: 110 000 FCFA",R.drawable.image4));
//        birdList.add(new Item("TERRAIN Superficie: 500 M²",R.drawable.image5));
//        birdList.add(new Item("TERRAIN Superficie: 800 M²",R.drawable.image6));
//        birdList.add(new Item("STUDIO Loyer Mensuel: 45 000 FCFA",R.drawable.image7));
//        birdList.add(new Item("CHAMBRE Loyer Mensuel: 25 000 FCFA",R.drawable.image8));
//        birdList.add(new Item("TERRAIN Superficie: 500 M²",R.drawable.image9));
//        birdList.add(new Item("CHAMBRE Loyer Mensuel: 30 000 FCFA",R.drawable.image10));
//        birdList.add(new Item("APPARTEMENT Loyer Mensuel: 130 000 FCFA",R.drawable.image11));
//        birdList.add(new Item("TERRAIN Superficie: 1000 M²",R.drawable.image12));

        initData();
        MyAdapter myAdapter=new MyAdapter(birdList, this, R.layout.grid_view_item);
        simpleList.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    private void initData() {
        birdList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String ville, description;
                int loyer;
                byte[] img = cursor.getBlob(cursor.getColumnIndex(SujetContrat.SujetEntry.COLUMN_SUJET_IMAGE));
                ville = cursor.getString(cursor.getColumnIndex(SujetContrat.SujetEntry.COLUMN_SUJET_VILLE));
                loyer = cursor.getInt(cursor.getColumnIndex(SujetContrat.SujetEntry.COLUMN_SUJET_LOYER));
                description = cursor.getString(cursor.getColumnIndex(SujetContrat.SujetEntry.COLUMN_SUJET_DESCRIPTION));


                birdList.add(new Sujet(ville, description, loyer, img));

            } while (cursor.moveToNext());
        }
        cursor.close();

    }

}
