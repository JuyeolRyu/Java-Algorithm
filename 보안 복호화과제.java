import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static String read(String filePath) throws IOException {
	    StringBuilder stringBuilder;
	    FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    try {
	        stringBuilder  = new StringBuilder();
	        fileReader     = new FileReader(filePath);
	        bufferedReader = new BufferedReader(fileReader);
	        String line;
	        while ((line = bufferedReader.readLine()) != null)
	            stringBuilder.append(line).append('\n');
	         
	    } finally {
	        if (bufferedReader != null) 
	        	try { bufferedReader.close(); } catch (Exception ex) {}
	        if (fileReader != null) 
	        	try { fileReader    .close(); } catch (Exception ex) {}
	    }
	    return stringBuilder.toString();
	}
	public static void main(String args[]) throws IOException{
		String str;
		StringBuffer newStr = new StringBuffer();
		File file = new File("cyptoReult.txt");
		int index=0;
		
		str = read("D:\\대학교 자료\\기타 파일\\ciphertext.txt");
		System.out.println("<원본파일>========================================================");
		System.out.println(str);
		
		
		while(index < str.length()) {
			if(str.charAt(index) >= 65 && str.charAt(index)<=90) {
				newStr.insert(index, (char)(((str.charAt(index)-65)+21)%26+65));
			}else if(str.charAt(index) >= 97 && str.charAt(index)<=122) {
				newStr.insert(index, (char)(((str.charAt(index)-97)+21)%26+97));
			}else {
				newStr.insert(index,str.charAt(index));
			}
			index++;
		}
		System.out.println("<복호화파일>========================================================");
		System.out.println(newStr);
	}
	

}
