package cat.urv.deim.asm.p2.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.Menu;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.urv.deim.asm.p2.common.persistence.EventsRoom;
import cat.urv.deim.asm.p2.common.persistence.EventsRoomDao;
import cat.urv.deim.asm.p2.common.persistence.FaqsRoom;
import cat.urv.deim.asm.p2.common.persistence.FaqsRoomDao;
import cat.urv.deim.asm.p2.common.persistence.RoomDB;
import cat.urv.deim.asm.p2.common.utils.NetworkUtils;
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
import cat.urv.deim.asm.p2.common.listener.ErrorListener;
import cat.urv.deim.asm.p2.common.listener.SuccessResponse;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;

public class MainActivity extends AppCompatActivity implements ICommunicateFragments {
    BroadcastReceiver broadcastReceiver;
    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences pref;
    private String events;
    private String news;
    private String calendar;
    private static String faqs;
    private String articles;
    private EventsRoomDao mDao;
    private FaqsRoomDao  mFaqsDao;

    public static String getFaqs() {
        return faqs;
    }

    private static final String EVENTS_URL = "https://api.gdgtarragona.net/api/json/events";
    private static final String FAQS_URL = "https://api.gdgtarragona.net/api/json/faqs";
    private static final String NEWS_URL = "https://api.gdgtarragona.net/api/json/news";
    private static final String CALENDAR_URL = "https://api.gdgtarragona.net/api/json/calendar";
    private static final String ARTICLES_URL = "https://api.gdgtarragona.net/api/json/articles";
    private static final String VALIDATE_URL = "https://api.gdgtarragona.net/api/json/validate_user";
    private static final String TOKEN_URL = "https://api.gdgtarragona.net/api/json/obtain_token";
    private static final String NOTI_BROADCAST = "cat.urv.deim.asm.p2.MY_NOTIFICATION";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.broadcastReceiver = new UpdateUIBroadcastReceiver(this);

        //cargamos las shared preference
        pref = getApplicationContext().getSharedPreferences("MyPref",
                0);

        boolean anonym = pref.getBoolean("anonym", true);

