package com.adamin90.adamlee.disklrucacheexample;

import java.util.List;

import com.adamin90.adamlee.disklrucacheexample.model.HePai;
import com.squareup.picasso.Picasso;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	private LayoutInflater layoutInflater;
	private List<HePai> hepais;
	

	public MyAdapter(LayoutInflater layoutInflater, List<HePai> hepais) {
		super();
		this.layoutInflater = layoutInflater;
		this.hepais = hepais;
	}

	@Override
	public int getCount() {
		return hepais.size();
		
	}

	@Override
	public Object getItem(int position) {
		return hepais.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holer;
		if(convertView==null){
			holer=new ViewHolder();
			convertView=layoutInflater.inflate(R.layout.item_list, parent, false);
			holer.created_at=(TextView) convertView.findViewById(R.id.creat_at);
			holer.imageview=(ImageView) convertView.findViewById(R.id.item_image);
			holer.lyrics=(TextView) convertView.findViewById(R.id.item_lyrics);
			convertView.setTag(holer);
		}
		else{
			holer=(ViewHolder) convertView.getTag();
		}
		holer.created_at.setText(hepais.get(position).getCreated_at());
		Picasso.with(convertView.getContext()).load(hepais.get(position).getTarget().getCover()).into(holer.imageview);
		holer.lyrics.setText(hepais.get(position).getTarget().getLyric());
		
		return convertView;
		
	}
	
	class ViewHolder{
		private TextView lyrics;
		private ImageView imageview;
		private TextView created_at;
	}

}

