package com.dreams.model;

public class Dream {

	private int dreamId, dreamRecordsId;
	private String dreamDate, dreamTitle, dreamDescr;
	private float dreamDuration;
	
	public Dream() {}
	
	public Dream(int dreamId, int dreamRecordsId, String dreamDate, String dreamTitle,
				 String dreamDescr, float dreamDuration) {
		this.dreamId = dreamId;
		this.dreamRecordsId = dreamRecordsId;
		this.dreamDate = dreamDate;
		this.dreamTitle = dreamTitle;
		this.dreamDescr = dreamDescr;
		this.dreamDuration = dreamDuration;
	}
	
	/* GETTERS */
	public int getDreamId() {
		return dreamId;
	}
	public int getDreamRecordsId() {
		return dreamRecordsId;
	}
	public String getDreamDate() {
		return dreamDate;
	}
	public String getDreamTitle() {
		return dreamTitle;
	}
	public String getDreamDescr() {
		return dreamDescr;
	}
	public float getDreamDuration() {
		return dreamDuration;
	}
	
	/* SETTERS */
//	public void setDreamId(int dreamId) {
//		this.dreamId = dreamId;
//	}
//	public void setDreamRecordId(int dreamRecordId) {
//		this.dreamRecordsId = dreamRecordId;
//	}
	public void setDreamDate(String dreamDate) {
		this.dreamDate = dreamDate;
	}
	public void setDreamTitle(String dreamTitle) {
		this.dreamTitle = dreamTitle;
	}
	public void setDreamDescr(String dreamDescr) {
		this.dreamDescr = dreamDescr;
	}
	public void setDreamDuration(float dreamDuration) {
		this.dreamDuration = dreamDuration;
	}
}
