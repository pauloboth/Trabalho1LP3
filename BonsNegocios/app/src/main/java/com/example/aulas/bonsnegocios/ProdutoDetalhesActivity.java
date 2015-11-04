package com.example.aulas.bonsnegocios;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

import DAO.ProdutoEspecificacaoDAO;
import Model.Produto;
import Model.ProdutoEspecificacao;

public class ProdutoDetalhesActivity extends AppCompatActivity {

    private  Produto produto = new Produto();
    private ListView listViewEspecificacoes;
    private ArrayAdapter<ProdutoEspecificacao> adpProdutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhes);


        listViewEspecificacoes = (ListView)findViewById(R.id.produtoListView);
        adpProdutos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        Bundle bundle  = getIntent().getExtras();
        if(bundle!=null)
            if(bundle.containsKey("PRODUTO")) {
                produto = (Produto) bundle.getSerializable("PRODUTO");
                SetProduto();
            }

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CarregaEspecificacoes().execute();
    }

    private void SetProduto(){
        TextView nome = (TextView)findViewById(R.id.produtoNome);
        TextView desc = (TextView)findViewById(R.id.produtoDescricao);
        TextView preco = (TextView)findViewById(R.id.produtoPreco);
        nome.setText(produto.getPro_nome());
        desc.setText(produto.getPro_descricao());
        Format f = new DecimalFormat();
        preco.setText(f.format(produto.getPro_preco()));

    }

    private class CarregaEspecificacoes extends AsyncTask<Produto, String, List<ProdutoEspecificacao>>{
        ProdutoEspecificacaoDAO pDAO = new ProdutoEspecificacaoDAO();
        @Override
        protected List<ProdutoEspecificacao> doInBackground(Produto... params) {
            return pDAO.Seleciona(produto.getPro_id());
        }

        @Override
        protected void onPostExecute(List<ProdutoEspecificacao> lsProdutoEspecificacaos) {
            super.onPostExecute(lsProdutoEspecificacaos);
            if(lsProdutoEspecificacaos!=null){
                adpProdutos.clear();
                for(ProdutoEspecificacao prs : lsProdutoEspecificacaos)
                    adpProdutos.add(prs);
                listViewEspecificacoes.setAdapter(adpProdutos);
            }

        }
    }


}
