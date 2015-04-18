package com.twelfthman.app.chant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twelfthman.app.Match;
import com.twelfthman.app.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by dhilipb on 18/04/2015.
 */
public class ChantView extends RecyclerView.ViewHolder {

    public interface Listener
    {
        public void onMatchClicked(Match match);
    }

    public Listener listener;
    private Context context;
    private Chant chant;

    @InjectView(R.id.txt_chant_title)
    TextView txtChantTitle;

    @InjectView(R.id.txt_chant_lyrics)
    TextView txtChantLyrics;


    protected ChantView(View view)
    {
        super(view);
        ButterKnife.inject(this, view);

    }

    public ChantView(Context context, ViewGroup parent)
    {
        this(LayoutInflater.from(context).inflate(R.layout.card_chant, parent, false));
        this.context = context;
    }

    public void setListener(Listener listener)
    {
        this.listener = listener;
    }

    public void setChant(Chant chant)
    {
        this.chant = chant;
        txtChantTitle.setText(chant.getTitle());
        txtChantLyrics.setText(chant.getLyrics());
    }
}
