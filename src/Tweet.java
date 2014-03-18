import java.util.Date;

import twitter4j.GeoLocation;


public class Tweet {
	
	private String username;
	private String location;
	private String content;
	private GeoLocation geo;
	private Date date;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public GeoLocation getGeo() {
		return geo;
	}
	public void setGeo(GeoLocation geo) {
		this.geo = geo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
