package cat.urv.deim.asm.p2.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;

import android.util.JsonReader;
import android.util.Log;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cat.urv.deim.asm.p3.shared.faqs.FaqsActivity;
import cat.urv.deim.asm.p2.common.ui.articles.ArticlesFragment;
import cat.urv.deim.asm.p2.common.ui.bookmarks.BookmarksFragment;
import cat.urv.deim.asm.p2.common.ui.calendar.CalendarFragment;
import cat.urv.deim.asm.p3.shared.ui.events.EventsDetail;
import cat.urv.deim.asm.p3.shared.ui.events.EventsFragment;
import cat.urv.deim.asm.p3.shared.ui.events.EventsVo;
import cat.urv.deim.asm.p3.shared.ui.events.ICommunicateFragments;
import cat.urv.deim.asm.p2.common.ui.favorites.FavoritesFragment;
import cat.urv.deim.asm.p2.common.ui.news.NewsFragment;


import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;

public class MainActivity extends AppCompatActivity implements ICommunicateFragments {

    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences pref;


    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Cargamos los datos de las librerias que nos hagan falta

        DataProvider dataProvider;
        dataProvider = DataProvider.getInstance(this.getApplicationContext(),R.raw.faqs,R.raw.news,R.raw.articles,R.raw.events,R.raw.calendar);
        DataProvider.generateMockJsonStr(this);



        //cargamos las shared preference
        pref = getApplicationContext().getSharedPreferences("MyPref",
                0);

        boolean anonym = pref.getBoolean("anonym", true);

        //cuando es true es que es anonimo
        if (anonym){
            setContentView(R.layout.activity_main_anonym);
        } else{ //Metodo registrado.
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
                        /*Obtención de las credenciales para acceder a la información*/
                        String credentials = "";

                        JSONResourceReader reader = new JSONResourceReader(getResources(), R.raw.credentials);
                        Credentials credenciales = reader.constructUsingGson(Credentials.class);
                        Log.d("Credentials", credenciales.toString());


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
                                    .replace(R.id.nav_host_fragment, fragment).commit();
                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawer.closeDrawers();
                        return true;
                    }
                });
        /*---------Fin control del botón que se haga en el menú---------------*/
    }

    public String loadJSONFromAsset(String flName) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(flName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.v("MainActivity", "Load json ok");
        } catch (IOException ex) {
            Log.v("MainActivity", "Error: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return json;
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


    @Override
    public void sendEvent(EventsVo event) {
        EventsDetail detailEvent = new EventsDetail();
        Bundle bundleSend = new Bundle();
        bundleSend.putSerializable("object",event);
        detailEvent.setArgument(bundleSend);

        //cargar fragment en la activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment,detailEvent).
                addToBackStack(null).commit();
    }
}
