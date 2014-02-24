import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class ConnectToDB {

	/*
	 * Do Whatever Database Connection here!
	 */
	/*
	
	String url = "jdbc:mysql://219.74.84.8:3306/";
	String dbName = "splunk";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "P@ssw0rd";

	Connection conn;
	
	*/
	boolean isConnected = false;

	public ConnectToDB() {
		try {
		/*	
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			
			*/
			isConnected = true;
			
		} catch (Exception e) {
			System.err.println("Error Connecting to Database");
		}
	}

	
	public void close()  {
		isConnected = false;
		//conn.close();
	}
	
	public boolean getDBConnection() {
		return isConnected;
	}
	
	
	public ArrayList<String> retriDict(String link) {
		
		BufferedReader br = null;
		ArrayList<String> neg = new ArrayList<String>();
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(link));		
			while ((sCurrentLine = br.readLine()) != null) {			
				neg.add(sCurrentLine.toUpperCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
		return neg;
	}
	
	
	public void getItem() {
		/*
		 * SQL here!
		 */
	}

}
