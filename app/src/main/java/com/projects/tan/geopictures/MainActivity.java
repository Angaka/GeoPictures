package com.projects.tan.geopictures;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.luseen.simplepermission.permissions.MultiplePermissionCallback;
import com.luseen.simplepermission.permissions.Permission;
import com.luseen.simplepermission.permissions.PermissionActivity;
import com.luseen.simplepermission.permissions.PermissionUtils;
import com.luseen.simplepermission.permissions.SinglePermissionCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends PermissionActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        Permission[] permissions = {
                Permission.READ_EXTERNAL_STORAGE,
                Permission.COARSE_LOCATION,
                Permission.FINE_LOCATION,
        };

        requestPermissions(permissions, new MultiplePermissionCallback() {
            @Override
            public void onPermissionGranted(boolean allPermissionsGranted, List<Permission> grantedPermissions) {
                Fragment fragment = null;
                switch (id) {
                    case R.id.nav_gallery:
                        fragment = new FolderFragment();
                        break;

                    default:
                        break;
                }

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, fragment);
                ft.commitAllowingStateLoss();
            }

            @Override
            public void onPermissionDenied(List<Permission> deniedPermissions, List<Permission> foreverDeniedPermissions) {
                for (Permission permission : deniedPermissions)
                    Toast.makeText(MainActivity.this, String.format(getString(R.string.permission), permission.toString()), Toast.LENGTH_LONG).show();
            }
        });
/*        requestPermission(Permission.READ_EXTERNAL_STORAGE, new SinglePermissionCallback() {
            @Override
            public void onPermissionResult(boolean permissionGranted, boolean isPermissionDeniedForever) {
                if (permissionGranted) {
                    Fragment fragment = null;
                    switch (id) {
                        case R.id.nav_gallery:
                            fragment = new FolderFragment();
                            break;

                        default:
                            break;
                    }

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_main, fragment);
                    ft.commitAllowingStateLoss();
                } else if (isPermissionDeniedForever) {
                    PermissionUtils.openApplicationSettings(MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, String.format(getString(R.string.permission), Permission.READ_EXTERNAL_STORAGE.toString()), Toast.LENGTH_LONG).show();
                }

            }
        });*/

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
