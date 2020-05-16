package cat.urv.deim.asm.p3.shared.ui.events;


import android.app.Activity;
import android.content.Context;
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

import cat.urv.deim.asm.p2.common.R;

import static android.app.ProgressDialog.show;


public class EventsFragment extends Fragment {

    ArrayList<EventsVo> listaEvents;
    RecyclerView recyclerEvents;

    Activity activity;
    ICommunicateFragments interfaceCommunicateFragements;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_events,container, false);

        listaEvents = new ArrayList<>();
        recyclerEvents= (RecyclerView) view.findViewById(R.id.recyclerId);
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

                interfaceCommunicateFragements.sendEvent(listaEvents.get
                        (recyclerEvents.getChildAdapterPosition(view)));
            }
        });

        return view;
    }

    private void pullEventsList() {
        listaEvents.add(new EventsVo("Uri", "Seras cerdo", R.drawable.error_l,
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
                "En mi casa", "ESTA ES LA LARGA",R.drawable.profile_user));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity= (Activity) context;
            interfaceCommunicateFragements= (ICommunicateFragments) this.activity;
        }
    }
}