package trungduc.tripfun.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import trungduc.tripfun.Fragments.AppInfoFragment;
import trungduc.tripfun.Fragments.UserInfoFragment;
import trungduc.tripfun.Fragments.ManagerTripFragment;
import trungduc.tripfun.Fragments.TripStatisticsFragment;
import trungduc.tripfun.Models.Constants;
import trungduc.tripfun.Models.Tripdetails;
import trungduc.tripfun.Models.User;
import trungduc.tripfun.R;
import trungduc.tripfun.Task.LoadUserByIDTask;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String TAG = "HomeActivity";
    private DrawerLayout drawer;
    public String trip_date,trip_time;
    public static User userLocal = new User();
    public static User TripUser = new User();
    public static Tripdetails tripdetail = new Tripdetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: ");
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);

        //get data from previous activity----------------
        Intent intent = getIntent();
        String user_id = intent.getStringExtra(Constants.TAG_USERID);
        String user_name = intent.getStringExtra(Constants.TAG_USERNAME);
        String user_birth = intent.getStringExtra(Constants.TAG_USERBIRTH);
        String user_phonenumber = intent.getStringExtra(Constants.TAG_USERPHONENUMBER);
        String user_gender = intent.getStringExtra(Constants.TAG_USERGENDER);
        String user_email = intent.getStringExtra(Constants.TAG_USEREMAIL);
        String user_status = intent.getStringExtra(Constants.TAG_USERSTATUS);
        String user_evaluation = intent.getStringExtra(Constants.TAG_EVALUATION);
        String trip_id = intent.getStringExtra(Constants.TAG_TRIPID);

        //if data trip detail exits-----------
        if (trip_id != null){
            String trip_userID = intent.getStringExtra(Constants.TAG_USERID);
            String trip_ori = intent.getStringExtra(Constants.TAG_ORIGIN);
            String trip_des = intent.getStringExtra(Constants.TAG_DESTINATION);
            trip_date = intent.getStringExtra(Constants.TAG_DATE);
            trip_time = intent.getStringExtra(Constants.TAG_TIME);
            String trip_vehicle = intent.getStringExtra(Constants.TAG_TYPEVEHICLE);
            String trip_position = intent.getStringExtra(Constants.TAG_POSITION);
            String trip_emptyseat = intent.getStringExtra(Constants.TAG_EMPTYSEAT);
            String trip_fullseat = intent.getStringExtra(Constants.TAG_FULLSEAT);
            String trip_seatprice = intent.getStringExtra(Constants.TAG_SEATPRICE);
            String trip_service = intent.getStringExtra(Constants.TAG_SERVICE);
            String trip_luggage = intent.getStringExtra(Constants.TAG_LUGGAGE);
            String trip_plan = intent.getStringExtra(Constants.TAG_PLAN);
            String trip_wgender = intent.getStringExtra(Constants.TAG_WGENDER);

            // set data for tripdetail object
            tripdetail.setTripID(Integer.parseInt(trip_id));
            tripdetail.setUserID(Integer.parseInt(trip_userID));
            tripdetail.setOrigin(trip_ori);
            tripdetail.setDestination(trip_des);
            tripdetail.setTypevehicle(trip_vehicle);
            tripdetail.setPosition(trip_position);
            tripdetail.setEmptyseat(Integer.parseInt(trip_emptyseat));
            tripdetail.setFullseat(Integer.parseInt(trip_fullseat));
            tripdetail.setSeatprice(Integer.parseInt(trip_seatprice));
            tripdetail.setService(trip_service);
            tripdetail.setLuggage(trip_luggage);
            tripdetail.setPlan(trip_plan);
            tripdetail.setWgender(trip_wgender);

            LoadUserByIDTask loadUserByIDTask = new LoadUserByIDTask(HomeActivity.this, trip_userID,TripUser);
            loadUserByIDTask.execute();

        }else{
            Log.d(TAG, "onCreate: USER ID RONG");
        }

        Log.d(TAG, "onActivityResult: "+ user_id +user_name+ user_birth+user_phonenumber+user_gender+user_email+user_status);
        userLocal.setUser_id(Integer.parseInt(user_id));
        userLocal.setName(user_name);
        userLocal.setBirth(user_birth);
        userLocal.setPhonenumber(user_phonenumber);
        userLocal.setGender(user_gender);
        userLocal.setEmail(user_email);
        userLocal.setStatus(user_status);
        userLocal.setEvaluation(Integer.parseInt(user_evaluation));

        //Set navigation
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set data for nav head
        View header = navigationView.getHeaderView(0);
        TextView tv_username_H = (TextView) header.findViewById(R.id.tv_username_H);
        TextView tv_phonenumber = (TextView) header.findViewById(R.id.tv_userphonenumber_H);
        TextView tv_email = (TextView) header.findViewById(R.id.tv_useremail_H);
        tv_username_H.setText(userLocal.getName());
        tv_phonenumber.setText(userLocal.getPhonenumber());
        tv_email.setText(userLocal.getEmail());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // set default fragment-----
        if (tripdetail.getOrigin() != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ManagerTripFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_manager_trip);
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new UserInfoFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_user_profile);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_manager_trip:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ManagerTripFragment()).commit();
                break;
            case R.id.nav_trip_statistics:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TripStatisticsFragment()).commit();
                break;
            case R.id.nav_user_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UserInfoFragment()).commit();
                break;
            case R.id.nav_app_info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AppInfoFragment()).commit();
                break;
            case R.id.nav_findtrip:
                Intent intentToFindTrip = new Intent(HomeActivity.this,FindTripActivity.class);
                intentToFindTrip.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentToFindTrip);
                break;
            case R.id.nav_uptrip:
                Intent intentToUpTrip = new Intent(HomeActivity.this,UpTripActivity.class);
                intentToUpTrip.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentToUpTrip);
                break;
            case R.id.nav_logout:
                Intent intentToLogin = new Intent(HomeActivity.this,LoginActivity.class);
                intentToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentToLogin);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Toast.makeText(this, "Back button is " +
                    "locked!", Toast.LENGTH_SHORT).show();
        }
    }
}