package com.example.x_etc_9_16;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_9_16.bean.WZBH;
import com.example.x_etc_9_16.fragment.Fragment_clwz;
import com.example.x_etc_9_16.fragment.Fragment_gjgl;
import com.example.x_etc_9_16.fragment.Fragment_grzx;
import com.example.x_etc_9_16.fragment.Fragment_hldgl;
import com.example.x_etc_9_16.fragment.Fragment_lkcx;
import com.example.x_etc_9_16.fragment.Fragment_shzs;
import com.example.x_etc_9_16.fragment.Fragment_sjfx;
import com.example.x_etc_9_16.fragment.Fragment_zhgl;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dra;
    private ImageView caidan;
    private TextView title;
    private NavigationView nav;
    private FragmentTransaction fragmentTransaction;
    private List<WZBH> wzbhList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (wzbhList == null){
            wzbhList = new ArrayList<>();
        }
        title.setText("智慧交通");
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dra.openDrawer(GravityCompat.START);
                nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.zhgl:
                                Fragment1(new Fragment_zhgl());
                                break;
                            case R.id.gjgl:
                                Fragment1(new Fragment_gjgl());
                                break;
                            case R.id.hldgl:
                                Fragment1(new Fragment_hldgl());
                                break;
                            case R.id.cxjg:
                                Fragment1(new Fragment_clwz(wzbhList));
                                break;
                            case R.id.lkcx:
                                Fragment1(new Fragment_lkcx());
                                break;
                            case R.id.shzs:
                                Fragment1(new Fragment_shzs());
                                break;
                            case R.id.sjfx:
                                Fragment1(new Fragment_sjfx());
                                break;
                            case R.id.grzx:
                                Fragment1(new Fragment_grzx());
                                break;
                        }
                        dra.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
            }
        });


    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void initView() {
        dra = findViewById(R.id.dra);
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        nav = findViewById(R.id.nav);
    }
}