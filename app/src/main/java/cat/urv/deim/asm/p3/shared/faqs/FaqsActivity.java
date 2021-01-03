package cat.urv.deim.asm.p3.shared.faqs;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.libraries.commanagerdc.models.CalendarItem;
import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.models.Events;
import cat.urv.deim.asm.libraries.commanagerdc.models.Faq;
import cat.urv.deim.asm.libraries.commanagerdc.models.Faqs;
import cat.urv.deim.asm.libraries.commanagerdc.models.New;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.ErrorActivity;
import cat.urv.deim.asm.p2.common.R;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.persistence.EventsRoom;
import cat.urv.deim.asm.p2.common.persistence.EventsRoomDao;
import cat.urv.deim.asm.p2.common.persistence.RoomDB;
import cat.urv.deim.asm.p2.common.utils.NetworkUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


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


        //Implementar clases con el broadcast receiver.
        chapterList = new ArrayList<String>();
        topicList = new HashMap<String, List<String>>();

        //Detectamos si hay conexión en el dispositivo para poder coger los datos de la capa de persistencia
        if (NetworkUtils.isConnected(FaqsActivity.this)) {

            //La información se ha descargado previamente de volley en el MainActivity
            Gson gson = new Gson();
            String faqs = MainActivity.getFaqs();
            Faqs data = (Faqs) gson.fromJson(faqs, Faqs.class);
            final List<Faq> listaFaqs = data.getFaqs();

            for (int i=0; listaFaqs.size() > i;i++){

                chapterList.add(listaFaqs.get(i).getTitle());
                List<String> topic = new ArrayList<>();
                topic.add(listaFaqs.get(i).getBody());

                topicList.put(chapterList.get(i),topic);
            }
        }else{
            //Buscamos la información de la base de datos y si no se puede
            RoomDB db = RoomDB.getDatabase(FaqsActivity.this);
            EventsRoomDao mDao = db.EventsRoomDao();
            List<EventsRoom> faqsOffline;
            faqsOffline =  mDao.getAllEvents();
            if (faqsOffline.size()==0){
                Intent faqs = new Intent(FaqsActivity.this, ErrorActivity.class);
                startActivity(faqs);
                finish();
            }else{
                //Guardar los datos de persistencia de los faqs y ponerlos aquí.


            }

        }






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_no_search, menu);
        return true;
    }

}
