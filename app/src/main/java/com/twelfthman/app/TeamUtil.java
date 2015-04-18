package com.twelfthman.app;

/**
 * Created by krishan on 18/04/15.
 */
public class TeamUtil
{
    public static int getTeamImageResId(int teamCode)
    {
        switch (teamCode)
        {
            case 40: return R.drawable.westham;
            case 33: return R.drawable.manchestercity;
            case 34: return R.drawable.manchesterunited;
            case 32: return R.drawable.liverpool;
            case 94: return R.drawable.qpr;
            case 28: return R.drawable.everton;
            case 939: return R.drawable.hullcity;
            case 25: return R.drawable.chelsea;
        }

        return R.drawable.default_team;
    }

}
