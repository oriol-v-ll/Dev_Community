package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import cat.urv.deim.asm.p2.common.ui.articles.ArticlesFragment;
import cat.urv.deim.asm.p2.common.ui.bookmarks.BookmarksFragment;
import cat.urv.deim.asm.p2.common.ui.calendar.CalendarFragment;
import cat.urv.deim.asm.p2.common.ui.events.EventsFragment;
import cat.urv.deim.asm.p2.common.ui.favorites.FavoritesFragment;
import cat.urv.deim.asm.p2.common.ui.news.NewsFragment;

public class ProfileActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);

    }

}

