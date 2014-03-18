import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnalyseTrend {
	ArrayList<String> food = new ArrayList<String>();
	private static final String username = "postgres";
	private static final String password = "derrick";
	private static final String connStr = "jdbc:postgresql://localhost:5432/TP3";
	private static Connection conn;
	private static PreparedStatement insertStmt, getPosStmt, getNegStmt, pStmt,
			grabAllFoodStmt;

	
	public AnalyseTrend() {
		init();

	}

	private void init() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Error looking for Driver");
		}

		try {
			conn = DriverManager.getConnection(connStr, username, password);
		} catch (Exception e) {
			System.out.println("Failure to obtain connection: " + connStr);
		}
	}

	/**
	 * Return the positive count for the word in the database.
	 * 
	 * @param food - String
	 * @return int - food Count
	 * 
	 * @author kaspp - Derrick
	 */
	public int getPositive(String food) {

		String sql = "SELECT food, count(food) FROM analysed WHERE food = ? AND sentiment = 'positive' GROUP BY food";

		try {
			getPosStmt = conn.prepareStatement(sql);
			getPosStmt.setString(1, food);

			ResultSet results = getPosStmt.executeQuery();
			while (results.next()) {
				return results.getInt(2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("Unable to execute command in selecting positive sentiment");
		}

		return 0;
	}
	
	
	public int getNeutral(String food) {
		String sql = "SELECT food, count(food) FROM analysed WHERE food = ? AND sentiment = 'NULL' GROUP BY food";

		try {
			getPosStmt = conn.prepareStatement(sql);
			getPosStmt.setString(1, food);

			ResultSet results = getPosStmt.executeQuery();
			while (results.next()) {
				return results.getInt(2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("Unable to execute command in selecting positive sentiment");
		}

		return 0;
	}

	/**
	 * Return the negative count for the word in the database.
	 * 
	 * @param food - String
	 * @return int - food Count
	 * 
	 * @author kaspp - Derrick
	 */
	public int getNegative(String food) {

		String sql = "SELECT food, count(food) FROM analysed WHERE food = ? AND sentiment = 'negative' GROUP BY food";

		try {
			getNegStmt = conn.prepareStatement(sql);
			getNegStmt.setString(1, food);

			ResultSet results = getNegStmt.executeQuery();
			while (results.next()) {
				return results.getInt(2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("Unable to execute command in selecting negative sentiment");
		}

		return 0;
	}

	/**
	 * Insert words into the database. Return true if add is possible.
	 * Statement can only accept positive, negative and NULLL
	 * case-sensitive
	 * 
	 * @param food
	 *            - type String
	 * @param sentimental
	 *            - type String
	 * @return boolean
	 * @author kaspp - Derrick
	 */
	public boolean insert(String food, String sentimental) {

		String insert = "INSERT INTO analysed(food, dates, sentiment) VALUES ( ?, now(), ?) ";
		// System.out.println(insert);

		if (sentimental.equals("positive") || sentimental.equals("negative")
				|| sentimental.equals("NULL")) {

			try {

				insertStmt = conn.prepareStatement(insert);
				insertStmt.setString(1, food);
				insertStmt.setString(2, sentimental);

				return (insertStmt.executeUpdate() > 0);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to execute command in insert path");
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * Return ALL the different type of food in the database
	 * 
	 * @return ArrayList
	 * @author kaspp - Derrick
	 */
	public ArrayList<String> AnalysedFood() {
		ArrayList<String> food = new ArrayList<String>();

		String sql = "SELECT DISTINCT food FROM analysed GROUP BY food";

		try {

			grabAllFoodStmt = conn.prepareStatement(sql);
			ResultSet results = grabAllFoodStmt.executeQuery();

			while (results.next()) {
				food.add(results.getString(1));
			}

		} catch (SQLException e) {
			System.out.println("Unable to take all the food");
		}
		return food;
	}

	/**
	 * topFive is to take the top 5 most positive or negative food from the
	 * database. Usage: topFive(int i)
	 * 
	 * @Return ArrayList
	 * @param 1 for positive sentiments, 2 for negative sentiments
	 * @author kaspp - Derrick
	 */
	public ArrayList<String> topFive(int i) {
		ArrayList<String> food = new ArrayList<String>();
		String sql = null;
		if (i == 1) {
			sql = "SELECT food, COUNT(food) FROM analysed WHERE sentiment='positive' GROUP BY food ORDER BY COUNT(food) DESC LIMIT 5";
		} else if (i == 2) {
			sql = "SELECT food, COUNT(food) FROM analysed WHERE sentiment='negative' GROUP BY food ORDER BY COUNT(food) DESC LIMIT 5";
		} else if (i == 3) {
			sql = "SELECT food, COUNT(food) FROM analysed WHERE sentiment='null' GROUP BY food ORDER BY COUNT(food) DESC LIMIT 5";
		} else {
			return null;
		}
		try {
			grabAllFoodStmt = conn.prepareStatement(sql);

			ResultSet results = grabAllFoodStmt.executeQuery();
			while (results.next()) {
				food.add(results.getString(1));
			}

		} catch (SQLException e) {
			System.out.println("Unable to retrieve topFive()");
		}

		return food;
	}
	
	public boolean StoreTweet(Tweet t) {
		
		String insert = "INSERT INTO tweet( username, content, location, lon, lat, dates) VALUES (?, ?, ?, ?, ?, ?)";
	
		
			try {

				pStmt = conn.prepareStatement(insert);
				pStmt.setString(1, t.getUsername());
				pStmt.setString(2, t.getContent());
				pStmt.setString(3, t.getLocation());
				pStmt.setDouble(4, t.getGeo().getLongitude());
				pStmt.setDouble(5, t.getGeo().getLatitude());
				pStmt.setString(6, t.getDate().toString());

				return (pStmt.executeUpdate() > 0);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to execute command in insert path");
				return false;
			} 
	
	}

}
