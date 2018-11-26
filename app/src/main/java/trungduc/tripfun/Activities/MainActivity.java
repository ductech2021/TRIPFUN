package trungduc.tripfun.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import trungduc.tripfun.Models.Constants;
import trungduc.tripfun.Models.User;
import trungduc.tripfun.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG = "MainActivity";
    private Button btnUpTrip_H,btnFindTrip_H; // _H = _Home
    private int countPressBack = 0;
    public static User userLocal = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handle();

        Intent intent = getIntent();

        String user_id = intent.getStringExtra(Constants.TAG_USERID);
        String user_name = intent.getStringExtra(Constants.TAG_USERNAME);
        String user_birth = intent.getStringExtra(Constants.TAG_USERBIRTH);
        String user_phonenumber = intent.getStringExtra(Constants.TAG_USERPHONENUMBER);
        String user_gender = intent.getStringExtra(Constants.TAG_USERGENDER);
        String user_email = intent.getStringExtra(Constants.TAG_USEREMAIL);
        String user_status = intent.getStringExtra(Constants.TAG_USERSTATUS);
        Log.d(TAG, "onActivityResult: "+ user_id +user_name+ user_birth+user_phonenumber+user_gender+user_email+user_status);


        userLocal.setUser_id(Integer.parseInt(user_id));
        userLocal.setName(user_name);
        userLocal.setBirth(user_birth);
        userLocal.setPhonenumber(user_phonenumber);
        userLocal.setGender(user_gender);
        userLocal.setEmail(user_email);
        userLocal.setStatus(user_status);
        Log.d(TAG, "onActivityResult: "+ userLocal.getUser_id() );
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
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onBackPressed(){
        countPressBack += 1;
        if (countPressBack == 2){
            finish();
            moveTaskToBack(true);
        }else Toast.makeText(this, "Nhấn thêm 1 lần để thoát!", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpTrip_H:
                startActivity(new Intent(getApplicationContext(),UpTripActivity.class));
                break;
            case R.id.btnFindTrip_H:
                startActivity(new Intent(getApplicationContext(),FindTripActivity.class));
                break;
        }
    }
    private void handle(){
        btnUpTrip_H = (Button) findViewById(R.id.btnUpTrip_H);
        btnFindTrip_H = (Button) findViewById(R.id.btnFindTrip_H);
        btnUpTrip_H.setOnClickListener(this);
        btnFindTrip_H.setOnClickListener(this);
    }
}
