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

    /*
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

     */
}
