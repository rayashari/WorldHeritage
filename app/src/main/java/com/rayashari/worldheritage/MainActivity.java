package com.rayashari.worldheritage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rayashari.worldheritage.adapter.MemberListAdapter;
import com.rayashari.worldheritage.model.Member;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private MemberListAdapter memberListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView listMember;

    protected Context context;
    int thumb[] = {R.drawable.collosseum, R.drawable.gyeongbokgung,R.drawable.stonehenge,R.drawable.machu_picchu,R.drawable.borobudur,R.drawable.eiffel};

    String name[];

    String location[];

    String desc[];
    double lat[] = {41.8912092,37.5778861,51.1788898,-13.1650709,-7.6081022,48.8582178};

    double lng[] = {12.4914854,126.9769658,-1.8262146,-72.5447154,110.2036425,2.2947585};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = getResources().getStringArray(R.array.name);
        location = getResources().getStringArray(R.array.location);
        desc = getResources().getStringArray(R.array.description);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ExploreActivity.class);
                startActivity(intent);
            }
        });
//        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
//        if(fragment == null){
//            fragment = MemberFragment.newInstance();
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(android.R.id.content, fragment, "")
//                    .commit();
//        } else {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .attach(fragment)
//                    .commit();
//        }
        listMember = (RecyclerView)findViewById(R.id.listMember);
        linearLayoutManager = new LinearLayoutManager(context);
        memberListAdapter = new MemberListAdapter(new MemberListAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MemberDetail.class);
                intent.putExtra(MemberDetail.EXTRA_NAME, name[position]);
                intent.putExtra(MemberDetail.EXTRA_TITLE, name[position]);
                intent.putExtra(MemberDetail.EXTRA_LOCATION, location[position]);
                intent.putExtra(MemberDetail.EXTRA_DESC, desc[position]);
                intent.putExtra(MemberDetail.EXTRA_LAT, lat[position]);
                intent.putExtra(MemberDetail.EXTRA_LNG, lng[position]);
                intent.putExtra(MemberDetail.EXTRA_PICTURE, thumb[position]);

                context.startActivity(intent);
            }
        });
        listMember.setLayoutManager(linearLayoutManager);
        listMember.setAdapter(memberListAdapter);
        loadData();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadData(){
        List<Member> memberList = new ArrayList<>();
        Member member;



        for(int i=0; i < thumb.length; i++){
            member = new Member();

            member.setId(i+1);
            member.setName(name[i]);
            member.setLocation(location[i]);
            member.setDesc(desc[i]);
            member.setlat(lat[i]);
            member.setLang(lng[i]);
            member.setThumb(thumb[i]);

            memberList.add(member);
        }

        memberListAdapter.addAll(memberList);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    static class Adapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragments = new ArrayList<>();
//        private final List<String> mFragmentTitles = new ArrayList<>();
//
//        public Adapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            mFragments.add(fragment);
//            mFragmentTitles.add(title);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragments.size();
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitles.get(position);
//        }
//    }
}
