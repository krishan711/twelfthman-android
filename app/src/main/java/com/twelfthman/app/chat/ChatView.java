package com.twelfthman.app.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twelfthman.app.Match;
import com.twelfthman.app.R;
import com.twelfthman.app.chant.Chant;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by dhilipb on 18/04/2015.
 */
public class ChatView extends RecyclerView.ViewHolder {

    public interface Listener
    {
        public void onMatchClicked(Match match);
    }

    public Listener listener;
    private Context context;
    private ChatMessage chat;

    @InjectView(R.id.txt_chat_name)
    TextView txtChatName;

    @InjectView(R.id.txt_chat_message)
    TextView txtChatMessage;

    protected ChatView(View view)
    {
        super(view);
        ButterKnife.inject(this, view);
    }

    public ChatView(Context context, ViewGroup parent)
    {
        this(LayoutInflater.from(context).inflate(R.layout.card_chat, parent, false));
    }

    public void setListener(Listener listener)
    {
        this.listener = listener;
    }

    public void setChat(ChatMessage chat)
    {
        this.chat = chat;
        txtChatName.setText(chat.getName());
        txtChatMessage.setText(chat.getMessage());
    }
}
