package com.example.prueba4android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActivityRegistrarPokemon extends AppCompatActivity {

    TextView nombre;
    RadioGroup ataque, vida, defensa;

    FirebaseDatabase bd;
    DatabaseReference referenciaBD;

    String dueño;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pokemon);

        nombre = (TextView) findViewById(R.id.inputNombre);
        ataque = (RadioGroup) findViewById(R.id.ataque);
        vida = (RadioGroup) findViewById(R.id.vida);
        defensa = (RadioGroup) findViewById(R.id.defensa);
        dueño = getIntent().getStringExtra("Username");

        iniciarFirebase();
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        bd = FirebaseDatabase.getInstance();
        referenciaBD = bd.getReference();
    }

    public void guardarAFB(View view) {
        String varAtaque = "", varVida = "", varDefensa = "";
        switch (ataque.getCheckedRadioButtonId()){
            case (R.id.ataqueAlto): {
                varAtaque += "Alto";
                break;
            }
            case (R.id.ataqueMediano): {
                varAtaque += "Mediano";
                break;
            }
            case (R.id.ataqueBajo): {
                varAtaque += "Bajo";
                break;
            }
        }

        switch (vida.getCheckedRadioButtonId()){
            case (R.id.vidaAlto): {
                varVida += "Alto";
                break;
            }
            case (R.id.vidaMediano): {
                varVida += "Mediano";
                break;
            }
            case (R.id.vidaBajo): {
                varVida += "Bajo";
                break;
            }
        }

        switch (defensa.getCheckedRadioButtonId()){
            case (R.id.defensaAlto): {
                varDefensa += "Alto";
                break;
            }
            case (R.id.defensaMediano): {
                varDefensa += "Mediano";
                break;
            }
            case (R.id.defensaBajo): {
                varDefensa += "Bajo";
                break;
            }
        }

        Pokémon p = new Pokémon();
        p.setId(UUID.randomUUID().toString());
        p.setNombre(nombre.getText().toString());
        p.setAtaque(varAtaque+"");
        p.setVida(varVida+"");
        p.setDefensa(varDefensa+"");
        p.setDueño(dueño);

        referenciaBD.child("Pokémon").child(p.getId()).setValue(p);

        Toast.makeText(getApplicationContext(), "Pokémon añadido", Toast.LENGTH_SHORT).show();
    }


    public void verAvistamientos(View view) {
        Intent i = new Intent(getApplicationContext(), ActivityAvistamientos.class);
        i.putExtra("dueño",dueño);
        startActivity(i);
    }
}