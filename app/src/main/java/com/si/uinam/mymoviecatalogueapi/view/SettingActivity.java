package com.si.uinam.mymoviecatalogueapi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.si.uinam.mymoviecatalogueapi.R;
import com.si.uinam.mymoviecatalogueapi.helper.LocaleHelper;

public class SettingActivity extends AppCompatActivity {

    public static  int RESULT_CODE = 400;
    public static final String EXTRA_LOCALE = "EXTRA_LOCALE";
    private RadioGroup rdgLocale;
    private RadioButton rdbId, rdbEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String localeId = getIntent().getStringExtra(MainActivity.EXTRA_CURRENT_LOCALE_ID);
        Log.i("LOCALE-SETTING",localeId);
        LocaleHelper.applyLocale(this);
        setContentView(R.layout.activity_setting);
        rdgLocale = findViewById(R.id.rdg_option_locale);
        rdbId = findViewById(R.id.rdb_indonesia);
        rdbEn = findViewById(R.id.rdb_english);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if(localeId.equals("in")){
            rdbId.setChecked(true);
        }else{
            rdbEn.setChecked(true);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        String locale = null;
        if(rdgLocale.getCheckedRadioButtonId()!=0){

            switch (rdgLocale.getCheckedRadioButtonId()){
                case R.id.rdb_indonesia:
                    locale = "Indonesia";
                    break;
                case R.id.rdb_english:
                    locale = "English";
                    break;
            }
        }
        Intent intentResult = new Intent();
        intentResult.putExtra(EXTRA_LOCALE, locale);
        RESULT_CODE = locale != null? 200 : 400;
        setResult(RESULT_CODE, intentResult);
        finish();
        return super.onSupportNavigateUp();
    }

}
