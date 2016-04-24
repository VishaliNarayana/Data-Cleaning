package edu.okstate.cs.EHL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.hash.Hash;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Statement;

public class ProvenanceTracker {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	   
	   public static boolean schemaExists(String arg1) throws ClassNotFoundException, SQLException {
	   
	      // Register driver and create driver instance
	      Class.forName(driverName);
	      // get connection
	      Connection con = (Connection) DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "root", "vishal3.");
	      
	      // execute statement to get schema
	      DatabaseMetaData meta=(DatabaseMetaData) con.getMetaData();
	      ResultSet set=meta.getSchemas(null, arg1);
	      
	      if(set.next()){return true;}
	      else{return false;}
	      
	   }
	   public static boolean tableExists(String arg1,String arg2) throws ClassNotFoundException, SQLException, MalformedURLException {
		      String location;
		      boolean verify=DataUsageTracker.isLogLocationSet();
		      if(verify==true){location=DataUsageTracker.getLogLocation();}
		      else{location="/DUT";}
		      // Register driver and create driver instance
		      Class.forName(driverName);
		      // get connection
		      Connection con = (Connection) DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "root", "vishal3.");
		      
		      // execute statement to get tables
		      DatabaseMetaData meta=(DatabaseMetaData) con.getMetaData();
		      ResultSet set=meta.getTables(null,arg1,arg2,null);
		      
		      if(set.next()){
		    	  Statement stmt = (Statement) con.createStatement();
		    	  stmt.executeQuery("Load data inpath '"+location+"' overwrite into table "+arg2);
		    	  return true;}
		      else{return false;}
		      
		   }
	   public static void createSchema(String arg1) throws SQLException, ClassNotFoundException{
		// Register driver and create driver instance
		      Class.forName(driverName);
		      
		      // get connection
		      Connection con = (Connection) DriverManager.getConnection("jdbc:hive://localhost:10000/userdb", "root", "vishal3.");
		      
		      // create statement
		      Statement stmt = (Statement) con.createStatement();
		      
		      // execute statement to create schema
		      stmt.executeQuery("Create schema "+arg1);
		         
		      
		      con.close();
	   }
	   
	   public static void createTable(String arg1,String arg2) throws ClassNotFoundException, SQLException, IOException{
		   	    
		      String location;
		      boolean verify=DataUsageTracker.isLogLocationSet();
		      if(verify==true){location=DataUsageTracker.getLogLocation();}
		      else{location="/DUT";}
		   
		      // Register driver and create driver instance
		      Class.forName(driverName);
		      
		      // get connection
		      Connection con = (Connection) DriverManager.getConnection("jdbc:hive://localhost:10000/userdb", "root", "vishal3.");
		      
		      // create statement
		      Statement stmt = (Statement) con.createStatement();
		      
		      // execute statement to get into database
		      stmt.executeQuery("use "+arg1);
		      
		      // execute statement to create table
		      stmt.executeQuery("CREATE EXTERNAL TABLE IF NOT EXISTS "
		    	         +arg2+"( user String, path String, "
		    	         +" timestamp String, type String)"
		    	         +" COMMENT ‘DUT’"
		    	         +" ROW FORMAT DELIMITED"
		    	         +" FIELDS TERMINATED BY ‘\t’"
		    	         +" LINES TERMINATED BY ‘\n’"
		    	         +" STORED AS TEXTFILE;");
		      stmt.executeQuery("Load data inpath '"+location+"' into table "+arg2);
		      con.close();
	   }
	   
	public static Map<String,String> getProvenanceInformation(String arg1,String arg2,String arg3,String arg4) throws ClassNotFoundException, SQLException, IOException{
		boolean schemaE=schemaExists("EHL_UI");
		if(schemaE==false){createSchema("EHL_UI");}
		boolean tableE=tableExists("EHL_UI","UsageInfo" );
		if(tableE==false){createTable("EHL_UI","UsageInfo");}
		Class.forName(driverName);
		Connection con = (Connection) DriverManager.getConnection("jdbc:hive://localhost:10000/userdb", "root", "vishal3.");
		 Statement stmt = (Statement) con.createStatement();
		 stmt.executeQuery("use UsageInfo");
		 String selectString = new String();
		 selectString +="select";
		 String condString =  new String();
		 condString +="where";
		 if(arg1==null &&arg2==null && arg3==null &arg4==null)
		 {
			 System.out.println("Cannot proceed");
			 System.exit(0);
		 }
		 if(arg1!=null &&arg2!=null &&arg3!=null && arg4!=null)
		 {
			 System.out.println("Everything is given, No need to fetch from Hive table");
			 System.exit(0);
		 }
		 if(arg1==null)
			 selectString += "user, ";
		 else
			 condString += "user="+arg1+"and";
		 if(arg2==null)
			 selectString += "timestamp, ";
		 else
			 condString += "timestamp"+arg2+"and";
		 if(arg3==null)
			 selectString += "path, ";
		 else
			 condString += "path="+arg3+"and";
		 if(arg4==null)
			 selectString += "type, ";
		 else
			 condString += "type="+arg4+"and";
		 selectString = selectString.substring(0,selectString.length()-2);
		 condString = condString.substring(0,selectString.length()-3);
		 
		 String queryString = selectString+"from UsageInfo"+condString;
		 ResultSet rs = stmt.executeQuery(queryString);
		 rs.next();
		 //parsing the result set based on arguments and return the values
		 Map<String,String> results = new HashMap<>();
		 if(null==arg1)
			 results.put("user",rs.getString("user"));
		 if(null==arg2)
			 results.put("timestamp",rs.getString("timestamp"));
		 if(null==arg3)
			 results.put("path",rs.getString("path"));
		 if(null==arg4)
			 results.put("type",rs.getString("type"));
		 return results;
	}
		 
		 
		   
	public static void main(String[] args) throws IOException, InterruptedException {

		
		//to be handled in Phase IV
		

	}

}
