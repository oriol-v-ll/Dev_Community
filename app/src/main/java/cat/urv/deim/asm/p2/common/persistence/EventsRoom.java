package cat.urv.deim.asm.p2.common.persistence;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import cat.urv.deim.asm.p3.shared.ui.events.EventsVo;

@Entity(tableName = "events")
public class EventsRoom implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "eventos")
    private String events;


    public int getID() {
        return ID;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getEvents() {
        return events;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public EventsRoom(String events) {
        this.events = events;
    }
}
