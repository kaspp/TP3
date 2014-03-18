/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Date;

import twitter4j.*;

/**
 * <p>
 * This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 * 
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class sporeTweetEnglish {
	/**
	 * Main entry of this application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws TwitterException {
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		final Tweet t = new Tweet();
		StatusListener listener = new StatusListener() {

			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatus(Status status) {
				User user = status.getUser();

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
				System.out.println("Lang: " + t.getGeo().getLatitude()
						+ " Long: " + t.getGeo().getLongitude());
				System.out.println("Created Date: " + t.getDate().toString());

				System.out.println("\n#####################################");
				DoAnalyse da = new DoAnalyse();

				da.checkTweet(t);
				AnalyseTrend at = new AnalyseTrend();
				
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}

		};
		FilterQuery fq = new FilterQuery();
		String[] lang = { "en" };
		fq.language(lang);
		// fq.locations(new double[][] { { -122.75,36.8 }, { -121.75,37.8 } });
		fq.locations(new double[][] { { 102.8, 1.3667 }, { 103.8, 1.4667 } });

		twitterStream.addListener(listener);
		twitterStream.filter(fq);
	}
}
