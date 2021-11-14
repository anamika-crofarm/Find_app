package com.example.find;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class w_bottomNavi extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbottom_navi);

        //initializing
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //setting icons
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_account_circle_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_settings_24));


        //for showing fragments
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                Fragment fragment = null; //initializing fragment
                switch (model.getId()){
                    case 1:
                        fragment = new profileFragment2();
                        break;
                    case 2:
                        fragment = new Whome();
                        break;
                    case 3:
                        fragment = new Setting();
                        break;
//                        FirebaseAuth.getInstance().signOut();
//                        startActivity(new Intent(w_bottomNavi.this,startpage.class));

                }
                loadFragment(fragment);
                return null;
            }
        });
        bottomNavigation.show(2,true);  // home page will open by default
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                return null;
            }
        });

        bottomNavigation.setOnReselectListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                return null;
            }
        });
    }


    private void loadFragment(Fragment fragment) {  //change one to another
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }
    }
