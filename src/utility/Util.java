package utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * The Class Util holds utilities useful throughout the app.
 */
public class Util {
	
	/**
	 * Instantiates a new util.
	 */
	public Util() {}
	
	/**
	 * Validate string.
	 *
	 * @param indata the indata
	 * @return the string
	 */
	public String validateString(String indata) {
		try {
			if (indata == null) {
				System.out.println("indata: " + indata); 
			}
			indata = indata.trim();
			indata = URLEncoder.encode(indata, "UTF-8");
			//System.out.println("encoded indata: " + indata); // Used for checking
		} catch (UnsupportedEncodingException e) {
			System.err.println("Oops! Failed to encode your text to server readable format: "
		+ e.getMessage());
		}
		return indata;
	}

}
