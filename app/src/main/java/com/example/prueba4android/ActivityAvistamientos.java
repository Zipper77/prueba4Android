package com.example.prueba4android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityAvistamientos extends AppCompatActivity {

    private List<Pokémon> listaDePokémon = new ArrayList<>();
    ArrayAdapter<Pokémon> adaptador;

    ListView listView;

    FirebaseDatabase bd;
    DatabaseReference referenciaBD;

    String dueño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avistamientos);

        listView = (ListView) findViewById(R.id.listView_Pokémon);

        dueño = getIntent().getStringExtra("dueño");

        iniciarFirebase();
        cargarNodosALista();
    }

    private void cargarNodosALista() {
        referenciaBD.child("Pokémon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaDePokémon.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    Pokémon p = ds.getValue(Pokémon.class);
                    if (p.getDueño().equals(dueño)){
                        listaDePokémon.add(p);
                        adaptador = new ArrayAdapter<Pokémon>(ActivityAvistamientos.this, android.R.layout.simple_list_item_1, listaDePokémon);
                        listView.setAdapter(adaptador);
                        adaptador.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        bd = FirebaseDatabase.getInstance();
        referenciaBD = bd.getReference();
    }
}