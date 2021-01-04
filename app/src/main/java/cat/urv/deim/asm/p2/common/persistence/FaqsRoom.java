package cat.urv.deim.asm.p2.common.persistence;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "faqs")
public class FaqsRoom implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name="faqs")
    private String faqs;


    public int getID() {
        return ID;
    }

    public void setFaqs(String faqs) {
        this.faqs = faqs;
    }

    public String getFaqs() {
        return faqs;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public FaqsRoom(String faqs) {
        this.faqs = faqs;
    }




}
