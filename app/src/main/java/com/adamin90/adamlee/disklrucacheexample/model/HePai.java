package com.adamin90.adamlee.disklrucacheexample.model;

import org.litepal.crud.DataSupport;
import org.xmlpull.v1.XmlPullParser;

import android.graphics.Canvas;

public class HePai extends DataSupport{
	
	private String id;
	private String reason;
	private String size;
	private String created_at;
	private Target target;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public Target getTarget() {
		return target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	
}

