package edu.okstate.cs.EHL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.mapred.JobContext;
import org.apache.hadoop.mapred.TestComparators.MyWritable;
import org.apache.hadoop.mapreduce.Job;

public class DataUsageTracker {
	public static boolean isLogLocationSet()throws MalformedURLException{
		Configuration conf=new Configuration();
		conf.addResource(new File("$HADOOP_HOME/etc/hadoop/hdfs-site.xml").getAbsoluteFile().toURI().toURL());
		conf.reloadConfiguration();
		String f=conf.get("data.usage.tracker.log.location");
		if(f==null){return false;}
		else{return true;}
	}
	public static String getLogLocation()throws MalformedURLException{
		Configuration conf=new Configuration();
		conf.addResource(new File("$HADOOP_HOME/etc/hadoop/hdfs-site.xml").getAbsoluteFile().toURI().toURL());
		conf.reloadConfiguration();
		String f=conf.get("data.usage.tracker.log.location");
		if(f==null){return null;}
		else{return f;}
	}
	public static void trackDataUsage(String user,String path,String type) throws IOException, InterruptedException{
		String y="";
		boolean x=isLogLocationSet();
		if(x==true){
			y=getLogLocation();
		}
		else{
			y="/DUT";
		}
		Configuration conf=new Configuration();
		Path filepath=new Path(y);
		org.apache.hadoop.fs.FileSystem fs=filepath.getFileSystem(conf);
		
		
		if(fs.exists(filepath)){}
		else{try{
			     fs.createNewFile(filepath);}
			 catch(IOException e){e.printStackTrace();}
		}

		if(type.equals("MR")){
		
		Date date=new Date();
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(date);
		BufferedWriter br=new BufferedWriter(new OutputStreamWriter(fs.append(filepath)));
		br.write(user+","+path+","+timestamp+","+type);	
		br.close();
		}
		
	}
		public static void main(String[] args) throws IOException, InterruptedException{
			//arguments are derives from hdfs.h or ResourceManager.java from yarn
			String User=args[0];
			String Path=args[1];
			String Type=args[2];
			trackDataUsage(User,Path,Type);
		}
}
