package com.example.bloconotassimples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    TextView txtTitulo;
    MaterialButton btnNovaNota;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitulo = findViewById(R.id.txt_title);
        recyclerView = findViewById(R.id.recyclerview);
        btnNovaNota = findViewById(R.id.btn_add_notas);

        // Ação de Click
        btnNovaNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotasActivity.class));
            }
        });

        // Listamos as nossas notas na tela principal chamando o realm e a recyclerview

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        // Retorna o resultdado completo (findAll) da pesquisa e transação da lista de notas
        RealmResults<Notas> listaNotas = realm.where(Notas.class).findAll();

        // Configura o Recyccler View mostrando na tela
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Chamamos nossos adpter criado e passamos por parametro tanto o contexto quando a lista que indicamos para o reaml
        AdapterNotas adapterNotas = new AdapterNotas(getApplicationContext(), listaNotas);
        recyclerView.setAdapter(adapterNotas);

        listaNotas.addChangeListener(new RealmChangeListener<RealmResults<Notas>>() {
            @Override
            public void onChange(RealmResults<Notas> notas) {
                adapterNotas.notifyDataSetChanged();
            }
        });



    }
}