        //cuando es true es que es anonimo
        if (anonym) {
            setContentView(R.layout.activity_main_anonym);
        } else { //Metodo registrado.
            setContentView(R.layout.activity_main_);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //seleciona la vista del menú lateral.
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_news, R.id.nav_articles, R.id.nav_events,
                R.id.nav_calendar)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*---------Control del botón que se haga en el menú---------------*/

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

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
                                // Mirar si hay conexión y resolver los problemas si no se puede descargar el codigo:
                                Bundle bundle = new Bundle();
                                fragment = new EventsFragment();
                                if (NetworkUtils.isConnected(MainActivity.this)) {
                                    bundle.putString("Events", events);
                                    //Transición de fragment con el bundle.
                                    try {
                                        fragment.setArguments(bundle);
                                        fragmentTransaction = true;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    RoomDB db = RoomDB.getDatabase(MainActivity.this);
                                    mDao = db.EventsRoomDao();
                                    List<EventsRoom> eventsOffline;
                                    eventsOffline =  mDao.getAllEvents();
                                    if (eventsOffline.size()==0){
                                        Intent eventos = new Intent(MainActivity.this, ErrorActivity.class);
                                        startActivity(eventos);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "No hay conexion... Se cargan datos locales", Toast.LENGTH_SHORT).show();
                                        bundle.putString("Events", eventsOffline.get(0).getEvents());
                                        try {
                                            fragment.setArguments(bundle);
                                            fragmentTransaction = true;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }


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

                        if (fragmentTransaction) {
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
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter("cat.urv.deim.asm.p2.FINISH_CALL_VOLLEY");
        this.registerReceiver(broadcastReceiver, filter);
        if (NetworkUtils.isConnected(this)) {
            descargaYBroadCasts(this, EVENTS_URL);
            descargaYBroadCasts(this, FAQS_URL);
            descargaYBroadCasts(this, NEWS_URL);
            try
            {
                Thread.sleep(1000);
            }catch(InterruptedException e){}
            descargaYBroadCasts(this, CALENDAR_URL);
            descargaYBroadCasts(this, ARTICLES_URL);

        } else {
            Log.e(TAG, "Device has not any type of internet connection");
        }

    }



    /**
     * Se pasa la url de que se quiere descargar y lo pone auntomatciamente en una variable.
     * @param context
     * @param url
     */
    public void descargaYBroadCasts(final Context context, String url) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new SuccessResponse(this), new ErrorListener(this)) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                JSONResourceReader reader = new JSONResourceReader(getResources(), R.raw.credentials);
                Credentials credenciales = reader.constructUsingGson(Credentials.class);
                String mail = credenciales.getMail();
                Log.d("Credentials", credenciales.toString());
                params.put("mail", credenciales.getMail());
                params.put("username", credenciales.getUsername());

                /*Validamos usuario*/
                boolean validado = validarUsuario(credenciales.getMail(), credenciales.getUsername(), credenciales.getPassword(), context);
                /*Obtenemos y modificamos token*/
                if (validado) {
                    String token = obtenerToken(credenciales.getMail(), credenciales.getUsername(), credenciales.getPassword());
                    params.put("token", token);
                } else {
                    params.put("token", credenciales.getToken());
                }

                return params;
            }
        };
        queue.add(stringRequest);

    }

    /**
     * Devuelve el token del usuario
     *
     * @param mail
     * @param username
     * @param password
     * @return token obtenido
     */
    public String obtenerToken(final String mail, final String username, final String password) {
        final String[] Token = {""};
        RequestQueue queue3 = Volley.newRequestQueue(this);
        StringRequest stringValidar = new StringRequest(Request.Method.GET, TOKEN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String token) {
                        Token[0] = token;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Error");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mail", mail);
                params.put("username", username);
                params.put("password", password);
                return params;
            }

        };
        queue3.add(stringValidar);
        return Token[0];
    }

    /**
     * Validar usuario, devuelve true si el usuario esta validado, false si no
     *
     * @param mail
     * @param username
     * @param password
     * @param context
     * @return
     */
    public boolean validarUsuario(final String mail, final String username, final String password, final Context context) {
        final boolean[] retorno = new boolean[1];
        RequestQueue queue2 = Volley.newRequestQueue(context);
        String urlValidar = "https://api.gdgtarragona.net/api/json/validate_user";
        StringRequest stringValidar = new StringRequest(Request.Method.GET, VALIDATE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String validado) {
                        Gson gson = new Gson();
                        ValidarUsuario validar = gson.fromJson(validado, ValidarUsuario.class);
                        if (validar.getMessage().equals("Mail is valid")) {
                            retorno[0] = true;
                        } else {
                            retorno[0] = false;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Error");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mail", mail);
                params.put("username", username);
                params.put("password", password);
                return params;
            }

        };
        queue2.add(stringValidar);
        return retorno[0];
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
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(broadcastReceiver);
    }

    /**
     * Función que llama el fragment de los eventos para poder realizar el cambio de archivos con el bundle.
     *
     * @param event
     */
    @Override
    public void sendEvent(EventsVo event) {
        EventsDetail detailEvent = new EventsDetail();
        Bundle bundleSend = new Bundle();
        bundleSend.putSerializable("object", event);
        //bundleSend.putString("Eventos", events);
        detailEvent.setArgument(bundleSend);

        //cargar fragment en la activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, detailEvent).
                addToBackStack(null).commit();
    }

    /**
     * Se guardan los datos obtenidos de los JSON descargados en las variables de la clase. Se guarda en su respectiva clase
     * de persistencia para poder obtenerlos en estado offline.
     *
     * @param datos
     * @throws JSONException
     */
    public void guardaDatos(String datos) throws JSONException {
        RoomDB db = RoomDB.getDatabase(MainActivity.this);
        mDao = db.EventsRoomDao();
        mFaqsDao = db.FaqsRoomDao();

        JSONObject object = new JSONObject(datos);
        //Los elementos descargados son alamacenados en las variables y en la base de datos.
        if (object.get("datatype").equals("events")){
            events = datos;
            EventsRoom eventsInsert = new EventsRoom(datos);
            mDao.insertEventsOffline(eventsInsert);
        }
        if (object.get("datatype").equals("news"))
            news = datos;
        if (object.get("datatype").equals("faqs")) {
            faqs = datos;
            FaqsRoom faqsInsert = new FaqsRoom(datos);
            mFaqsDao.insertFaqsOffline(faqsInsert);
        }
        if (object.get("datatype").equals("calendar")){
            calendar=datos;
        }
        if (object.get("datatype").equals("articles")){
            articles = datos;
        }

    }

    /**
     * Boradcast-receiver que recibe la información de las diferentes llamadas a la diferente información que necesitamos.
     */
    public class UpdateUIBroadcastReceiver extends BroadcastReceiver {
        public final static String UPDATED_DATA_KEY = "UPDATED_DATA_KEY";
        private final String TAG = UpdateUIBroadcastReceiver.class.getSimpleName();

        private MainActivity activity;

        UpdateUIBroadcastReceiver(AppCompatActivity activity) {
            this.activity = (MainActivity) activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String parameter = intent.getStringExtra(UPDATED_DATA_KEY);
            Log.e(TAG, parameter);
            try {
                guardaDatos(parameter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }





    }
}
