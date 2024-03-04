package com.example.bloconotassimples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class NotasActivity extends AppCompatActivity {

    TextView txtTituloNota;
    EditText edtTitulo, edtDescricao;
    MaterialButton btnSalvarNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        txtTituloNota = findViewById(R.id.txt_titulo_nota);
        edtTitulo = findViewById(R.id.edt_titulo);
        edtDescricao = findViewById(R.id.edt_descricao);
        btnSalvarNota = findViewById(R.id.btn_salvar);

        // Configuração Realm
        /*
            Realm - Gerenciador de Transacional e de armazenamento persistente de objetos
            É responsavel por criar instancias do RealmObject
            Os objetos dentro de um Realm podem ser consultados e lidos a qualquer momento
            A criação, modificação e exclusão de objetos devem ser feitas dentro de uma transação.
            As transações garantem que múltiplas instâncias (em múltiplos threads) possam acessar os mesmos objetos em um estado consistente com garantias completas de ACID.
            As instâncias do Realm são armazenadas em cache automaticamente por thread usando contagem de referência, portanto, desde que a contagem de referência não chegue a zero, chamar getInstance(RealmConfiguration) retornará apenas o Realm armazenado em cache e deve ser considerada uma operação leve.
            As instâncias do Realm em threads sem um Looper não podem receber atualizações, a menos que update() seja chamado manualmente.

            Init - Inicializa a biblioteca Realm e cria uma configuração padrão pronta para uso.
            É necessário chamar este método antes de interagir com qualquer outra API do Realm.

            GetDefaultInstance - Define a configuração padrão STATIC do Realm
            Retorna: uma instância da classe Realm.
            Lança: NullPointerException – se nenhuma configuração padrão tiver sido definida

            Iniciamos a Realm no contexto da Aplicação

        */
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();


        // Ação do Botão Salvar
        btnSalvarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Criar variaveis para receber as definições de cada nota
                String titulo = edtTitulo.getText().toString();
                String descricao = edtDescricao.getText().toString();
                // Pega o tempo | Hora do sistema
                long dataCriacao = System.currentTimeMillis();

                /*
                    BeginTransaction - Inicia uma transação
                    Deve ser fechada por Realm.commitTransaction() ou abortada por Realm.cancelTransaction().
                    As transações são usadas para criar, atualizar e excluir atomicamente objetos dentro de um Realm.
                    Antes de iniciar uma transação, a instância do Realm é atualizada para a versão mais recente para incluir todas as alterações de outros threads.

                    CreateObject - Instancia e adiciona um novo objeto ao Realm.
                    Este método está disponível apenas para classes de modelo sem anotação @PrimaryKey
                    Se você deseja criar um objeto que tenha uma chave primária, use createObject(Class, Object) ou copyToRealm(RealmModel, ImportFlag...) em vez disso.

                    CommitTransaction - Todas as alterações desde Realm.beginTransaction() são persistidas no disco e o Realm volta a ser somente leitura.
                    Um evento é enviado para notificar todas as outras instâncias do Realm de que ocorreu uma alteração.
                    Quando o evento for recebido, os outros Realms atualizarão seus objetos e RealmResults para refletir as alterações deste commit
                 */

                // Inicializar transação para arquivar as notas no celular, como uma especie de banco de dados interno
                realm.beginTransaction();

                // Inicializar a classe de Notas com o realm inializado acima
                Notas notas = realm.createObject(Notas.class);

                // Setar na transação as variaveis informadas pelo usuario na Classe de Notas que recebeu o realm
                notas.setTitulo(titulo);
                notas.setDescricao(descricao);
                notas.setTempoCriado(dataCriacao);

                // Commita a transação
                realm.commitTransaction();

                // Chama um tost para verificar se a nota foi salva
                Toast.makeText(getApplicationContext(), "Nota Salva com Sucesso", Toast.LENGTH_SHORT).show();

                // Finaliza Operação
                finish();

            }
        });
    }
}