package com.twelfthman.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

public class MatchCardView extends RecyclerView.ViewHolder
{
    public interface Listener
    {
        public void onMatchClicked(Match match);
    }

    public Listener listener;

    protected MatchCardView(View view)
    {
        super(view);
        ButterKnife.inject(this, view);
    }

    public MatchCardView(Context context, ViewGroup parent)
    {
        this(LayoutInflater.from(context).inflate(R.layout.card_match, parent, false));
    }

    public void setListener(Listener listener)
    {
        this.listener = listener;
    }

    public void setMatch(Match match)
    {
        // TODO
    }

}
