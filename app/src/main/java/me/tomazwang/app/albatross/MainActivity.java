package me.tomazwang.app.albatross;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_MAIN = "fragment_main";
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar)findViewById(R.id.main_toolBar);
        setSupportActionBar(mToolBar);


        MainFragment mainFragment = MainFragment.newInstance();

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.main_container, mainFragment, FRAGMENT_MAIN).commit();


    }
}
