package com.yusuf.myphotogallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yusuf.myphotogallery.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ArrayList<PhotoDao> photoDaos;
    PhotoAdapter photoAdapter;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        database= this.openOrCreateDatabase("Images",MODE_PRIVATE,null);

        photoDaos=new ArrayList<>();
        getData();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        photoAdapter=new PhotoAdapter(photoDaos);
        binding.recyclerView.setAdapter(photoAdapter);
        Toast.makeText(this, "Use the button at the top right to add a image", Toast.LENGTH_SHORT).show();



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.photo_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void getData(){
        try {
            Cursor cursor = database.rawQuery("select * from images",null);
            int idIx = cursor.getColumnIndex("id");
            int titleIx = cursor.getColumnIndex("title");

            while (cursor.moveToNext()){
                String title = cursor.getString(titleIx);
                int id = cursor.getInt(idIx);
                PhotoDao photo = new PhotoDao(id,title+"\n \n");

                photoDaos.add(photo);
            }
            photoAdapter.notifyDataSetChanged();
            cursor.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.menuItem1){
            Intent intent = new Intent(MainActivity.this,PhotoActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}