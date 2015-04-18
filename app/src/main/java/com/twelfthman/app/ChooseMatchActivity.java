package com.twelfthman.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseMatchActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_match);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.img_back)
    public void onImageClicked()
    {
        Log.i("ChooseMatchActivity", "onImageClicked");
        gotoMain();
    }

    private void gotoMain()
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
