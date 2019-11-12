package adaptiveHuffman;
import java.io.*;
public class FileOperator {
	public static String readFile(File file) {
		String fileContents = new String();
		String temp = new String();
		BufferedReader r = null;
		
		try {

			
			r = new BufferedReader(new FileReader(file));
			
			while((temp = r.readLine()) != null) {
				fileContents += temp;
			}
			
			
		}catch(IOException e) {
			
			System.err.println(e.getStackTrace());
			
		}finally {
			try {
				
				r.close();
			}catch(IOException e) {
				
				System.err.println(e.getStackTrace());
			}
		}
		
		return fileContents;
	}
	
	
	public static boolean saveFile(File file, String content) {
		boolean couldSave = false;
		
		FileWriter w = null;
		
		try {
			
			w = new FileWriter(file.getAbsolutePath());
			
			if(!file.exists()) {
				
				if(!file.createNewFile()) {
					couldSave = false;
				}
				
			}
			
			
			w.write(content);
			couldSave = true;
		
		}catch(IOException e){
			System.err.println(e.getStackTrace());
		}finally {
			
			try {
				w.flush();
				w.close();
			}catch(IOException e) {
				System.err.println(e.getStackTrace());
			}
		}
		
		return couldSave;
	}

}
