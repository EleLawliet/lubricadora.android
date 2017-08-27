package com.grupopulpo.lubriacadora.duty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.grupopulpo.lubriacadora.duty.finals.ConfigEssentials;
import com.grupopulpo.lubriacadora.duty.fragments.ProfileFragment;
import com.grupopulpo.lubriacadora.duty.fragments.TipsFragment;
import com.grupopulpo.lubriacadora.duty.fragments.VehiculosFragment;
import com.grupopulpo.lubriacadora.duty.tools.SirSessionManager;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MenuActivity extends com.grupopulpo.lubriacadora.duty.utils.BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private CoordinatorLayout rootView = null;
    private VehiculosFragment vehiculosFragment = null;
    private TipsFragment tipsFragment = null;
    private ProfileFragment profileFragment = null;
    private FragmentManager fragmentManager = null;
    SirSessionManager mySesionCeden = null;
    Intent intent = getIntent();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle parametros = new Bundle();
        parametros.putInt("layout", R.layout.activity_menu_activitty);
        super.onCreate(parametros);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        rootView = (CoordinatorLayout) findViewById(R.id.cl_root_view);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        fragmentManager = getSupportFragmentManager();
        if (vehiculosFragment == null) {
            vehiculosFragment = new VehiculosFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.content_frame, vehiculosFragment).commit();

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activitty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        fragmentManager = getSupportFragmentManager();
        if(mySesionCeden==null){
            mySesionCeden = new SirSessionManager(this);
        }
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if (vehiculosFragment == null) {
                vehiculosFragment = new VehiculosFragment();
            }
            fragmentManager.beginTransaction().replace(R.id.content_frame, vehiculosFragment).commit();

        } else if (id == R.id.nav_tips) {
            if (tipsFragment == null) {
                tipsFragment = new TipsFragment();
            }
            fragmentManager.beginTransaction().replace(R.id.content_frame, tipsFragment).commit();

        } else if (id == R.id.nav_nosotros) {
            if (profileFragment == null) {
                profileFragment = new ProfileFragment();
            }
            fragmentManager.beginTransaction().replace(R.id.content_frame, profileFragment).commit();

        } else if (id == R.id.nav_share) {
            mySesionCeden.removercredenciales();
            this.finish();
            goToLogin();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void goToLogin() {
        Intent goToLogin = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(goToLogin);
    }


    public void mensajeSnack(String msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MenuActivity Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void actionLogout() {
        if (MenuActivity.this.getPreferenciacb().isLoggedIn()) {
            MenuActivity.this.getPreferenciacb().clearCredentials();
        }
    }

    public void actionLogoutByMessageApi(String strMessage) {
        if (strMessage.trim().equals(ConfigEssentials.MESSAGE_LOGOUT_API)) {
            onConnectionFinished();
            mySesionCeden.removercredenciales();
            this.finish();
            goToLogin();
            new SweetAlertDialog(MenuActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("season low")
                    .setContentText("BLABLA")
                    .show();
        } else {
        }
    }




}
