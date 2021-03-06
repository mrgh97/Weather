package com.app.zlt.perfectweather.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.zlt.perfectweather.R;
import com.app.zlt.perfectweather.util.Util;
import com.app.zlt.perfectweather.view.fragment.SelectCityFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SelectCityActivity extends AppCompatActivity {

    private Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    SelectCityFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        unbinder=ButterKnife.bind(this);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("请选择省份或城市");
        setSupportActionBar(toolbar);

        fragment = new SelectCityFragment();
        Util.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }

}
