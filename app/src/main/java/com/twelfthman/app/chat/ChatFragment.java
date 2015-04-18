package com.twelfthman.app.chat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.pusher.client.Pusher;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.twelfthman.app.R;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    @InjectView(R.id.list_chat)
    RecyclerView listChats;

    ChatAdapter chatAdapter;
    Pusher pusher;
    PrivateChannel channel;

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

        pusher = new Pusher("116349");
        pusher.connect(new ConnectionEventListener()
        {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change)
            {
                System.out.println("State changed to " + change.getCurrentState() +
                        " from " + change.getPreviousState());
            }

            @Override
            public void onError(String message, String code, Exception e)
            {
                System.out.println("There was a problem connecting!");
            }
        }, ConnectionState.ALL);

//        TwelfthManApplication app = (TwelfthManApplication) getActivity().getApplication();
//        int teamId = app.getTeamId();
//        String teamName = "";
//        app.getMatch()
//        if (teamId == app.getMatch().teamId1) {
//            teamName = app.getMatch()
//        }

        return view;
    }

    private void sendChat(String team, String message)
    {
        try
        {
            JSONObject json = new JSONObject();
            json.put("team", team);
            json.put("message", message);
            channel.trigger("client-new-comment", json.toString());
        }
        catch (Exception e)
        {

        }
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

        public void addChat(ChatMessage message)
        {
            this.chants.add(message);
            notifyDataSetChanged();
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
