package cat.urv.deim.asm.p3.shared.ui.events;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;
import cat.urv.deim.asm.p3.shared.EventsDetailActivity;

import static android.app.ProgressDialog.show;


public class EventsFragment extends Fragment {

    ArrayList<EventsVo> listaEvents;
    RecyclerView recyclerEvents;

    private static final String TAG = MainActivity.class.getSimpleName();

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
                Toast.makeText(getContext(), "Select: "
                        +listaEvents.get(recyclerEvents.getChildAdapterPosition(view)).getName(),
                        Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getActivity(), EventsDetailActivity.class);
                startActivity(intent);


            }
        });

        return view;
    }

    private void pullEventsList() {

        DataProvider  dataProvider;
        dataProvider = DataProvider.getInstance(this.getApplicationContext(),R.raw.faqs,R.raw.news,R.raw.articles,R.raw.events,R.raw.calendar);

        final List<Event> event = dataProvider.getEvents();

        for (int i=0; event.size() > i;i++){
            String title = event.get(i).getName();
            String image = event.get(i).getImageURL();
            String descripc = event.get(i).getDescription();
            String tipus = event.get(i).getType();
            String descripl = "Esta aun no esta";
            int image2 = R.drawable.profile_user; // de momento ponemos las dos igual

            listaEvents.add(new EventsVo(title,descripc,image,tipus,descripl,image2));
        }

        /** listaEvents.add(new EventsVo("Uri", "Seras cerdo", R.drawable.error_l,
                "En mi casa", "ESTA ES LA LARGA",R.drawable.profile_user));

        listaEvents.add(new EventsVo("Juan", "", R.drawable.ic_menu_gallery,
                "En la casa", "ESTA ES LA CORTA",R.drawable.profile_user));

        listaEvents.add(new EventsVo("Uri", "Seras cerdo", R.drawable.error_l,
                "En mi casa", "ESTA ES LA LARGA",R.drawable.profile_user));

        listaEvents.add(new EventsVo("Tomas", "Seras pepo", R.drawable.error_l,
                "En su casa", "ESTA ES LA Corta",R.drawable.profile_user));

        listaEvents.add(new EventsVo("Uri", "Seras cerdo", R.drawable.error_l,
                "En mi casa", "ESTA ES LA LARGA",R.drawable.profile_user));

        listaEvents.add(new EventsVo("Uri", "i", R.drawable.error_l,
                "En mi casa", "ESTA ES LA LARGA",R.drawable.profile_user));

        listaEvents.add(new EventsVo("h", "Seras cerdo", R.drawable.error_l,
                "En mi casa", "ESTA ES LA LARGA",R.drawable.profile_user));

        listaEvents.add(new EventsVo("Uri", "Seras cerdo", R.drawable.error_l,
                "En mi casa", "ESTA ES LA LARGA",R.drawable.profile_user));

        listaEvents.add(new EventsVo("FATIMA", "Seras PIPI", R.drawable.error_l,
                "En mi casa", "ESTA ES LA LARGA",R.drawable.profile_user)); **/
    }
    

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity= (Activity) context;
            interfaceCommunicateFragements= (ICommunicateFragments) this.activity;
        }
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }
}