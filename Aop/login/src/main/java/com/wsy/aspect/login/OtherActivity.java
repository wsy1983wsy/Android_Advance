package com.wsy.aspect.login;

import android.app.Activity;
import android.content.Intent;

public class OtherActivity extends Activity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_other);
    }
}
