package com.example.learnroomcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.learnroomcrud.data.factory.AppDatabase;
import com.example.learnroomcrud.databinding.ActivityMainBinding;
import com.example.learnroomcrud.databinding.ActivityRoomCreateBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "barangdb").build();

        db.barangDAO();

        b.addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RoomCreateActivity.class));
            }
        });

        b.readData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RoomReadActivity.class));
            }
        });
    }
}
