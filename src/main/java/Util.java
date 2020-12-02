
public class Util {

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getAsInt(String str) {
		if ( str == null ) {
			return 0;
		}
		
		str = str.trim();
		
		if(str.length() == 0) {
			return 0; 
		}
		
		str = str.replaceAll(",", ""); 
		
		try {
			return Integer.parseInt(str); 
		} catch (NumberFormatException e) {
			return 0; 
		}
	}

}
