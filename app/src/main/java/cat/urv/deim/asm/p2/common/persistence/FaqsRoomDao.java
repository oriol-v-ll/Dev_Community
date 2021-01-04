package cat.urv.deim.asm.p2.common.persistence;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;


@Dao
public interface FaqsRoomDao {

    @Query("SELECT * FROM faqs")
    List<FaqsRoom> getAllFaqs();

    @Insert
    void insertFaqsOffline(FaqsRoom ... faqs);




}
