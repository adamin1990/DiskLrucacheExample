package com.adamin90.adamlee.disklrucacheexample;

import android.os.AsyncTask;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.adamin90.adamlee.disklrucacheexample.model.HePai;
import com.adamin90.adamlee.disklrucacheexample.model.Target;
import com.adamin90.adamlee.disklrucacheexample.utils.Constant;
import com.adamin90.adamlee.disklrucacheexample.utils.HttpUtils;
import com.adamin90.adamlee.disklrucacheexample.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private LayoutInflater inflater;
    private MyAdapter adapter;
    private List<HePai> hepais;
    boolean first=false;
    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connector.getDatabase();
        listView=(ListView) findViewById(R.id.listView);
        inflater=getLayoutInflater();
        hepais=new ArrayList<HePai>();
        adapter=new MyAdapter(inflater, hepais,Constant.BASE_URL);
        listView.setAdapter(adapter);
        if(NetUtils.isConnected(this)){
            new LoadTask().execute(Constant.BASE_URL+1,Constant.BASE_URL+1);
        }else{
            Toast.makeText(this, "网络不好,启用缓存", Toast.LENGTH_SHORT).show();
            List<HePai> hePaishuancun=DataSupport.findAll(HePai.class,true);
            hepais.addAll(hePaishuancun);
            adapter.notifyDataSetChanged();
        }

    listView.setOnScrollListener(new AbsListView.OnScrollListener() {
        boolean idle=false;
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if(scrollState==SCROLL_STATE_IDLE){
                idle=true;

            }else {
                idle=false;
            }

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if(idle&&firstVisibleItem+visibleItemCount>=totalItemCount-1){
                page++;
                new LoadTask().execute(Constant.BASE_URL+page,Constant.BASE_URL+page);
            }

        }
    });
    }


    class LoadTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            if(params[1].equals(Constant.BASE_URL+1)){
                first=true;
            }else {
                first=false;
            }
            return HttpUtils.doGet(params[0]);

        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            hepais.addAll(ParseJson(result,first));
            adapter.notifyDataSetChanged();


        }

    }

    private List<HePai> ParseJson(String result,boolean isfirst){
        List<HePai> hePais=new ArrayList<HePai>();
        try {
            JSONArray jaArray=new JSONArray(result);
            if(isfirst){
                DataSupport.deleteAll(HePai.class);
            }

            for(int i=0;i<jaArray.length();i++){
                JSONObject jb=jaArray.getJSONObject(i);
                HePai   hePai=new HePai();
                Target  target=new Target();
                String creage_at=jb.getString("created_at");
                hePai.setCreated_at(creage_at);
                JSONObject jb2=(JSONObject) jb.get("target");


                String lyric=jb2.getString("name");
                target.setLyric(lyric);
                if(!jb2.has("avatars")){
                    JSONObject user= (JSONObject) jb2.get("user");
                    JSONObject avator= (JSONObject) user.get("avatars");
                    String image=avator.getString("origin");
                    target.setCover(image);
                }else{
                    JSONObject av= (JSONObject) jb2.get("avatars");
                    String avstring=av.getString("origin");
                    target.setCover(avstring);
                }

                hePai.setTarget(target);
                target.save();
                hePai.save();
                hePais.add(hePai);


            }
            return hePais;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.e("",e.toString());
            e.printStackTrace();
            return new ArrayList<HePai>();
        }

    }
}
