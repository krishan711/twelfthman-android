package com.twelfthman.app.chat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.twelfthman.app.R;
import com.twelfthman.app.TwelfthManApplication;
import com.twelfthman.app.chant.Chant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ChatFragment extends Fragment {

    @InjectView(R.id.list_chat)
    RecyclerView listChats;

    @InjectView(R.id.send_message)
    EditText sendMessage;

    ChatAdapter chatAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        ButterKnife.inject(this,view);

        listChats.setHasFixedSize(true);
        listChats.setLayoutManager(new LinearLayoutManager(view.getContext()));

        chatAdapter = new ChatAdapter(view.getContext());
        listChats.setAdapter(chatAdapter);


        List<ChatMessage> chats = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ChatMessage chat = new ChatMessage();
            chat.setName("Name " + i);
            chat.setMessage("Message " + i);
            chats.add(chat);
        }
        chatAdapter.setChats(chats);

        sendMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    ChatMessage cm = new ChatMessage();
                    cm.setName("Liverpool fan");
                    cm.setMessage(((EditText) v).getText().toString());
                    chatAdapter.addChat(cm);
                    v.setText("");

                }
                return true;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private static class ChatAdapter extends RecyclerView.Adapter<ChatView>
    {
        private Context context;
        private List<ChatMessage> chants;
        private ChatView.Listener listener;

        private ChatAdapter(Context context)
        {
            this.context = context;
            this.chants = new ArrayList<>();
        }

        public void setChats(Collection<ChatMessage> chants)
        {
            this.chants = new ArrayList<>(chants);
            notifyDataSetChanged();
        }

        public void addChat(ChatMessage chant) {
            this.chants.add(chant);
        }

        public void setListener(ChatView.Listener listener)
        {
            this.listener = listener;
        }

        @Override
        public int getItemCount()
        {
            return chants != null ? chants.size() : 0;
        }

        public ChatMessage getItem(int position)
        {
            return chants.get(position);
        }

        @Override
        public ChatView onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            ChatView view = new ChatView(context, viewGroup);
            view.setListener(listener);


            return view;
        }

        @Override
        public void onBindViewHolder(ChatView chantView, int i)
        {
            chantView.setChat(getItem(i));
        }
    }

}
