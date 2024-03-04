package com.example.bloconotassimples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class AdapterNotas extends RecyclerView.Adapter<AdapterNotas.MyViewHolder>  {

      /*
        Criar a classe ViewHolder e Extendeer para a ViewHolder e criar o construtor
        Criar as variaveis dos objetos de retorno da lista - Titulo, Descriçao e tempo
        Chamar os itens na Adpter e retornar seus objetos do layout da lista
        Extender a classe do adpter para Adpter

        RealmResults - Esta classe contém todas as correspondências de um RealmQuery para um determinado Realm
        Os objetos não são copiados do Realm para a lista RealmResults, mas apenas referenciados do RealmResult.
        Isso economiza memória e aumenta a velocidade.
        RealmResults são visualizações ao vivo, o que significa que se estiver em um thread Looper, ele atualizará automaticamente os resultados da consulta após a confirmação de uma transação.


    *  */

    // Crie as variaveis e chame o construtor
    Context context;
    RealmResults<Notas> listaNotas;

    // Constutor Gerado das variaveis
    public AdapterNotas(Context context, RealmResults<Notas> listaNotas) {
        this.context = context;
        this.listaNotas = listaNotas;
    }

    // Inflar e chamar o layut para a lista de notas
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itens_notas, parent, false));
    }

    // Pega os itebs da nossa nota e lista por posição
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Pega a classe notas e recebe a lista de notas intanciada acima e puxa a posição de cada lista
        Notas notas = listaNotas.get(position);
        holder.tituloOutput.setText(notas.getTitulo());
        holder.descricaoOutput.setText(notas.getDescricao());

        // Formatação de data e hora para mostrar o resultado
        String formatarHora = DateFormat.getDateTimeInstance().format(notas.tempoCriado);
        // Pega a data hora que formatamos
        holder.tempoOutput.setText(formatarHora);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                // Criação de PopPup para deletar a nota
                PopupMenu menu = new PopupMenu(context, view);
                menu.getMenu().add("DELETAR");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("DELETAR")){
                            // Deletar a nota
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            notas.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context, "NOTA DELETADA", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{



    TextView tituloOutput, descricaoOutput, tempoOutput;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloOutput = itemView.findViewById(R.id.txt_saida_titulo);
            descricaoOutput = itemView.findViewById(R.id.txt_saida_descricao);
            tempoOutput = itemView.findViewById(R.id.txt_saida_tempo);
        }
    }
}

