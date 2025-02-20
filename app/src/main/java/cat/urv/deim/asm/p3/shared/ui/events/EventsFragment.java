package cat.urv.deim.asm.p3.shared.ui.events;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import cat.urv.deim.asm.libraries.commanagerdc.models.Events;
import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.libraries.commanagerdc.utils.Utils;
import cat.urv.deim.asm.p2.common.Credentials;
import cat.urv.deim.asm.p2.common.JSONResourceReader;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

import static android.app.ProgressDialog.show;


public class EventsFragment extends Fragment {
    BroadcastReceiver broadcastReceiver;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static EventsFragment mInstance;
    ArrayList<EventsVo> listaEvents;
    RecyclerView recyclerEvents;
    private String eventos = "";

    private  RequestQueue requestQueue;
    Activity activity;
    ICommunicateFragments interfaceCommunicateFragements;
    private Context applicationContext;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_events,container, false);
        listaEvents = new ArrayList<>();
        recyclerEvents=  view.findViewById(R.id.recyclerId);
        recyclerEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        pullEventsList();
        AdapterEvents adapter = new AdapterEvents(listaEvents);
        recyclerEvents.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceCommunicateFragements.sendEvent(listaEvents.get(recyclerEvents.getChildAdapterPosition(view)));

            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!=null){
            eventos = getArguments().getString("Events","0");
            Log.e("Eventos_fragment", eventos);
        }
    }

    private void pullEventsList()  {

        //Hacer que cargue mis eventos.
        Gson gson = new Gson();
        Events data = (Events)gson.fromJson(eventos, Events.class);
        final List<Event> event = data.getEvents();


        String tags="";
        for (int i=0; event.size() > i;i++){
            String title = event.get(i).getName();

            String descripl = event.get(i).getDescription();
            String tipus = event.get(i).getType();


            tags = "";
            for(Tag tag:event.get(i).getTags()){
                tags = tags +", "+tag.getName();

            }

            String imageURL = data.getEvents().get(i).getImageURL();

            tags=tags.substring(1,tags.length());

            listaEvents.add(new EventsVo(title,tipus,imageURL,tags,descripl));

        }
    }

    public void onAttach (Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity = (Activity) context;
            interfaceCommunicateFragements = (ICommunicateFragments) this.activity;
        }
    }

}