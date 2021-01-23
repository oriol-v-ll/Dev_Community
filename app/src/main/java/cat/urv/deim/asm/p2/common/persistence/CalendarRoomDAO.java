package cat.urv.deim.asm.p2.common.persistence;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import cat.urv.deim.asm.p3.shared.ui.events.EventsVo;

@Dao
public interface CalendarRoomDAO {
/*
    @Query("SELECT * FROM calendar")
    List<CalendarRoom> getAllCalendar();
*/
    @Insert
    void insertCalendarOffline(CalendarRoom ... news);




}