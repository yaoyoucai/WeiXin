package com.weixin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yh on 2016/1/15.
 * Fragment类
 */
public class TabsFragment extends Fragment {
    public static final String TITLE = "title";
    private String mTitle = "Default";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(TITLE);
        }
        TextView textView=new TextView(getActivity());
        textView.setText(mTitle);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
