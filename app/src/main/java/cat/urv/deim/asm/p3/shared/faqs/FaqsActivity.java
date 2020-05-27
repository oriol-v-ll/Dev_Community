package cat.urv.deim.asm.p3.shared.faqs;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.libraries.commanagerdc.models.CalendarItem;
import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.models.Faq;
import cat.urv.deim.asm.libraries.commanagerdc.models.New;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
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

        DataProvider dataProvider;
        dataProvider = DataProvider.getInstance(this.getApplicationContext(),R.raw.faqs,R.raw.news,R.raw.articles,R.raw.events,R.raw.calendar);
        DataProvider.generateMockJsonStr(this);

        final List<Faq> faqs = dataProvider.getFaqs();


        for (int i=0; faqs.size() > i;i++){

            chapterList.add(faqs.get(i).getTitle());
            List<String> topic = new ArrayList<>();
            topic.add(faqs.get(i).getBody());

            topicList.put(chapterList.get(i),topic);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_no_search, menu);
        return true;
    }

}
