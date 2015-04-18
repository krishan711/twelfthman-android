package com.twelfthman.app.chant;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twelfthman.app.Match;
import com.twelfthman.app.MatchCardView;
import com.twelfthman.app.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ChantFragment extends Fragment {

    @InjectView(R.id.list_chants)
    RecyclerView listChants;

    ChantAdapter chantAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chant, container, false);
        ButterKnife.inject(this, view);

        listChants.setHasFixedSize(true);
        listChants.setLayoutManager(new LinearLayoutManager(view.getContext()));

        chantAdapter = new ChantAdapter(view.getContext());
        listChants.setAdapter(chantAdapter);

        List<Chant> chants = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Chant chant = new Chant();
            chant.setTeam("Chant Team");
            chant.setTitle("Chant " + i);
            chant.setLyrics("Chant lyrics" + i);
            chants.add(chant);
        }
        chantAdapter.setChants(chants);

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




    private static class ChantAdapter extends RecyclerView.Adapter<ChantView>
    {
        private Context context;
        private List<Chant> chants;
        private ChantView.Listener listener;

        private ChantAdapter(Context context)
        {
            this.context = context;
        }

        public void setChants(List<Chant> chants)
        {
            this.chants = chants;
            notifyDataSetChanged();
        }

        public void setListener(ChantView.Listener listener)
        {
            this.listener = listener;
        }

        @Override
        public int getItemCount()
        {
            return chants != null ? chants.size() : 0;
        }

        public Chant getItem(int position)
        {
            return chants.get(position);
        }

        @Override
        public ChantView onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            ChantView view = new ChantView(context, viewGroup);
            view.setListener(listener);
            return view;
        }

        @Override
        public void onBindViewHolder(ChantView chantView, int i)
        {
            chantView.setChant(getItem(i));
        }
    }

}
