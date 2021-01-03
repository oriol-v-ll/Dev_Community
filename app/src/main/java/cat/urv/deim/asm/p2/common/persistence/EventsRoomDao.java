package cat.urv.deim.asm.p2.common.persistence;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import cat.urv.deim.asm.p3.shared.ui.events.EventsVo;

@Dao
public interface EventsRoomDao {

    @Query("SELECT * FROM events")
    List<EventsRoom> getAllEvents();

    @Insert
    void insertEventsOffline(EventsRoom ... events);


}
