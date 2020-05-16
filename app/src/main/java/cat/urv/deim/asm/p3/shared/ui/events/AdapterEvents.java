package cat.urv.deim.asm.p3.shared.ui.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import cat.urv.deim.asm.p2.common.R;

public class AdapterEvents extends
        RecyclerView.Adapter <AdapterEvents.EventsViewHolder> implements View.OnClickListener{

    ArrayList<EventsVo> listaEvents;
    private View.OnClickListener listener;

    public AdapterEvents(ArrayList<EventsVo> listaEvents) {
        this.listaEvents = listaEvents;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_list,null,false);
        view.setOnClickListener(this);

        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.txtName.setText(listaEvents.get(position).getName());
        holder.txtInfo.setText(listaEvents.get(position).getInfo());
        holder.foto.setImageResource(listaEvents.get(position).getImageId());
        holder.txtDesc.setText(listaEvents.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return listaEvents.size();
    }


    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder{

        TextView txtName, txtInfo, txtDesc;
        ImageView foto;

        public EventsViewHolder(View itemView) {
            super(itemView);
            txtName= (TextView) itemView.findViewById(R.id.idName);
            txtInfo= (TextView) itemView.findViewById(R.id.idInfo);
            foto= (ImageView) itemView.findViewById(R.id.idImageEvent);
            txtDesc= (TextView) itemView.findViewById(R.id.idDesc);
        }
    }
}
