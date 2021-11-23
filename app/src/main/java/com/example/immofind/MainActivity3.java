package com.example.immofind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.immofind.data.ImoDBHelper;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class MainActivity3 extends AppCompatActivity {

    private ImageView imgtof;
    private Button btn, btn_cnx;
    private EditText nom, ville, description,loyer;

    private ImoDBHelper emdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        emdb = new ImoDBHelper(this);


        initActivity();
    }

    private void initActivity(){

        nom = findViewById(R.id.name);
        ville = findViewById(R.id.Ville);
        description = findViewById(R.id.et_description);
        loyer = findViewById(R.id.Loyermensuel);

        imgtof=(ImageView) findViewById(R.id.imageViewform);
        btn=(Button)findViewById(R.id.bt_image);
        btn_cnx=(Button)findViewById(R.id.btn_cnx);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "choisir une image"), 1);


            }
        });

        btn_cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (emdb.addSujet(nom.getText().toString(), ville.getText().toString(), Integer.valueOf(loyer.getText().toString()),  description.getText().toString(),imgViewToByte( imgtof))) {
                            Log.i("user ---", "USER ENTERED SUCCESSFULLY");
                            Snackbar.make(v, "Sujet ENREGISTRE AVEC SUCCES.", Snackbar.LENGTH_LONG).setAction("", null).show();
                            Intent home = new Intent(MainActivity3.this,MainActivity.class);
                            startActivity(home);
                            finish();
                        } else {
                            Snackbar.make(v, "FAILED TO ENTER CURRENT SUBJECT. TRY AGAIN LATER.", Snackbar.LENGTH_LONG).setAction("", null).show();
                        }

                    }
                },1500);

            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Uri imageUri = data.getData();
            imgtof.setImageURI(imageUri);
        }else {
            Toast.makeText(MainActivity3.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    private byte[] imgViewToByte(ImageView imagev){

        Bitmap bitmap = ((BitmapDrawable) imagev.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytearray  = stream.toByteArray();
        return bytearray;
    }
}