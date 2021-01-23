package cat.urv.deim.asm.p2.common.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;

import java.util.List;

import cat.urv.deim.asm.p3.shared.ui.events.EventsVo;

@Dao
public interface NewsRoomDAO {
/*
    @Query("SELECT * FROM news")
    List<NewsRoom> getAllNews();
*/
    @Insert
    void insertNewsOffline(NewsRoom ... news);




}