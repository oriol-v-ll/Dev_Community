package cat.urv.deim.asm.p2.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.content.SharedPreferences;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;

import cat.urv.deim.asm.p2.common.ui.articles.ArticlesFragment;
import cat.urv.deim.asm.p2.common.ui.bookmarks.BookmarksFragment;
import cat.urv.deim.asm.p2.common.ui.calendar.CalendarFragment;
import cat.urv.deim.asm.p2.common.ui.events.EventsFragment;
import cat.urv.deim.asm.p2.common.ui.favorites.FavoritesFragment;
import cat.urv.deim.asm.p2.common.ui.news.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cargamos las shared preference
        pref = getApplicationContext().getSharedPreferences("MyPref",
                0);

        boolean anonym = pref.getBoolean("anonym", true);

        //cuando es true es que es anonimo
        if (anonym){

            setContentView(R.layout.activity_main_anonym);

        }

        else{ //Metodo registrado.

            setContentView(R.layout.activity_main_);

        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //seleciona la vista del menú lateral.
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_news, R.id.nav_articles, R.id.nav_events,
                R.id.nav_calendar)
                .setDrawerLayout(drawer)
                .build();
        //Se pone los fragments correspondientes.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*---------Control del botón que se haga en el menú---------------*/

        navigationView.setNavigationItemSelectedListener( //Ponemos un listener en el navigationview
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        //
                        switch (menuItem.getItemId()) {
                            case R.id.nav_profile:
                                Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                                startActivity(profile);
                                finish();
                                break;
                            case R.id.nav_news:
                                fragment = new NewsFragment();
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_articles:
                                fragment = new ArticlesFragment();
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_events:
                                fragment = new EventsFragment();
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_calendar:
                                fragment = new CalendarFragment();
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_faqs:
                                Intent faqs = new Intent(MainActivity.this, FaqsActivity.class);
                                startActivity(faqs);
                                finish();
                                break;
                            case R.id.nav_favorites:
                                fragment = new FavoritesFragment();
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_booksmarks:
                                fragment = new BookmarksFragment();
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_settings:
                                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(settings);
                                finish();
                                break;
                        }

                        if(fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.nav_host_fragment, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawer.closeDrawers();

                        return true;
                    }
                });
        /*---------Fin control del botón que se haga en el menú---------------*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Falta inflater de menú.
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}
