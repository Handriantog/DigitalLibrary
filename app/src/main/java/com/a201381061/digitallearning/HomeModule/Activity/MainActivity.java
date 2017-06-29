package com.a201381061.digitallearning.HomeModule.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a201381061.digitallearning.HomeModule.Fragment.AboutUsFragment;
import com.a201381061.digitallearning.HomeModule.Fragment.HomeFragment;
import com.a201381061.digitallearning.HomeModule.Fragment.SettingsFragment;
import com.a201381061.digitallearning.HomeModule.Utility.FirebaseHomeUtil;
import com.a201381061.digitallearning.LoginModule.Activity.LoginActivity;
import com.a201381061.digitallearning.NewPostModule.Activity.NewPostActivity;
import com.a201381061.digitallearning.R;

public class MainActivity extends AppCompatActivity{

    //Navigation Drawer
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private View navHeader;
    private Toolbar toolbar;

    //TAG FRAGMENT
    private static final String TAG_HOME = "home";
    private static final String TAG_ABOUT = "about";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    //Indek nav drawer
    public static int navItemIndex = 0;

    //String judul toolbar
    public String[] activityTiles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    //Element Nav Drawer Header
    private TextView textViewHeaderName;
    private ImageView imageViewImage;

    //Firebase Utility
    private FirebaseHomeUtil fbUtil;

    //Others
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        castingElement();
        fabClicked();
        setUpNavigationDrawer();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    private void castingElement(){
        fab = (FloatingActionButton)findViewById(R.id.fab);
    }

    private void fabClicked(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewPostActivity.class));
            }
        });
    }

    private void setUpNavigationDrawer(){
        activityTiles = getResources().getStringArray(R.array.nav_item_activity_titles);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_view);

        //set data di header
        loadHeader();

        //set menu click listener
        setUpNavigationMenu();
    }

    private void loadHeader(){
        fbUtil = new FirebaseHomeUtil();
        navHeader = navigationView.getHeaderView(0);
        textViewHeaderName = (TextView)navHeader.findViewById(R.id.drawer_header_name);
        imageViewImage = (ImageView)navHeader.findViewById(R.id.drawer_header_image);

        textViewHeaderName.setText(fbUtil.getFirebaseUserName());
    }

    private void setUpNavigationMenu(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_new_post:
                        navigationView.setCheckedItem(R.id.menu_none);
                        startActivity(new Intent(MainActivity.this, NewPostActivity.class));
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_settings:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_ABOUT;
                        break;
                    case R.id.nav_logout:
                        navItemIndex = 4;
                        fbUtil.signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                loadHomeFragment();

                return true;
                }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        //menset title toolbar
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawerLayout.closeDrawers();

            // show or hide the fab button
            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        toggleFab();

        //Closing drawer on item click
        drawerLayout.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 2:
                // settings
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            case 3:
                // about us fragment
                AboutUsFragment aboutUsFragment = new AboutUsFragment();
                return aboutUsFragment;

            default:
                return new HomeFragment();
        }
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle(){
        getSupportActionBar().setTitle(activityTiles[navItemIndex]);
}

    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }

}
