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

import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;

import java.util.ArrayList;
import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

import static android.app.ProgressDialog.show;


public class EventsFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();

    ArrayList<EventsVo> listaEvents;
    RecyclerView recyclerEvents;


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

    private void pullEventsList() {

        DataProvider  dataProvider;
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

    public void onAttach (Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity = (Activity) context;
            interfaceCommunicateFragements = (ICommunicateFragments) this.activity;
        }
    }

}