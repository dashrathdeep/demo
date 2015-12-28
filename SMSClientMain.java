import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SMSClientMain {

	/**
	 * @param args
	 */
	
	public String sendPushSMS(String username,String password,String message,String mobiles ,
			String smsId,String vendorID,String sender, String flash, String scheduleDate, String scheduleTime ){
		
//		String fdate = df.format(cal.getTime());
//		String tdate = df.format(cal.getTime());
//		String user = "mahaforest";
//		String pass = "1327e811606505f48ce612a22b2979e6";
		String route = "4";;
		String unicode = "0";

		String parameter = "authkey=" +vendorID+ "&mobiles="+ mobiles + "&message=" + message + "&sender="
				+ sender + "&route="+route+"&unicode="+unicode;
	
		System.out.println("parameter - "+parameter);
		
		byte[] postData = parameter.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;

		BufferedReader rd = null;
		StringBuilder sb = null;
		String line = null;

		String MainURL = "https://control.msg91.com/api/sendhttp.php";
		URL url;
		try {
			url = new URL(
			"https://control.msg91.com/api/sendhttp.php?authkey=85509AJLB0vH7J55642514&mobiles=8149799364&message=Welcome to MahaOnline SMS Gateway&sender=MAHGOV&route=4&unicode=0");
//			url = new URL(MainURL);
			System.out.println("MainURL  -- "+MainURL);
			HttpURLConnection http = (HttpURLConnection) url
					.openConnection();
			http.setDoOutput(true);
			http.setInstanceFollowRedirects(false);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setRequestProperty("Content-Length",
					Integer.toString(postDataLength));
			http.setRequestProperty("charset", "UTF8");
			http.setUseCaches(false);

			DataOutputStream wr = new DataOutputStream(
					http.getOutputStream());
			wr.write(postData);
			wr.flush();
			wr.close();
			// http.connect();

			int statusCode;
			statusCode = http.getResponseCode();
			System.out.println("statusCode == "+statusCode);
			return String.valueOf(statusCode);
		} catch (Exception e) {
			System.out.println("RunJob Exception " + e.toString());
		}
		
		return null;
	}
	
	
	public static void main(String[] args) {
		SMSClientMain sm = new SMSClientMain();
		
		String username ="test";
		String password ="test";
		String message ="This is test msg";
		String mobiles ="8149799364,8689955530";
		String smsId ="test";
		String vendorID ="88060AoWrPXzdK9QO5597d346";
		String sender ="MAHAGOV";
		String flash ="test";
		String scheduleDate ="test";
		String scheduleTime ="test";
		
		sm.sendPushSMS(username, password, message, mobiles ,
				 smsId, vendorID, sender, flash, scheduleDate, scheduleTime);
	}
}
