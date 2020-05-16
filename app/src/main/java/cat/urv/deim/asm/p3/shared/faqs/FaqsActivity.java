package cat.urv.deim.asm.p3.shared.faqs;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cat.urv.deim.asm.p2.common.R;
import cat.urv.deim.asm.p2.common.MainActivity;

public class FaqsActivity extends MainActivity {

    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> chapterList;
    HashMap<String, List<String>> topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);


        /*Creacio d'adapter*/
        expandableListView = findViewById(R.id.eListView);
        showList();

        listViewAdapter = new ExpandableListViewAdapter(this, chapterList, topicList);
        expandableListView.setAdapter(listViewAdapter);




        Toolbar toolbar = findViewById(R.id.toolbar_faqs);
        setSupportActionBar(toolbar);



        //El metodo setDiplayHomeUpEnabled pone la flecha pero al hacer click lo quita de cualquier elemento UI.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Ponemos un listener en el objeto de la flecha hacia atras para que vaya un paso atras
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                Intent intent = new Intent(FaqsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showList() {

        chapterList = new ArrayList<String>();
        topicList = new HashMap<String, List<String>>();

        chapterList.add("Pregunta 1");
        chapterList.add("Pregunta 2");
        chapterList.add("Pregunta 3");
        chapterList.add("Pregunta 4");
        chapterList.add("Pregunta 5");
        chapterList.add("Pregunta 6");

        List<String> topic1 = new ArrayList<>();
        topic1.add("Lorem ipsum dolor sit amet, sapien etiam, nunc amet dolor ac odio mauris justo." +
                " Luctus arcu, urna praesent at id quisque ac. Arcu es massa vestibulum maleusada," +
                " integer vivamus elit eu mauris eus cum eros quis aliquam wisi. Nulla wisi laoreet" +
                " suspendisse integer vivamus elit eu mauris");

        List<String> topic2 = new ArrayList<>();
        topic2.add("Uri nos vamos paeeencima papiii!");

        List<String> topic3 = new ArrayList<>();
        topic3.add("La movie en play ya no estamos en novela. Bby dale suave cuando baje que yo " +
                "quiero ver ese tatuaje.");

        List<String> topic4 = new ArrayList<>();
        topic4.add("YongKingz bby eso no lo hiso Juan Salgado. Sol en el bote y dinero en el tuvo." +
                " Kevvo aunque tenga jevo yo me atrevo huele bicho cria cuervo.");


        List<String> topic5 = new ArrayList<>();
        topic5.add("Somos reales aqui no hay phantom. Diabla per tiene un bury sagrado");

        List<String> topic6 = new ArrayList<>();
        topic6.add("Dise que es tranquila pero se las trae. Yo soy como el fly, 15 temporadas sin " +
                "strike. Pq mami yo quiero la combi completa.");



        topicList.put(chapterList.get(0),topic1);
        topicList.put(chapterList.get(1),topic2);
        topicList.put(chapterList.get(2),topic3);
        topicList.put(chapterList.get(3),topic4);
        topicList.put(chapterList.get(4),topic5);
        topicList.put(chapterList.get(5),topic6);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Falta inflater de menú.
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_no_search, menu);
        return true;
    }
}
