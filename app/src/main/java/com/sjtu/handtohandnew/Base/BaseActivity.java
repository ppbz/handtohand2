package com.sjtu.handtohandnew.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.*;


public class BaseActivity extends AppCompatActivity {
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    }

    public void startAct(Class<?> cls) {
        startActivity(new Intent(activity, cls));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activity = null;
    }

    public TextView findTv(int id) {
        return (TextView) findViewById(id);
    }

    public EditText findEt(int id) {
        return (EditText) findViewById(id);
    }

    public Button findBut(int id) {
        return (Button) findViewById(id);
    }

    public ImageView findImg(int id) {
        return (ImageView) findViewById(id);
    }

    public ListView findLv(int id) {
        return (ListView) findViewById(id);
    }

    public GridView findGv(int id) {
        return (GridView) findViewById(id);
    }

    public LinearLayout findLin(int id) {
        return (LinearLayout) findViewById(id);
    }

    public RelativeLayout findRel(int id) {
        return (RelativeLayout) findViewById(id);
    }

    public void toastS(String text) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    public void toastL(String text) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
    }

    public void logI(String text) {
        Log.i("Act", text);
    }

    public void logE(String text) {
        Log.e("Act", text);
    }
}
