package fernando.com.geolocationapicellid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private int SPLASH_TIME = 4000; //This is 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mySuperIntent);
                finish();
            }
        }, SPLASH_TIME);
    }
}