package com.example.myapplication.JetPack.Navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.myapplication.R;

public class NavigationActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavHostFragment host;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        drawerLayout = findViewById(R.id.container);
        host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.myFragment);
        navController = new NavController(this);
        navController = host.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.myFragment), drawerLayout);
    }
}
