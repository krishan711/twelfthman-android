package com.twelfthman.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChooseMatchActivity extends ActionBarActivity implements MatchCardView.Listener
{
    @InjectView(R.id.list_matches)
    RecyclerView listMatches;

    @InjectView(R.id.v_loading)
    View vLoading;

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
        matchAdapter.setListener(this);
        listMatches.setAdapter(matchAdapter);

        new AsyncTask<Void, Void, List<Match>>()
        {
            @Override
            protected List<Match> doInBackground(Void... params)
            {
                ArrayList<Match> matches = new ArrayList<>();
                try
                {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpResponse response = httpclient.execute(new HttpGet("https://2d7a0216.ngrok.io/matches/39"));
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == HttpStatus.SC_OK){
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        response.getEntity().writeTo(out);
                        String responseString = out.toString();
                        out.close();

                        GregorianCalendar cal = new GregorianCalendar();

                        Log.i("ChooseMatchActivity", responseString);
                        JSONArray responseObject = new JSONArray(responseString);
                        for (int i=0; i<responseObject.length(); i++)
                        {
                            JSONObject matchObject = responseObject.getJSONObject(i);
                            int matchId = matchObject.getInt("id");
                            String matchDate = matchObject.getString("scheduled_at");
                            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(matchDate);

                            cal.setTime(date);
                            if (2015 == cal.get(Calendar.YEAR) && 81 == cal.get(Calendar.DAY_OF_YEAR))
                            {
                                String stadiumName = matchObject.getJSONObject("stadium").getString("name");
                                JSONArray teams = matchObject.getJSONArray("teams");
                                JSONObject team1 = teams.getJSONObject(0);
                                int teamId1 = team1.getInt("id");
                                String teamName1 = team1.getString("name");
                                String teamCode1 = team1.getString("abbreviation");

                                JSONObject team2 = teams.getJSONObject(1);
                                int teamId2 = team2.getInt("id");
                                String teamName2 = team2.getString("name");
                                String teamCode2 = team2.getString("abbreviation");

                                matches.add(new Match(matchId, teamName1, teamName2, teamCode1, teamCode2, teamId1, teamId2, stadiumName, date));
                            }
                        }

                    } else {
                        response.getEntity().getContent().close();
                        throw new IOException(statusLine.getReasonPhrase());
                    }
                }
                catch (IOException | JSONException | ParseException e)
                {
                    e.printStackTrace();
                }
                return matches;
            }

            @Override
            protected void onPostExecute(List<Match> matches)
            {
                vLoading.setVisibility(View.GONE);
                matchAdapter.setMatches(matches);
            }
        }.execute();
    }

    private void gotoMain(final Match match)
    {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Who are ya?");
        builderSingle.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(match.teamName1);
        arrayAdapter.add(match.teamName2);
        arrayAdapter.add("Neutral");
        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int teamId = which == 0 ? match.teamId1 : which == 1 ? match.teamId2 : -1;
                ((TwelfthManApplication) getApplication()).setTeamId(teamId);
                ((TwelfthManApplication) getApplication()).setMatch(match);
                startActivity(new Intent(ChooseMatchActivity.this, MainActivity.class));
                finish();
            }
        });
        builderSingle.show();
    }

    @Override
    public void onMatchClicked(Match match)
    {
        gotoMain(match);
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
