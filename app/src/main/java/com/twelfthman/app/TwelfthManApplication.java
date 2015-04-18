package com.twelfthman.app;

import android.app.Application;

/**
 * Created by krishan on 18/04/15.
 */
public class TwelfthManApplication extends Application
{
    private Match match;
    private int teamId;

    public void setMatch(Match match)
    {
        this.match = match;
    }

    public Match getMatch()
    {
        return match;
    }

    public int getTeamId()
    {
        return teamId;
    }

    public void setTeamId(int teamId)
    {
        this.teamId = teamId;
    }

}
