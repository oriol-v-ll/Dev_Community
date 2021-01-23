package cat.urv.deim.asm.p2.common.persistence;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;


@Entity(tableName = "calendar")
public class CalendarRoom implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "calendar")
    private String calendar;


    public int getID() {
        return ID;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public CalendarRoom(String calendar) {
        this.calendar = calendar;
    }


}