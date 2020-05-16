package cat.urv.deim.asm.p3.shared.ui.events;


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
            }
        });

        return view;
    }

    private void pullEventsList() {
        listaEvents.add(new EventsVo("Uri", "Seras cerdo", R.drawable.error_l, "En mi casa"));
        listaEvents.add(new EventsVo("Juanma", "Seras crack", R.drawable.ic_menu_camera, "En su casa"));
        listaEvents.add(new EventsVo("Jeejje", "Seras cerdo", R.drawable.error_l, "En la de tu mami"));
        listaEvents.add(new EventsVo("Uri", "Seras cerdo", R.drawable.error_l, "En la tuya"));
        listaEvents.add(new EventsVo("Jamon Jamon", "Seras cerdo", R.drawable.error_l, "En mi casa"));
        listaEvents.add(new EventsVo("Jeejje", "Seras cerdo", R.drawable.error_l, "En mi casa"));
        listaEvents.add(new EventsVo("Jeejje", "Seras cerdo", R.drawable.error_l, "En mi huevo"));
        listaEvents.add(new EventsVo("Jeejje", "Seras cerdo", R.drawable.error_l, "En la casa de Dios"));
        listaEvents.add(new EventsVo("Jeejje", "Seras cerdo", R.drawable.error_l, "En mi casa"));
    }
}