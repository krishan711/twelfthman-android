package com.twelfthman.app.chant;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.twelfthman.app.R;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChantFragment extends Fragment
{
    @InjectView(R.id.list_chants)
    RecyclerView listChants;

    @InjectView(R.id.v_loading)
    View vLoading;

    ChantAdapter chantAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chant, container, false);
        ButterKnife.inject(this, view);

        listChants.setHasFixedSize(true);
        listChants.setLayoutManager(new LinearLayoutManager(view.getContext()));
        chantAdapter = new ChantAdapter(view.getContext());
        listChants.setAdapter(chantAdapter);

        new AsyncTask<Void, Void, List<Chant>>()
        {
            @Override
            protected List<Chant> doInBackground(Void... params)
            {
                ArrayList<Chant> chants = new ArrayList<>();
                try
                {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpResponse response = httpclient.execute(new HttpGet("https://2d7a0216.ngrok.io/matches/39"));
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == HttpStatus.SC_OK)
                    {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        response.getEntity().writeTo(out);
                        String responseString = out.toString();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                for (int i = 1; i <= 10; i++) {
                    Chant chant = new Chant();
                    chant.setTeam("Chant Team");
                    chant.setTitle("Chant " + i);
                    chant.setLyrics("Chant lyrics" + i);
                    chants.add(chant);
                }

                return chants;
            }

            @Override
            protected void onPostExecute(List<Chant> chants)
            {
                vLoading.setVisibility(View.GONE);
                chantAdapter.setChants(chants);
            }
        }.execute();

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
