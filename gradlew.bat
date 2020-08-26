package com.example.android.irsc.NavigationFolder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.irsc.MainActivity;
import com.example.android.irsc.NavigationFolder.ui.Competetion;
import com.example.android.irsc.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Arrays;

public class NavigationActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    final String[] Aboutarray={"About us","The Problem","The Reach","What we Do","Collaboraters"};
    final String[]  Eventsarray={"Past Events","Competetion","isafe'18","isafe'19"};
    final String[]  Domainaray={"Awareness Arm","Techmnical Arm","Law Arm","Medical Arm","Policy Arm","Service Arm"
    ,"Workshop Arm"};
    final String[] Teamarray={"Board of Awareness"};
    final String[] Mediaarray={"In Media","Videos","Newsletter","Gallery","Blog"};
    final String[] Collaborate={"Join Us","Contact Us"};
    final String[] Careerarray={"Careers ","City Team Program"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        T