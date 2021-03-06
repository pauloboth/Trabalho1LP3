package com.example.aulas.bonsnegocios;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import DAO.ProdutoDAO;
import Model.Produto;

public class ProdutoActivity extends AppCompatActivity {

    private ListView produtoListView;
    //static public List<Produto> lsItems;
    // private Button btnNovo;
    private ArrayAdapter<Produto> adpProduto;
    private ProdutoDAO DAO = new ProdutoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
   /*     Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        adpProduto = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        produtoListView = (ListView) findViewById(R.id.produtoListView);

        produtoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto = adpProduto.getItem(position);
                Intent i = new Intent(ProdutoActivity.this, ProdutoDetalhesActivity.class);
                i.putExtra("PRODUTO", produto);
                startActivity(i);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent i = new Intent(ProdutoActivity.this, ProdutoActivityForm.class);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("--ACAO--", "onRESUME");
        adpProduto.clear();
        new CarregaRegistros().execute();

    }

    protected void AtualizaGrid(List<Produto> lsItem){
        if(lsItem!=null) {
            adpProduto.clear();
            for (Produto pd : lsItem) {
                adpProduto.add(pd);//converte object em Grupo
            }
            produtoListView.setAdapter(adpProduto);
        }
    }

    private class CarregaRegistros extends AsyncTask<String, Integer, List<Produto>> {
        ProgressBar pg = (ProgressBar) findViewById(R.id.produtoProgressBar);
        @Override
        protected List<Produto> doInBackground(String... params) {
            try{
                return DAO.SelecionaProduto();
            }catch (Exception e){
                Log.e("CARREGA",e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Produto> lsItem) {
            super.onPostExecute(lsItem);

            if(lsItem!=null) {
                Log.i("Carrega", "RETOURNOU com" + lsItem.size());
                AtualizaGrid(lsItem);
            }else
                Log.i("Carrega", "--- LISTA NULA ---");
            pg.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pg.setProgress(values[0]);
        }
    }

}
