import twitter4j.Status;
import twitter4j.User;


public class WorkerThread implements Runnable{
	
	private Tweet t = new Tweet();
	Status status;
	DoAnalyse da = new DoAnalyse();
	AnalyseTrend at = new AnalyseTrend();
	
	public WorkerThread(Status s)  {
		this.status = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		User user = status.getUser();
		System.out.println(Thread.currentThread().getName() + " Running!");
		System.out.println("\n#####################################");

		// gets Username
		t.setUsername(status.getUser().getScreenName());
		// String username = status.getUser().getScreenName();
		// System.out.println("Username1: "+username);

		// gets location
		t.setLocation(user.getLocation());
		// String profileLocation = user.getLocation();
		// System.out.println("Location1: "+profileLocation);

		// get content
		t.setContent(status.getText());
		// String content = status.getText();
		// System.out.println(content +"\n");

		t.setGeo(status.getGeoLocation());
		// GeoLocation g = status.getGeoLocation();
		// System.out.println("Lang: "+g.getLatitude()+
		// "Long: "+g.getLongitude());

		t.setDate(status.getCreatedAt());
		// Date date = status.getCreatedAt();
		// System.out.println("Date: "+date.toString());

		System.out.println("Username: " + t.getUsername());
		System.out.println("Location: " + t.getLocation());
		System.out.println("Tweet: " + t.getContent());

		if (t.getGeo() != null) {
		System.out.println("Lang: " + t.getGeo().getLatitude()
				+ " Long: " + t.getGeo().getLongitude());
		} 	
		System.out.println("Created Date: " + t.getDate().toString());
		DoAnalyse da = new DoAnalyse();
		da.checkTweet(t);
		System.out.println("\n#####################################");
		System.out.println(Thread.currentThread().getName() + " Ended!");
		
	}
}
