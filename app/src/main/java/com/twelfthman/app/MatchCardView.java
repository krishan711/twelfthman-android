package com.twelfthman.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchCardView extends RecyclerView.ViewHolder
{
    public interface Listener
    {
        public void onMatchClicked(Match match);
    }

    @InjectView(R.id.txt_match_location)
    TextView txtMatchLocation;

    @InjectView(R.id.txt_match_time)
    TextView txtMatchTime;

    @InjectView(R.id.txt_match_team_1)
    TextView txtMatchTeam1;

    @InjectView(R.id.txt_match_team_code_1)
    TextView txtMatchTeamCode1;

    @InjectView(R.id.txt_match_team_2)
    TextView txtMatchTeam2;

    @InjectView(R.id.txt_match_team_code_2)
    TextView txtMatchTeamCode2;

    @InjectView(R.id.img_match_team_icon_1)
    ImageView imgMatchTeamIcon1;

    @InjectView(R.id.img_match_team_icon_2)
    ImageView imgMatchTeamIcon2;

    public Listener listener;
    public Match match;

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

    @OnClick(R.id.v_content)
    void onContentClicked()
    {
        if (listener != null)
        {
            listener.onMatchClicked(match);
        }
    }

    public void setMatch(Match match)
    {
        this.match = match;
        txtMatchTeam1.setText(match.teamName1);
        txtMatchTeam2.setText(match.teamName2);
        txtMatchTeamCode1.setText(match.teamCode1);
        txtMatchTeamCode2.setText(match.teamCode2);
        imgMatchTeamIcon1.setImageResource(TeamUtil.getTeamImageResId(match.teamId1));
        imgMatchTeamIcon2.setImageResource(TeamUtil.getTeamImageResId(match.teamId2));

        txtMatchLocation.setText(Html.fromHtml("at <b>" + match.location + "</b>"));
        if (match.startTime.after(new Date())) {
            txtMatchTime.setText(Html.fromHtml("Starts at <b>" + SimpleDateFormat.getTimeInstance(DateFormat.SHORT).format(match.startTime) + "</b>"));
        } else {
            txtMatchTime.setText(Html.fromHtml("Started at <b>" + SimpleDateFormat.getTimeInstance(DateFormat.SHORT).format(match.startTime) + "</b>"));
        }
    }

}
