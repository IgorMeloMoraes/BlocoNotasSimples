package com.example.bloconotassimples;

import io.realm.RealmObject;

public class Notas extends RealmObject {

    // Criamos nossos objetos de retono da nossa lista - Titulo da lista, descrição, e tempo
    String titulo;
    String descricao;
    long tempoCriado;

    // Gerar os Getter e Setter

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }

    public long getTempoCriado() {
        return tempoCriado;
    }

    public void setTempoCriado(long tempoCriado) {
        this.tempoCriado = tempoCriado;
    }


    // Depois Extendemos para o RealmObjetct


}
