package pr.tongson.application;

import android.Manifest;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import pr.tongson.base.BaseActivity;
import pr.tongson.service.MyService;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fab.getVisibility() == View.VISIBLE) {
                    fab.hide();

                } else {
                    fab.show();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public static final String TAG = "Tongson";
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected:" + name);
            if (service instanceof MyService.DialogBinder) {
                MyService.DialogBinder dialogBinder = (MyService.DialogBinder) service;
                dialogBinder.showDialog(new Runnable() {
                    @Override
                    public void run() {
                        showMyDialog();
                    }
                });
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected:" + name);
        }
    };

    private void showMyDialog() {
        Log.i(TAG, "showMyDialog");
        AlertDialog dialog = new AlertDialog.
                Builder(this).
                setTitle("温馨提示").
                setMessage("这是一个由service调起的对话框。").
                setCancelable(false).
                setPositiveButton("了解了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.layout_dialog);
            TextView tipsTv = dialog.findViewById(R.id.tv_tips);
            tipsTv.setText("试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。");
            dialog.show();
        } else if (id == R.id.nav_gallery) {
            final String[] items = new String[]{"北京", "上海", "广州", "深圳"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher).setTitle("提示").setItems(items, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityForResult(intent, 1);
                }
            }
        } else if (id == R.id.nav_share) {
            String[] permissionArray = {Manifest.permission.READ_EXTERNAL_STORAGE};

            ActivityCompat.requestPermissions(this, permissionArray, 0);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(this, MyService.class);
            //        startService(intent);
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 0:
                Log.i(TAG, "grantResults:" + grantResults.length);
                Log.i(TAG, "grantResults:" + Arrays.toString(grantResults));
                break;
            default:
                break;
        }
    }

}
