import java.sql.SQLException;
import java.util.ArrayList;

public class DoAnalyse {
	ConnectToDB db;
	ArrayList<String> food = new ArrayList<String>();
	ArrayList<String> country = new ArrayList<String>();
	ArrayList<String> neg = new ArrayList<String>();
	ArrayList<String> pos = new ArrayList<String>();
	ArrayList<String> restr = new ArrayList<String>();
	ArrayList<String> ct = new ArrayList<String>();
	ArrayList<ArrayList<String>> collate = new ArrayList<ArrayList<String>>();
	ArrayList<String> chk = new ArrayList<String>();
	ArrayList<String> check;
	AnalyseTrend at = new AnalyseTrend();

	public DoAnalyse() {
		db = new ConnectToDB();
		init();

	}
	/**
	 * Clear everything inside the cache.
	 * @author kaspp - Derrick
	 */
	public void clear() {
		chk.clear();
		check.clear();
	}

	/**
	 * Check the phrase. 
	 * After string is passed in, it check every word that is in the phrase.
	 *
	 * @param phrase
	 * @return ArrayList<String>
	 * @author kaspp - Derrick
	 */
	public ArrayList<String> checkPhrase(String phrase) {

		String[] slit = phrase.split(" ");
		check = new ArrayList<String>();
		String wordcheck = "";
		boolean fp = false;
		int senti = 0;
		ArrayList<String> thatFood = new ArrayList<String>();
	
		
		for (int i = 0; i < slit.length; i++) {
			for (int j = 0; j < slit.length; j++) {
				if (i == j) {
					wordcheck = slit[i];
				} else if (j < i) { 
					continue;
				} else {
					wordcheck += " " + slit[j];
				}
				
				System.out.println(wordcheck);
				switch (checkWord(wordcheck)) {
				case 1:
					check.add("Food category found");
					thatFood.add(wordcheck);
					fp = true;
					break;

				case 2:
					check.add("Country found");
					break;

				case 3:
					check.add("Cooking terms found");
					thatFood.add(wordcheck);
					break;

				case 4:
					check.add("Negative sentiments found");
					if (senti == 0)
						senti = 1;
					else if (senti == 2) {
						senti = -1;
					}
					break;

				case 5:
					check.add("Positive sentiments found");
					if (senti == 0)
						senti = 2;
					else if (senti == 1) 
						senti = -1;
					break;

				case 6:
					check.add("Restaurant found");
					thatFood.add(wordcheck);
					break;

				default:
					// do nothing.
					break;
				}

			}
			

		}

		
		// check multiple.
		// added new line here only.
		if (fp) {
			check.add("The statement is a food post!");
			if (senti == 2) {
				for (String temp : thatFood) {
					at.insert(temp, "positive", 0);
				}
			} else if (senti == 1){
				for (String temp : thatFood) {
					at.insert(temp, "negative", 0);
				}
			}
			
			else if (senti == 0) {
				for (String temp : thatFood) { 
					at.insert(temp, "NULL", 0);
				}
			}

		} else
			check.add("The statement is not a food post!");
		
		if (senti == -1) {
			check.add("There are mixed sentiments in the post. No sentiments captured.");
		}
		
		try {
			at.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

	
	public ArrayList<String> checkTweet(Tweet t) {
		
		String phrase = t.getContent();
		
		String[] slit = phrase.split(" ");
		check = new ArrayList<String>();
		String wordcheck = "";
		boolean fp = false;
		int senti = 0;
		ArrayList<String> thatFood = new ArrayList<String>();
	
		
		for (int i = 0; i < slit.length; i++) {
			for (int j = 0; j < slit.length; j++) {
				if (i == j) {
					wordcheck = slit[i];
				} else if (j < i) { 
					continue;
				} else {
					wordcheck += " " + slit[j];
				}
				
				//checkWord(wordcheck);
				switch (checkWord(wordcheck)) {
				case 1:
					check.add("Food category found");
					thatFood.add(wordcheck);
					fp = true;
					break;

				case 2:
					check.add("Country found");
					break;

				case 3:
					check.add("Cooking terms found");
					thatFood.add(wordcheck);
					break;

				case 4:
					check.add("Negative sentiments found");
					if (senti == 0)
						senti = 1;
					else if (senti == 2) {
						senti = -1;
					}
					break;

				case 5:
					check.add("Positive sentiments found");
					if (senti == 0)
						senti = 2;
					else if (senti == 1) 
						senti = -1;
					break;

				case 6:
					check.add("Restaurant found");
					thatFood.add(wordcheck);
					break;

				default:
					// do nothing.
					break;
				}

			}
			

		}

		
		// check multiple.
		// added new line here only.
		if (fp) {
			check.add("The statement is a food post!");
			
			if (at.StoreTweet(t)) {
				System.out.println("Tweet Stored");
			} else {
				System.out.println("Tweet Not Stored");
			}
			
			
			if (senti == 2) {
				for (String temp : thatFood) {
					at.insert(temp.toLowerCase(), "positive", at.getTweetID(t));
					
					
				}
			} else if (senti == 1){
				for (String temp : thatFood) {
					at.insert(temp.toLowerCase(), "negative", at.getTweetID(t));
					
				}
			} else {
				for (String temp : thatFood) { 
					at.insert(temp.toLowerCase(), "NULL", at.getTweetID(t));
				}
			}

		} else
			check.add("The statement is not a food post!");
		
		if (senti == -1) {
			check.add("There are mixed sentiments in the post. No sentiments captured.");
		}
		
		try {
			at.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;

	}
	
	
	/**
	 * Check if the word is in the database return the number on which arraylist is the word found in.
	 * 
	 * @param food - String
	 * @return int - count
	 * @author kaspp - Derrick
	 */
	public int checkWord(String word) {

		int count = 0;
		for (ArrayList<String> p : collate) {
			count++;
			if (p.contains(word.toUpperCase())) {
				//System.out.println("Found " + word);
				return count;

			} 
		}
		return 0;
	}

	public void init() {

		food = db.getFood();
		country = db.getCountry();
		ct = db.getCt();
		neg = db.getNeg();
		pos = db.getPos();
		restr = db.getRestr();
		
		try {
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		collate.add(food);
		collate.add(country);
		collate.add(ct);
		collate.add(neg);
		collate.add(pos);
		collate.add(restr);

	}
	
	

}
