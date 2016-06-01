package com.tttony3.doubanmovie.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tttony3.doubanmovie.R;
import com.tttony3.doubanmovie.adapter.MainFragmentAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, USboxMoviesFragment.OnFragmentInteractionListener, TopMoviesFragment.OnFragmentInteractionListener,
        TopBooksFragment.OnFragmentInteractionListener,ChartBooksFragment.OnFragmentInteractionListener {
    private String TAG = "MainActivity";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragmentList;
    private List<String> fragmentTitles;
    private MainFragmentAdapter adapter;
    int currentType = 1;
    private static int MOVIE = 1;
    private static int BOOK = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mViewPager = (ViewPager) findViewById(R.id.viewpager_main);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        fragmentList = new ArrayList<>();
        fragmentTitles = new ArrayList<>();
        fragmentList.add(USboxMoviesFragment.newInstance("", ""));
        fragmentList.add(TopMoviesFragment.newInstance("", ""));
        fragmentTitles.add("Recent");
        fragmentTitles.add("Top");
        adapter = new MainFragmentAdapter(getSupportFragmentManager(), fragmentList, fragmentTitles);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);


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
        if (id == R.id.nav_movie) {
            if (currentType != MOVIE) {
                Log.v(TAG, "R.id.nav_movie");
                currentType = MOVIE;
                fragmentList.clear();
                fragmentTitles.clear();
                fragmentList.add(USboxMoviesFragment.newInstance("", ""));
                fragmentList.add(TopMoviesFragment.newInstance("", ""));
                fragmentTitles.add("Recent");
                fragmentTitles.add("Top");
                adapter.setFragmentsAndTitle(fragmentList, fragmentTitles);
                adapter.notifyDataSetChanged();
                mTabLayout.setupWithViewPager(mViewPager);
            }
        } else if (id == R.id.nav_book) {
            Log.v(TAG, "R.id.nav_book");
            if (currentType != BOOK) {
                currentType = BOOK;
                fragmentList.clear();
                fragmentTitles.clear();
                fragmentList.add(ChartBooksFragment.newInstance("", ""));
                fragmentList.add(TopBooksFragment.newInstance("", ""));
                fragmentTitles.add("Chart");
                fragmentTitles.add("Top");
                adapter.setFragmentsAndTitle(fragmentList, fragmentTitles);
                adapter.notifyDataSetChanged();
                mTabLayout.setupWithViewPager(mViewPager);
            }
        } else if (id == R.id.about) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
