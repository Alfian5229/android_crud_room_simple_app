package com.example.learnroomcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.learnroomcrud.databinding.ActivityRoomReadBinding;

public class RoomReadActivity extends AppCompatActivity {

    ActivityRoomReadBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityRoomReadBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }
}
