package cat.urv.deim.asm.p2.common.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import cat.urv.deim.asm.p3.shared.ui.events.EventsVo;

@Entity(tableName = "news")
public class NewsRoom implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "news")
    private String news;


    public int getID() {
        return ID;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getNews() {
        return news;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public NewsRoom(String news) {
        this.news = news;
    }




}
