package com.example.bloconotassimples;

public class Passos {


    /*
            I - Add JCenter ** JCenter está depreciado e descontinuado desde 2021, utiliza-se MavenCetral
            I - Add o Plugin Realm - Na nova configuração do android studio para add o plugin fazemos o seguinte:

            No Moduylo de projeto deixamos assim
            buildscript {
                repositories {
                    mavenCentral()
                }
                dependencies {
                    classpath "io.realm:realm-gradle-plugin:10.11.1"
                }
            }

            No Modulo de App, chamamos o plugin
            plugins {
                id 'com.android.application'
                id 'realm-android'
            }

            apply plugin: "realm-android"



            II - Add a dependencia da RecyclerView
            III - Crie uma classe para inserir os Itens da lista
            IV - Criar Layout principal
            V - Hooks e ClickListener
            VI - Criar uma activity para a criação das notas e start ela no main
            VII - Criar layout da tela de notas
            VIII - Configurar a Realm no activity de notas
            XI - Criar o recycler para mostrar a lista de notas criada




        *  */
}
