package aslabs.manyinone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

public class ActiMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;


    private AdvancedWebView mAdvancedWebView;
    private TabLayout mTabLayoutTopNews;
    private TabLayout mTabLayoutAreaNews;
    private TabLayout mTabLayoutLangNews;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acti_main);

        setSupportActionBar(mToolbar);
        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        createObj();
        generateId();
        registerEvent();


        if (Utils.getPreferenceInt(mContext, Utils.FIRST_OPEN) != 1) {

            new AlertDialog.Builder(this)
                    .setTitle("First time user note")
                    .setIcon(getResources().getDrawable(R.drawable.ic_change))
                    .setMessage("1. Swipe from left to right for navigation panel.\n\n2. Ads which are shown are from websites and not from this application." +
                            "\n\n3. The loading time is based on your internet speed.\n\n4. No history is stored")
                    .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utils.setPreferenceInt(mContext, Utils.FIRST_OPEN, 1);
                            dialog.dismiss();
                            mDrawerLayout.openDrawer(Gravity.START);
                        }
                    }).setNegativeButton("Show me Again later", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
            }).show();


        }

        if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_TOP_NEWS) {
            changeTabs();
            mAdvancedWebView.clearHistory();
            mAdvancedWebView.loadUrl(getString(R.string.news_times_of_india));
            Log.d(TAG, "onCreate: top news");
        } else if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_AREA_NEWS) {
            changeTabs();
            mAdvancedWebView.clearHistory();
            mAdvancedWebView.loadUrl(getString(R.string.news_state_andaman));
            Log.d(TAG, "onCreate: area");
        } else if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_LANG_NEWS) {
            changeTabs();
            mAdvancedWebView.clearHistory();
            mAdvancedWebView.loadUrl(getString(R.string.news_lang_hindi_webduniya));
            Log.d(TAG, "onCreate: language");
        } else {

            mAdvancedWebView.clearHistory();
            mAdvancedWebView.loadUrl(getString(R.string.news_times_of_india));
            Utils.setPreferenceInt(mContext, Utils.READ_NEWS_MODE, Utils.READ_TOP_NEWS);
            changeTabs();
            Log.d(TAG, "default");
        }


    }

    public void shareLink(String link) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Read News Android App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, link + " \n\n\n Download Read News for Android: https://play.google.com/store/apps/details?id=aslabs.manyinone.readnews");
        startActivity(Intent.createChooser(sharingIntent, "Share Using"));
    }

    public void registerEvent() {

        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);


        mTabLayoutAreaNews.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText() != null)
                    if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_AREA_NEWS) {

                        if (tab.getText().equals(getString(R.string.andaman))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_andaman));
                        } else if (tab.getText().equals(getString(R.string.andhrapradesh))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_andhra_pradesh));
                        } else if (tab.getText().equals(getString(R.string.arunachal_pradesh))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_arunachal_pradesh));
                        } else if (tab.getText().equals(getString(R.string.assam))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_assam));
                        } else if (tab.getText().equals(getString(R.string.bihar))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_bihar));
                        } else if (tab.getText().equals(getString(R.string.bihar2))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_bihar2));
                        } else if (tab.getText().equals(getString(R.string.chattisgarh))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_chattisgarh));
                        } else if (tab.getText().equals(getString(R.string.goa))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_goa));
                        } else if (tab.getText().equals(getString(R.string.delhi))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_delhi));
                        } else if (tab.getText().equals(getString(R.string.gujrat))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_gujrat));
                        } else if (tab.getText().equals(getString(R.string.haryana))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_haryana));
                        } else if (tab.getText().equals(getString(R.string.himachal_pradesh))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_himachal_pradesh));
                        } else if (tab.getText().equals(getString(R.string.jammu))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_jammu));
                        } else if (tab.getText().equals(getString(R.string.kashmir))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_kashmir));
                        } else if (tab.getText().equals(getString(R.string.kashmir2))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_kashmir2));
                        } else if (tab.getText().equals(getString(R.string.jharkhand))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_jharkhand));
                        } else if (tab.getText().equals(getString(R.string.karnataka))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_karnataka));
                        } else if (tab.getText().equals(getString(R.string.kerala))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_kerala));
                        } else if (tab.getText().equals(getString(R.string.lakshwadeep))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_laskhwadeep));
                        } else if (tab.getText().equals(getString(R.string.madhya_pradesh))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_madhya_pradesh));
                        } else if (tab.getText().equals(getString(R.string.maharashtra))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_maharashtra));
                        } else if (tab.getText().equals(getString(R.string.maharashtra2))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_maharashtra2));
                        } else if (tab.getText().equals(getString(R.string.maharashtra3))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_maharashtra3));
                        } else if (tab.getText().equals(getString(R.string.manipur))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_manipur));
                        } else if (tab.getText().equals(getString(R.string.meghalaya))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_meghalaya));
                        } else if (tab.getText().equals(getString(R.string.mizoram))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_mizoram));
                        } else if (tab.getText().equals(getString(R.string.nagaland))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_nagaland));
                        } else if (tab.getText().equals(getString(R.string.odisha))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_odisha));
                        } else if (tab.getText().equals(getString(R.string.punjab))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_punjab));
                        } else if (tab.getText().equals(getString(R.string.rajashthan))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_rajasthan));
                        } else if (tab.getText().equals(getString(R.string.sikkim))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_sikkim));
                        } else if (tab.getText().equals(getString(R.string.tamil_nadu))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_tamil_nadu));
                        } else if (tab.getText().equals(getString(R.string.telangana))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_telangana));
                        } else if (tab.getText().equals(getString(R.string.tripura))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_tripura));
                        } else if (tab.getText().equals(getString(R.string.uttar_pradesh))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_uttar_pradesh));
                        } else if (tab.getText().equals(getString(R.string.uttar_pradesh))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_uttarakhand));
                        } else if (tab.getText().equals(getString(R.string.west_bengal))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_state_west_bengal));
                        }
                    }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayoutTopNews.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText() != null)
                    if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_TOP_NEWS) {

                        if (tab.getText().equals("timesofindia")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_times_of_india));
                        } else if (tab.getText().equals("ndtv")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_ndtv));
                        } else if (tab.getText().equals("indiatoday")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_india_today));
                        } else if (tab.getText().equals("indianexpress")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_the_indian_express));
                        } else if (tab.getText().equals("thehindu")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_the_hindu));
                        } else if (tab.getText().equals("news18")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_news_18));
                        } else if (tab.getText().equals("bhaskar")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_bhaskar));
                        } else if (tab.getText().equals("firstpost")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_first_post));
                        } else if (tab.getText().equals("business-standard")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_business_standard));
                        } else if (tab.getText().equals("dnaindia")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_dna));
                        } else if (tab.getText().equals("deccanchronicle")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_deccan_chronicle));
                        } else if (tab.getText().equals("oneindia")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_one_india));
                        } else if (tab.getText().equals("financialexpress")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_the_financial_express));
                        } else if (tab.getText().equals("scroll")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_scroll_in));
                        } else if (tab.getText().equals("thehindubusinessline")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_business_line));
                        } else if (tab.getText().equals("thequint")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_the_quint));
                        } else if (tab.getText().equals("outlookindia")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_outlook_india));
                        } else if (tab.getText().equals("freepressjournal")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_press_journal));
                        } else if (tab.getText().equals("newsx")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_news_x));
                        } else if (tab.getText().equals("asianage")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_the_asian_age));
                        } else if (tab.getText().equals("navhindtimes")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_navhind_times));
                        } else if (tab.getText().equals("nagpurtoday")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_nagpur_today));
                        } else if (tab.getText().equals("india")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_india));
                        } else if (tab.getText().equals("jagran")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_jagran));
                        } else if (tab.getText().equals("manoramaonline")) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_manorma));
                        }
                    }

            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayoutLangNews.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText() != null)
                    if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_LANG_NEWS) {
                        if (tab.getText().equals(getString(R.string.hindi))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_hindi_aajtak));
                        } else if (tab.getText().equals(getString(R.string.hindi2))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_hindi_live_hindustan));
                        } else if (tab.getText().equals(getString(R.string.hindi3))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_hindi_raftar));
                        } else if (tab.getText().equals(getString(R.string.hindi4))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_hindi_webduniya));
                        } else if (tab.getText().equals(getString(R.string.bengali))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_bengali_abp));
                        } else if (tab.getText().equals(getString(R.string.bengali2))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_bengali_anandabazar));
                        } else if (tab.getText().equals(getString(R.string.bengali3))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_bengali_zeenews));
                        } else if (tab.getText().equals(getString(R.string.telgu))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_telgu_eenadu));
                        } else if (tab.getText().equals(getString(R.string.telgu2))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_telgu_tupaki));
                        } else if (tab.getText().equals(getString(R.string.marathi))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_marathi_ibn_lokmat));
                        } else if (tab.getText().equals(getString(R.string.marathi2))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_marathi_lokmat));
                        } else if (tab.getText().equals(getString(R.string.marathi3))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_marathi_loksatta));
                        } else if (tab.getText().equals(getString(R.string.tamil))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_tamil_dinamalar));
                        } else if (tab.getText().equals(getString(R.string.tamil2))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_tamil_dailythanthi));
                        } else if (tab.getText().equals(getString(R.string.urdu))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_urdu_times));
                        } else if (tab.getText().equals(getString(R.string.kannada))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_kannada));
                        } else if (tab.getText().equals(getString(R.string.gujrati))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_gujrati));
                        } else if (tab.getText().equals(getString(R.string.malyalam))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_malyalam));
                        } else if (tab.getText().equals(getString(R.string.sanskrit))) {
                            mAdvancedWebView.clearHistory();
                            mAdvancedWebView.loadUrl(getString(R.string.news_lang_sanskrit));
                        }
                    }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public void generateId() {

        mTabLayoutTopNews = findViewById(R.id.tabLayout_top_News);
        mTabLayoutAreaNews = findViewById(R.id.tabLayout_area_news);
        mTabLayoutLangNews = findViewById(R.id.tabLayout_lang_News);
        mAdvancedWebView = findViewById(R.id.webview);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.nav_view);

    }

    private void createObj() {
        mContext = this;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mAdvancedWebView.canGoBack()) {
                mAdvancedWebView.goBack();
            } else {
                finish();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acti_main, menu);
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

        if (id == R.id.top_news) {

            if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_TOP_NEWS) {
                Toast.makeText(mContext, "Already in Top news mode", Toast.LENGTH_SHORT).show();
                return true;
            }

            Utils.setPreferenceInt(mContext, Utils.READ_NEWS_MODE, Utils.READ_TOP_NEWS);
            changeTabs();
            mAdvancedWebView.clearHistory();
            mAdvancedWebView.loadUrl(getString(R.string.news_times_of_india));
            // Handle the camera action
        } else if (id == R.id.lang_news) {
            if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_LANG_NEWS) {
                Toast.makeText(mContext, "Already in Language mode", Toast.LENGTH_SHORT).show();
                return true;
            }
            Utils.setPreferenceInt(mContext, Utils.READ_NEWS_MODE, Utils.READ_LANG_NEWS);
            changeTabs();
            mAdvancedWebView.clearHistory();
            mAdvancedWebView.loadUrl(getString(R.string.news_lang_hindi_webduniya));

        } else if (id == R.id.area_news) {
            if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_AREA_NEWS) {
                Toast.makeText(mContext, "Already in Area mode", Toast.LENGTH_SHORT).show();
                return true;
            }
            Utils.setPreferenceInt(mContext, Utils.READ_NEWS_MODE, Utils.READ_AREA_NEWS);
            changeTabs();
            mAdvancedWebView.clearHistory();
            mAdvancedWebView.loadUrl(getString(R.string.news_state_andaman));
        } else if (id == R.id.nav_share) {
            if (mAdvancedWebView.getOriginalUrl() != null) {
                shareLink(mAdvancedWebView.getOriginalUrl());
                Toast.makeText(mContext, "Sharing Link", Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeTabs() {

        if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_TOP_NEWS) {
            mTabLayoutTopNews.setVisibility(View.VISIBLE);
            mTabLayoutAreaNews.setVisibility(View.GONE);
            mTabLayoutLangNews.setVisibility(View.GONE);
        } else if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_AREA_NEWS) {
            mTabLayoutTopNews.setVisibility(View.GONE);
            mTabLayoutAreaNews.setVisibility(View.VISIBLE);
            mTabLayoutLangNews.setVisibility(View.GONE);
        } else if (Utils.getPreferenceInt(mContext, Utils.READ_NEWS_MODE) == Utils.READ_LANG_NEWS) {
            mTabLayoutTopNews.setVisibility(View.GONE);
            mTabLayoutAreaNews.setVisibility(View.GONE);
            mTabLayoutLangNews.setVisibility(View.VISIBLE);
        }
    }
}
