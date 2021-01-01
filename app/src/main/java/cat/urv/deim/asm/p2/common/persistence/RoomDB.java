package cat.urv.deim.asm.p2.common.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EventsRoom.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {
    public abstract EventsRoomDao EventsRoomDao();
}
