package com.example.learnroomcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.learnroomcrud.databinding.ActivityRoomCreateBinding;
import com.example.learnroomcrud.model.Barang;

public class RoomReadSingleActivity extends AppCompatActivity {

    ActivityRoomCreateBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        EditText etNama = findViewById(R.id.et_namabarang);
        EditText etMerk = findViewById(R.id.et_merkbarang);
        EditText etHarga = findViewById(R.id.et_hargabarang);
        Button btSubmit = findViewById(R.id.bt_submit);

        etNama.setEnabled(false);
        etMerk.setEnabled(false);
        etHarga.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Barang barang = (Barang) getIntent().getSerializableExtra("data");
        if(barang!=null){
            etNama.setText(barang.getNamaBarang());
            etMerk.setText(barang.getMerkBarang());
            etHarga.setText(barang.getHargaBarang());
        }

    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, RoomReadSingleActivity.class);
    }
}
