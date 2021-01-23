package cat.urv.deim.asm.p2.common.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EventsRoom.class,FaqsRoom.class, NewsRoom.class, CalendarRoom.class, ArticlesRoom.class}, version = 3)
public abstract class RoomDB extends RoomDatabase {

    public abstract EventsRoomDao EventsRoomDao();
    public abstract FaqsRoomDao FaqsRoomDao();
    public abstract NewsRoomDAO NewsRoomDAO();
    public abstract CalendarRoomDAO CalendarRoomDAO();
    public abstract articlesRoomDAO ArticlesRoomDAO();
    private static RoomDB INSTANCE;

    /**
     * Metodo para referenciar siempre la misma base de datos desde todos los sitios dónde se pida.
     * @param context
     * @return
     */
    public static RoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, "Dev_community_DB").allowMainThreadQueries()
                            .fallbackToDestructiveMigration() //Necesario para cambiar de version y añadir entidades a la base de datos.
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
