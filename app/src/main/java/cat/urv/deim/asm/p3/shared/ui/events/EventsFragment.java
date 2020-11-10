package cat.urv.deim.asm.p3.shared.ui.events;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

import static android.app.ProgressDialog.show;


public class EventsFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static EventsFragment mInstance;
    ArrayList<EventsVo> listaEvents;
    RecyclerView recyclerEvents;

    private  RequestQueue requestQueue;
    Activity activity;
    ICommunicateFragments interfaceCommunicateFragements;
    private Context applicationContext;


    /*MÉTODOS PARA HACER LA DESCARGA DE LOS FICHEROS POSIBLE*/
    public static synchronized EventsFragment getInstance()
    {
        return mInstance;
    }
    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null)
            requestQueue= Volley.newRequestQueue(activity.getApplicationContext());

        return requestQueue;
    }
    public void addToRequestQueue(Request request,String tag)
    {
        request.setTag(tag);
        getRequestQueue().add(request);

    }
    public void cancelAllRequests(String tag)
    {
        getRequestQueue().cancelAll(tag);
    }
    /*MÉTODOS PARA HACER LA DESCARGA DE LOS FICHEROS POSIBLE*/


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

    private void pullEventsList() {

        //Aqui se tiene que descargar la información con el volley
        String url = "https://api.gdgtarragona.net/api/json/events";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Failure Callback
                    }
                });



// Adding the request to the queue along with a unique string tag

        EventsFragment.getInstance().addToRequestQueue(jsonObjReq,"headerRequest");

        DataProvider  dataProvider;
        //Pregunta -> Aquí solo se pueden pasar los eventos, los otros parametros los pillara del raw de momento.
        dataProvider = DataProvider.getInstance(this.getActivity().getApplicationContext(),R.raw.faqs,R.raw.news/*PASAR EVENTS DESCARGADOS*/,R.raw.articles,R.raw.events,R.raw.calendar);

        final List<Event> event = dataProvider.getEvents();

        String tags="";
        for (int i=0; event.size() > i;i++){
            String title = event.get(i).getName();

            String descripl = event.get(i).getDescription();
            String tipus = event.get(i).getType();


            tags = "";
            for(Tag tag:event.get(i).getTags()){
                tags = tags +", "+tag.getName();

            }

            String imageURL = dataProvider.getEvents().get(i).getImageURL();

            tags=tags.substring(1,tags.length());

            listaEvents.add(new EventsVo(title,tipus,imageURL,tags,descripl));

        }
    }

    /** Passing some request headers* */

    public Map getHeaders() throws AuthFailureError {
        HashMap headers = new HashMap();
        //Obtener los datos de credentials.json
        headers.put("mail", "xxxxxxx");
        headers.put("username", "xxxxxxxxxxxxxxx");
        headers.put("token", "xxxxxxxxxxxxxxx");
        return headers;
    }

    public void onAttach (Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity = (Activity) context;
            interfaceCommunicateFragements = (ICommunicateFragments) this.activity;
        }
    }

}