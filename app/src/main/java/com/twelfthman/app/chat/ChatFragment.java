package com.twelfthman.app.chat;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twelfthman.app.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ChatFragment extends Fragment {

    @InjectView(R.id.list_chants)
    RecyclerView listChats;

    ChantAdapter chatAdapter;

    public ChatFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        ButterKnife.inject(view);

        listChats.setHasFixedSize(true);
        listChats.setLayoutManager(new LinearLayoutManager(view.getContext()));

        chatAdapter = new ChantAdapter(view.getContext());
        listChats.setAdapter(chatAdapter);

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




    private static class ChantAdapter extends RecyclerView.Adapter<ChatView>
    {
        private Context context;
        private List<ChatMessage> chants;
        private ChatView.Listener listener;

        private ChantAdapter(Context context)
        {
            this.context = context;
        }

        public void setChants(Collection<ChatMessage> chants)
        {
            this.chants = new ArrayList<>(chants);
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
            chantView.setChant(getItem(i));
        }
    }

}
