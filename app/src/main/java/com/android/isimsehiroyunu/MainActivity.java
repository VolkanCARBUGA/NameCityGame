package com.android.isimsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnAnasayfa(View view) {

        switch (view.getId()) {
            case R.id.btnNormaloyun:
                activityGeç("NormalOyun");
                break;
            case R.id.btnSureli:
                activityGeç("SureliOyun");
                break;
            case R.id.btnCıkıs:
                cıkısYap();
                break;
        }
    }

    private void activityGeç(String activity) {
        if (activity.equals("NormalOyun")) {
            intent = new Intent(this, NormalOyun.class);
            startActivity(intent);
        } else {
            intent = new Intent(this, SureliOyun.class);
            startActivity(intent);
        }


    }

    private void cıkısYap() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        cıkısYap();
    }
}