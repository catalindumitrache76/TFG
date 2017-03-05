package catalin_tfg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class MainApp {

public static void hadoop() throws IOException {

    Configuration conf = new Configuration();
    conf.addResource(new Path("/usr/local/hadoop/projects/hadoop-1.0.4/conf/core-site.xml"));
    conf.addResource(new Path("/usr/local/hadoop/projects/hadoop-1.0.4/conf/hdfs-site.xml"));

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the file path...");
    String filePath = br.readLine();

    Path path = new Path(filePath);
    FileSystem fs = path.getFileSystem(conf);
    FSDataInputStream inputStream = fs.open(path);
    System.out.println("He leído un total de " + inputStream.available() + " characters");
    
    fs.close();
	}
}