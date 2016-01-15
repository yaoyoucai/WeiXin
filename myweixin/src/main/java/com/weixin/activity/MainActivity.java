package com.weixin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.SearchView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private ViewPager viewPager;
    private SearchView mSearchView;
    private String[] titles = new String[]{
            "First Fragment",
            "Second Fragment",
            "Thrid Fragment",
            "Fouth Fragment"
    };
    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //让actionBar的图标隐藏
        getActionBar().setDisplayShowHomeEnabled(false);
        setOverFlowButtonAlways();

        initView();
        initDatas();
        viewPager.setAdapter(new MyPagertAdapter(getSupportFragmentManager()));
    }

    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
    }

    public void initDatas() {
        for (String title : titles) {
            TabsFragment tabsFragment = new TabsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TabsFragment.TITLE, title);
            tabsFragment.setArguments(bundle);
            fragments.add(tabsFragment);
        }
    }

    class MyPagertAdapter extends FragmentPagerAdapter {

        public MyPagertAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_serach);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return true;
    }

    /**
     * 让overflow正常显示
     */
    public void setOverFlowButtonAlways() {
        ViewConfiguration config = new ViewConfiguration().get(this);
        try {
            //通过反射获取名称为sHasPermanentMenuKey的字段
            Field menuKey = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            //设置字段可访问
            menuKey.setAccessible(true);
            menuKey.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
