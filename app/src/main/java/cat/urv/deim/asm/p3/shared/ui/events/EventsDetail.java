package cat.urv.deim.asm.p3.shared.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import cat.urv.deim.asm.p2.common.R;

public class EventsDetail extends Fragment {

    TextView textTitle;
    TextView textSite;
    TextView textDescription;
    ImageView imageDetail;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_events_detail,container,false);

        textTitle=view.findViewById(R.id.titleId);
        textSite=view.findViewById(R.id.placeId);
        textDescription=view.findViewById(R.id.infoId);
        imageDetail=view.findViewById(R.id.imageDetailId);

        Bundle objectEvent = getArgument();
        EventsVo event = null;

        if (objectEvent != null){
            event = (EventsVo) objectEvent.getSerializable("object");
            //imageDetail.setImageResource(event.getImageDetail());
            textDescription.setText(event.getInfoDetail());
            textTitle.setText(event.getName());
            textSite.setText(event.getDesc());
        }

        return view;
    }

    private Bundle argument;

    public void setArgument(Bundle argument) {
        this.argument = argument;
    }

    public Bundle getArgument() {
        return argument;
    }
}

