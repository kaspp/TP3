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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

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
		
		final DoAnalyse da = new DoAnalyse();
		
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		
		final ExecutorService executor = Executors.newFixedThreadPool(5);
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
				//do something here.
				
				Runnable worker = new WorkerThread(status, da.getCollate());
				executor.execute(worker);
				
				
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
		//executor.shutdown();
		while(!executor.isTerminated());

	}
}
