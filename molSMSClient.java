import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class molSMSClient {

	public String sendPushSMS(String username,String password,String message,String mobiles ,String smsId,String vendorID,String sender, String flash, String scheduleDate, String scheduleTime ){
		
//		String fdate = df.format(cal.getTime());
//		String tdate = df.format(cal.getTime());
		String user = "mahaforest";
		String pass = "1327e811606505f48ce612a22b2979e6";
		String route = "4";;
		String unicode = "0";

		String parameter = "authkey=" +vendorID+ "mobiles="+ mobiles + "&message=" + message + "&sender="
				+ sender + "&route="+route+"unicode="+unicode;
	

		byte[] postData = parameter.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;

		BufferedReader rd = null;
		StringBuilder sb = null;
		String line = null;

		String MainURL = "https://control.msg91.com/api/sendhttp.php";

		String FinalUrl = MainURL + parameter;

		URL url;
		try {
			// url = new URL(
			// "https://control.msg91.com/api/sendhttp.php?authkey=85509AJLB0vH7J55642514&mobiles=8149799364&message=Welcome to MahaOnline SMS Gateway&sender=MAHGOV&route=4&unicode=0");
			url = new URL(MainURL);

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
			rd = new BufferedReader(new InputStreamReader(
					http.getInputStream(), "UTF8"));
			sb = new StringBuilder();

			sb.append("{ data :");
			while ((line = rd.readLine()) != null) {
				sb.append(line + '\n');
			}

			sb.append("}");
			rd.close();

			String jsonResponse = sb.toString();
		} catch (Exception e) {
			System.out.println("RunJob Exception " + e.toString());
		}
		
		
		return null;
	}
	
	
}
