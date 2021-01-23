package cat.urv.deim.asm.p2.common.persistence;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import cat.urv.deim.asm.p3.shared.ui.events.EventsVo;

@Dao
public interface articlesRoomDAO {
/*
    @Query("SELECT * FROM articles")
    List<ArticlesRoom> getAllArticles();
*/
    @Insert
    void insertArticlesOffline(ArticlesRoom ... articles);




}