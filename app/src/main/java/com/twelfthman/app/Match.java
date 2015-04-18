package com.twelfthman.app;

import java.util.Date;

public class Match
{
    public int matchId;
    public String teamName1;
    public String teamName2;
    public String teamCode1;
    public String teamCode2;
    public int teamId1;
    public int teamId2;

    public String location;
    public Date startTime;

    public Match(int matchId, String teamName1, String teamName2, String teamCode1, String teamCode2, int teamId1, int teamId2, String location, Date startTime)
    {
        this.matchId = matchId;
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.teamCode1 = teamCode1;
        this.teamCode2 = teamCode2;
        this.teamId1 = teamId1;
        this.teamId2 = teamId2;
        this.location = location;
        this.startTime = startTime;
    }

}
