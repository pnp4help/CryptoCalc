package cryptocalsi.it.cspit.charusat.crypto;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);

        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {}
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new homeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new homeFragment()).addToBackStack("Stack").commit();
                navigationView.setCheckedItem(R.id.nav_Home);
                break;
            case R.id.nav_Symmetric:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SymmetricFragment()).addToBackStack("Stack").commit();
                navigationView.setCheckedItem(R.id.nav_Symmetric);
                break;
            case R.id.nav_Asymmetric:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AsymmetricFragment()).addToBackStack("Stack").commit();
                navigationView.setCheckedItem(R.id.nav_Asymmetric);
                break;
            case R.id.nav_Hashing:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HashingFragment()).addToBackStack("Stack").commit();
                navigationView.setCheckedItem(R.id.nav_Hashing);
                break;
            case R.id.nav_Other:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new OtherFragment()).addToBackStack("Stack").commit();
                navigationView.setCheckedItem(R.id.nav_Other);
                break;
            case R.id.nav_Feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FeedbackFragment()).addToBackStack("Stack").commit();
                navigationView.setCheckedItem(R.id.nav_Feedback);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();

    }

}

