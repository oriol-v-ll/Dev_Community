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

import org.json.JSONException;
import org.json.JSONObject;

import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.Credentials;
import cat.urv.deim.asm.p2.common.JSONResourceReader;
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

    private void pullEventsList()  {

        //Aqui se tiene que descargar la información con el volley
         final Context j = (Context)this.getActivity();
        String url = "https://api.gdgtarragona.net/api/json/events";
        RequestQueue queue = Volley.newRequestQueue(this.activity);
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        Intent intent = new Intent();
                        intent.setAction("cat.urv.deim.padm.demo.MY_NOTIFICATION");
                        intent.putExtra("data",response);
                        j.sendBroadcast(intent);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                //Descargar de credentials.json, esto no se puede dejar asi.
                JSONResourceReader reader = new JSONResourceReader(getResources(), R.raw.credentials);
                Credentials credenciales = reader.constructUsingGson(Credentials.class);
                Log.d("Credentials", credenciales.toString());

                params.put("mail", credenciales.getMail());
                params.put("username", credenciales.getUsername());
                params.put("token", credenciales.getToken());

                return params;
            }
        };
        queue.add(getRequest);

        DataProvider  dataProvider;
        //Pregunta -> Aquí solo se pueden pasar los eventos, los otros parametros los pillara del raw de momento.
        dataProvider = DataProvider.getInstance(this.getActivity().getApplicationContext(),R.raw.faqs,R.raw.news,R.raw.articles,R.raw.events,R.raw.calendar);

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

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String receivedText = intent.getStringExtra("data");
            Log.d("Response", receivedText);
        }
    };



    public void onAttach (Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity = (Activity) context;
            interfaceCommunicateFragements = (ICommunicateFragments) this.activity;
        }
    }

}