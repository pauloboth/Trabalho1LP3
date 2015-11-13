package com.example.aulas.bonsnegocios;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DAO.ProdutoDAO;
import Model.Produto;

public class ProdutoActivityForm extends AppCompatActivity {
    private Produto produto = new Produto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button)findViewById(R.id.btnSalvar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Getproduto())
                    new Salvar().execute();
            }
        });
    }

    private  boolean Getproduto(){
        produto = new Produto();
        EditText nome = (EditText)findViewById(R.id.nome);
        EditText descricao = (EditText)findViewById(R.id.descricao);
        EditText valor = (EditText)findViewById(R.id.valor);
        produto.setPro_nome(nome.getText().toString());
        produto.setPro_descricao(descricao.getText().toString());
        if(valor.getText().toString().trim()!="" && valor.getText().toString().trim()!=null) {
            produto.setPro_preco(Double.parseDouble(valor.getText().toString().replace(",",".")));
            return true;
        }else{
            return false;
        }
    }


    private class Salvar extends AsyncTask<Produto,Integer,Boolean> {
        ProdutoDAO DAO = new ProdutoDAO();
        @Override
        protected Boolean doInBackground(Produto... params) {
            try {
                return DAO.Salvar(produto);
            }catch (Exception e){
                Log.e("EROO", e.toString());
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean salvo) {
            super.onPostExecute(salvo);
            if(salvo){
                finish();
            }
            else {
                Log.e("EROO","NOA SSALVO");
            }

        }
    }

    ////codig cadasrto
   /// DatabaseConnector db = new DataBaseConnection();





}
