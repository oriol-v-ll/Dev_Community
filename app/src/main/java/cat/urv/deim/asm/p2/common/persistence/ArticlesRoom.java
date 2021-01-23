package cat.urv.deim.asm.p2.common.persistence;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;


@Entity(tableName = "articles")
public class ArticlesRoom implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "articles")
    private String articles;


    public int getID() {
        return ID;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public String getArticles() {
        return articles;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArticlesRoom(String articles) {
        this.articles = articles;
    }


}