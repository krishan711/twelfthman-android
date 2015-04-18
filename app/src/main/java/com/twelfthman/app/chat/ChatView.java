package com.twelfthman.app.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twelfthman.app.Match;
import com.twelfthman.app.R;

import butterknife.ButterKnife;

/**
 * Created by dhilipb on 18/04/2015.
 */
public class ChatView extends RecyclerView.ViewHolder {

    public interface Listener
    {
        public void onMatchClicked(Match match);
    }

    public Listener listener;

    protected ChatView(View view)
    {
        super(view);
        ButterKnife.inject(this, view);
    }

    public ChatView(Context context, ViewGroup parent)
    {
        this(LayoutInflater.from(context).inflate(R.layout.card_chant, parent, false));
    }

    public void setListener(Listener listener)
    {
        this.listener = listener;
    }

    public void setChant(ChatMessage chant)
    {
        // TODO
    }
}