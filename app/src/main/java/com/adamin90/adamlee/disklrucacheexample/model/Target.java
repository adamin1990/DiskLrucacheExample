package com.adamin90.adamlee.disklrucacheexample.model;

import org.litepal.crud.DataSupport;

public class Target extends DataSupport{
	private String id;
	private String name;
	private String lyric ;
	private String audio_file;
	private String cover;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLyric() {
		return lyric;
	}
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}
	public String getAudio_file() {
		return audio_file;
	}
	public void setAudio_file(String audio_file) {
		this.audio_file = audio_file;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	

}

