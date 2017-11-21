package com.goyalzz.knowyournationhackathon.controller.activity;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.goyalzz.knowyournationhackathon.R;
import com.goyalzz.knowyournationhackathon.controller.fragment.CountryListFragment;
import com.goyalzz.knowyournationhackathon.db.AppDatabase;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences pref;

    public static final String ONETIMESYNC = "ONE_TIME_SYNC";

    private Boolean backPressed = Boolean.FALSE;

    private AppDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataBase = Room.databaseBuilder(this,
                AppDatabase.class, getString(R.string.app_name))
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (!pref.getBoolean(ONETIMESYNC, Boolean.FALSE))
            pref.edit().putBoolean(ONETIMESYNC, Boolean.FALSE).apply();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CountryListFragment(), "CountryList")
                .commit();

    }

    public AppDatabase getDataBase() {
        return dataBase;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            if (backPressed) {
                super.onBackPressed();
            } else {
                backPressed = Boolean.TRUE;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backPressed = Boolean.FALSE;
                    }
                }, 2000);
                Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        if (v != null &&
                (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + v.getLeft() - scrcoords[0];
            float y = event.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                Activity activity = this;
                if (activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
