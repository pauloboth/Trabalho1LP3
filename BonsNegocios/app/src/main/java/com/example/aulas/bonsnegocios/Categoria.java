package com.example.aulas.bonsnegocios;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Categoria extends AppCompatActivity {

   /* private ListView categoriaListView;
    //  static public List<Grupo> lsGrupos;
    private Button btnNovo;
    private ArrayAdapter<Grupo> adpGrupo;
    private GrupoDAO DAO = new GrupoDAO();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    }

}
