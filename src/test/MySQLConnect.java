package test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class MySQLConnect {

	/*
	 * Do Whatever Database Connection here!
	 */
	

	
	
	boolean isConnected = false;

	public MySQLConnect() {
		Connection conn;
        //Statement stmt;
        //ResultSet rs;

        String sql;
    	String url = "jdbc:mysql://splunk.ksynnet.com:3306/";
    	String dbName = "splunk";
    	String driver = "com.mysql.jdbc.Driver";
    	String userName = "root@localhost";
    	String password = "P@ssw0rd";

            try{
    			Class.forName(driver).newInstance();
    			conn = DriverManager
    					.getConnection(url + dbName, userName, password);

                //stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                //sql = "Select * from user_account";
                //rs = stmt.executeQuery(sql);

            }
            catch (Exception e){
               e.printStackTrace();
             
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
