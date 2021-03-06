package com.app.zlt.perfectweather.view.fragment;

import android.app.Notification;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;

import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.app.zlt.perfectweather.R;
import com.app.zlt.perfectweather.base.WeatherApplication;
import com.app.zlt.perfectweather.util.FileUtil;
import com.app.zlt.perfectweather.util.SharedPreferenceUtil;

import java.io.File;

import butterknife.Unbinder;

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener,
        Preference.OnPreferenceChangeListener{

    private Preference mClearCache;
    CheckBoxPreference mNoticicatin;


    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        mNoticicatin=(CheckBoxPreference)findPreference("noti_bar");
        if (SharedPreferenceUtil.getInstance().getNotificationModel() != Notification.FLAG_ONGOING_EVENT) {
            mNoticicatin.setChecked(false);
        }else {
            mNoticicatin.setChecked(true);
        }

        mNoticicatin.setOnPreferenceChangeListener(this);

        mClearCache=findPreference("clear_cache");
        mClearCache.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference){

        if(preference==mClearCache){
            if(FileUtil.delete(new File(WeatherApplication.getAppCacheDir()+"/Cache"))){
                Toast.makeText(WeatherApplication.getAppContext(),"缓存已清除",Toast.LENGTH_SHORT).show();
            }
        }

        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference,Object newValue){

        if(preference==mNoticicatin){
            SharedPreferenceUtil.getInstance().setNotificationModel((boolean) newValue ? Notification.FLAG_ONGOING_EVENT : Notification.FLAG_AUTO_CANCEL);
            Toast.makeText(WeatherApplication.getAppContext(),"更改成功,重启应用后生效",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
