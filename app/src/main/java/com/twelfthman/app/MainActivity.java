package com.twelfthman.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.twelfthman.app.chant.ChantFragment;

public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction().add(R.id.v_content, new ChantFragment()).commit();
    }

}
