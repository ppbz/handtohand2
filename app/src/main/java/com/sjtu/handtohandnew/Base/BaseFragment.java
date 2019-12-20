package com.sjtu.handtohandnew.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public abstract class BaseFragment extends Fragment {
    public Activity activity;
    private View view;
    private boolean flag = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    /**
     * 下面两个方法是优化
     */
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //final禁止重写
        if (view == null) {
            view = initContentView(inflater, container);
        }
        return view;
    }


    @Override
    public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (flag) {
            init();
            flag = false;
        }
    }

    protected abstract void init();

    protected abstract View initContentView(LayoutInflater inflater, ViewGroup container);

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

    public void startAct(Class<?> cls) {
        startActivity(new Intent(activity, cls));
    }

}
