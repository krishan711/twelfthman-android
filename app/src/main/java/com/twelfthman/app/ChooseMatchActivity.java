package com.twelfthman.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChooseMatchActivity extends ActionBarActivity
{
    @InjectView(R.id.list_matches)
    RecyclerView listMatches;

    MatchAdapter matchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_match);
        ButterKnife.inject(this);

        listMatches.setHasFixedSize(true);
        listMatches.setLayoutManager(new LinearLayoutManager(this));
        matchAdapter = new MatchAdapter(this);
        listMatches.setAdapter(matchAdapter);
    }

    @OnClick(R.id.img_back)
    public void onImageClicked()
    {
        gotoMain();
    }

    private void gotoMain()
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private static class MatchAdapter extends RecyclerView.Adapter<MatchCardView>
    {
        private Context context;
        private List<Match> matches;
        private MatchCardView.Listener listener;

        private MatchAdapter(Context context)
        {
            this.context = context;
        }

        public void setMatches(Collection<Match> matches)
        {
            this.matches = new ArrayList<>(matches);
            notifyDataSetChanged();
        }

        public void setListener(MatchCardView.Listener listener)
        {
            this.listener = listener;
        }

        @Override
        public int getItemCount()
        {
            return matches != null ? matches.size() : 0;
        }

        public Match getItem(int position)
        {
            return matches.get(position);
        }

        @Override
        public MatchCardView onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            MatchCardView view = new MatchCardView(context, viewGroup);
            view.setListener(listener);
            return view;
        }

        @Override
        public void onBindViewHolder(MatchCardView matchCardView, int i)
        {
            matchCardView.setMatch(getItem(i));
        }
    }

}
