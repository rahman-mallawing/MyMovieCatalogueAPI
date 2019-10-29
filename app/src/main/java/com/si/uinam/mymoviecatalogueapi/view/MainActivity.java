package com.si.uinam.mymoviecatalogueapi.view;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.si.uinam.mymoviecatalogueapi.R;
import com.si.uinam.mymoviecatalogueapi.helper.LocaleHelper;
import com.si.uinam.mymoviecatalogueapi.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private final int requestCode = 100;
    public static final String EXTRA_CURRENT_LOCALE_ID = "EXTRA_CURRENT_LOCALE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleHelper.applyLocale(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_locale:
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                intent.putExtra(EXTRA_CURRENT_LOCALE_ID,LocaleHelper.getLocale(this));
                Log.i("LOCALE-LOCALE",LocaleHelper.getLocale(this));
                startActivityForResult(intent, requestCode);
                return true;
            default:
                return true;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==this.requestCode){
            String result;
            if(resultCode != 200){
                return;
            }
            result = data.getStringExtra(SettingActivity.EXTRA_LOCALE);
            LocaleHelper.setLocale(this,(
                    result.toUpperCase().equals("INDONESIA")? "in" : "en"
            ));
            recreate();
            Toast.makeText(this, LocaleHelper.getLocale(this), Toast.LENGTH_SHORT).show();
        }
    }

}