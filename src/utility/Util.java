package utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Util {
	
	public Util() {}
	
	public String validateString(String indata) {
		try {
			if (indata == null) {
				System.out.println("indata: " + indata); //TODO TEMP
			}
			System.out.println("indata: " + indata); //TODO TEMP
			indata = indata.trim();
			System.out.println("trimmed indata: " + indata); //TODO TEMP
			indata = URLEncoder.encode(indata, "UTF-8");
			System.out.println("encoded indata: "+indata); //TODO TEMP
		} catch (UnsupportedEncodingException e) {
			System.err.println("Oops! Failed to encode your text to server readable format: "
		+ e.getMessage());
		}
		return indata;
	}

}
