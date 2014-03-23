import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectToDB {

	/*
	 * Do Whatever Database Connection here!
	 */

	private static final String username = "postgres";
	private static final String password = "Password";
	private static final String connStr = "jdbc:postgresql://localhost:5432/TP3";
	private static PreparedStatement pStmt;
	

	Connection conn;

	boolean isConnected = false;

	public ConnectToDB() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Error looking for Driver");
		}
		
		
		try {
			conn = DriverManager.getConnection(connStr, username, password);
			
			isConnected = true;
			
		} catch (Exception e) {
			System.out.println("Failure to obtain connection: " + connStr);
		}
	}

	public void close() {
		isConnected = false;
		// conn.close();
	}

	public boolean getDBConnection() {
		return isConnected;
	}

	public ArrayList<String> getFood() {

		ArrayList<String> word = new ArrayList<String>();
		String sql = "SELECT word FROM food";
		
		try {
			
			pStmt = conn.prepareStatement(sql);
			ResultSet results = pStmt.executeQuery();
			
			while (results.next()) {
				word.add(results.getString(1).toUpperCase());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return word;
		
		
	}

	public ArrayList<String> getPos() {

		ArrayList<String> word = new ArrayList<String>();
		String sql = "SELECT word FROM positive";
		
		try {
			
			pStmt = conn.prepareStatement(sql);
			ResultSet results = pStmt.executeQuery();
			
			while (results.next()) {
				word.add(results.getString(1).toUpperCase());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return word;
		
		
	}
	
	public ArrayList<String> getNeg() {

		ArrayList<String> word = new ArrayList<String>();
		String sql = "SELECT word FROM negative";
		
		try {
			
			pStmt = conn.prepareStatement(sql);
			ResultSet results = pStmt.executeQuery();
			
			while (results.next()) {
				word.add(results.getString(1).toUpperCase());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return word;
		
	}
	
	public ArrayList<String> getCountry() {

		ArrayList<String> word = new ArrayList<String>();
		String sql = "SELECT word FROM country";
		
		try {
			
			pStmt = conn.prepareStatement(sql);
			ResultSet results = pStmt.executeQuery();
			
			while (results.next()) {
				word.add(results.getString(1).toUpperCase());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return word;
	}
	
	public ArrayList<String> getCt() {

		ArrayList<String> word = new ArrayList<String>();
		String sql = "SELECT word FROM cookingterm";
		
		try {
			
			pStmt = conn.prepareStatement(sql);
			ResultSet results = pStmt.executeQuery();
			
			while (results.next()) {
				word.add(results.getString(1).toUpperCase());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return word;
		
		
	}
	
	public ArrayList<String> getRestr() {

		ArrayList<String> word = new ArrayList<String>();
		String sql = "SELECT word FROM restr";
		
		try {
			
			pStmt = conn.prepareStatement(sql);
			ResultSet results = pStmt.executeQuery();
			
			while (results.next()) {
				word.add(results.getString(1).toUpperCase());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return word;
		
		
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
		conn = null;
	}

}
