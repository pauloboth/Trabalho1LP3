package com.example.aulas.bonsnegocios;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import DAO.CategoriaDAO;
import Model.Categoria;

public class CategoriaActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Categoria> adpCategoria;
   private CategoriaDAO DAO = new CategoriaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        /*grupoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Grupo grupo = adpGrupo.getItem(position);
                Intent i = new Intent(GrupoActivity.this, GrupoActivityForm.class);
                i.putExtra("GRUPO", grupo);
                startActivity(i);
            }
        });
*/
        /*adpGrupo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);*/
        adpCategoria = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.categoriaListView);
        new CarregaRegistros().execute();
    }

    protected void AtualizaGrid(List<Categoria> lsItem){
        if(lsItem!=null) {
            adpCategoria.clear();
            for (Categoria pd : lsItem) adpCategoria.add(pd);//converte object em Grupo
            listView.setAdapter(adpCategoria);
        }
    }



    private class CarregaRegistros extends AsyncTask<String, Integer, List<Categoria>> {
        ProgressBar pg = (ProgressBar) findViewById(R.id.categoriaProgressBar);
        @Override
        protected List<Categoria> doInBackground(String... params) {
            try{
                return DAO.Seleciona();
            }catch (Exception e){
                Log.e("CARREGA-GRUPOS", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Categoria> lsItem) {
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
