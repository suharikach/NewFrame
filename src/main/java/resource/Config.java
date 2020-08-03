package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;



public class Config {
			
	//@Test
	public String Properties(String key)
	{
	Properties pr= new Properties();
	
try {
	File f= new File("C:\\Users\\sai.suharika.chakka\\eclipse-workspace\\DataDrivenFramework\\src\\main\\java\\resource\\pro.properties");
	FileInputStream fi;
	fi = new FileInputStream(f);
	pr.load(fi);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

return pr.getProperty(key);

	}
}
