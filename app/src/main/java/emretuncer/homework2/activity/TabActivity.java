package emretuncer.homework2.activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Emre on 10.5.2017.
 */
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import emretuncer.homework2.R;
import emretuncer.homework2.fragment.AnnouncementFragment;
import emretuncer.homework2.fragment.FoodFragment;
import emretuncer.homework2.fragment.NewsFragment;
import emretuncer.homework2.adapter.ViewPagerAdapter;
import emretuncer.homework2.model.Announcement;
import emretuncer.homework2.model.News;

public class TabActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Announcement> announcements;
    private List<News> newsList;
    private ArrayList<String> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        announcements = (List<Announcement>) i.getSerializableExtra("announcementList");
        newsList = (List<News>) i.getSerializableExtra("newsList");
        foodList = getIntent().getStringArrayListExtra("foodList");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); toolbar.setNavigationIcon(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Yemek");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.food, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Duyurular");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.duyuru, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Haberler");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.news, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        FoodFragment foodFragment = new FoodFragment();
        AnnouncementFragment announcementFragment = new AnnouncementFragment();
        NewsFragment newsFragment = new NewsFragment();
        adapter.addFrag(foodFragment, "Food");
        adapter.addFrag(announcementFragment, "Announcement");
        adapter.addFrag(newsFragment, "News");
        viewPager.setAdapter(adapter);
    }



    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    public ArrayList<String> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<String> foodList) {
        this.foodList = foodList;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}