package com.example.prueba4android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity {

    private List<Usuario> listaDeUsuario = new ArrayList<>();
    ArrayAdapter<Usuario> adaptadorUsuario;

    TextView username, contraseña;

    ListView listView;
    
    FirebaseDatabase bd;
    DatabaseReference referenciaBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (TextView) findViewById(R.id.inputUsername);
        contraseña = (TextView) findViewById(R.id.inputContra);
        listView = new ListView(this);
        iniciarFirebase1();
        cargarNodosALista();
    }

    private void iniciarFirebase1() {
        FirebaseApp.initializeApp(this);
        bd = FirebaseDatabase.getInstance();
        referenciaBD = bd.getReference();
    }

    public void registrarUsuario(View view) {
        Usuario u = new Usuario();
        u.setId(UUID.randomUUID().toString());
        u.setUsername(username.getText().toString());
        u.setContraseña(contraseña.getText().toString());
        
        referenciaBD.child("Usuario").child(u.getId()).setValue(u);
        
        limpiarFormulario();
        Toast.makeText(getApplicationContext(), "Usuario añadido", Toast.LENGTH_SHORT).show();
    }

    private void limpiarFormulario() {
        username.setText("");
        contraseña.setText("");
    }

    public void iniciarSesionUsuario(View view) {
        try {
            String textousuario = username.getText().toString();
            String textocontraseña = contraseña.getText().toString();
            for (int i = 0; i < adaptadorUsuario.getCount(); i++){
                Usuario usuario = adaptadorUsuario.getItem(i);
                if(usuario.getUsername().equals(textousuario) && usuario.getContraseña().equals(textocontraseña)){
                    Intent intent = new Intent(getApplicationContext(), ActivityRegistrarPokemon.class);
                    intent.putExtra("Username",textousuario);
                    startActivity(intent);
                    break;
                }
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Oops, Registrate!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarNodosALista() {
        referenciaBD.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaDeUsuario.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    Usuario u = ds.getValue(Usuario.class);
                    listaDeUsuario.add(u);
                    adaptadorUsuario = new ArrayAdapter<Usuario>(MainActivity.this, android.R.layout.simple_list_item_1, listaDeUsuario);
                    listView.setAdapter(adaptadorUsuario);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}