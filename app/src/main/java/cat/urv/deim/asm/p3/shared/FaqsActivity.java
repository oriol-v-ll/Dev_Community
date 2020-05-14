package cat.urv.deim.asm.p3.shared;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

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

        chapterList.add("Chapter 1");
        chapterList.add("Chapter 2");
        chapterList.add("Chapter 3");
        chapterList.add("Chapter 4");
        chapterList.add("Chapter 5");

        List<String> topic1 = new ArrayList<>();
        topic1.add("Topic1");
        topic1.add("Topic2");
        topic1.add("Topic3");

        List<String> topic2 = new ArrayList<>();
        topic2.add("Topic1");
        topic2.add("Topic2");
        topic2.add("Topic3");

        List<String> topic3 = new ArrayList<>();
        topic3.add("Topic1");
        topic3.add("Topic2");
        topic3.add("Topic3");

        List<String> topic4 = new ArrayList<>();
        topic4.add("Topic1");
        topic4.add("Topic2");
        topic4.add("Topic3");

        List<String> topic5 = new ArrayList<>();
        topic5.add("Topic1");
        topic5.add("Topic2");
        topic5.add("Topic3");


        topicList.put(chapterList.get(0),topic1);
        topicList.put(chapterList.get(1),topic2);
        topicList.put(chapterList.get(2),topic3);
        topicList.put(chapterList.get(3),topic4);
        topicList.put(chapterList.get(4),topic5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Falta inflater de menú.
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_no_search, menu);
        return true;
    }
}
