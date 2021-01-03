package cat.urv.deim.asm.p2.common.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EventsRoom.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {
    public abstract EventsRoomDao EventsRoomDao();

    private static RoomDB INSTANCE;

    public static RoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, "Dev_community_DB").allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
