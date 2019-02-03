package com.qa.sms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class SmsUtil {
	
	
    public String birthdayBoy;
    public String phoneNumber;
    
	
	public void sendMessage(String name,String num) {

		this.birthdayBoy=name;
		this.phoneNumber=num;
		try {

			final TrustManager[] trustAllCerts = new TrustManager[] { 
					new X509TrustManager() 
					{
						@Override
						public void checkClientTrusted( final X509Certificate[] chain, final String authType ) {
						}
						@Override
						public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {
						}
						@Override
						public X509Certificate[] getAcceptedIssuers() {
							return null;
						}
					} };
			

			final SSLContext sslContext = SSLContext.getInstance( "SSL" );
			sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory( sslContext.getSocketFactory() );
			URL url = new URL(getURLPath());
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			//System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getURLPath() {
		
		String s1="Happy%20Birthday%20";
		String s3="%20Fill%20Happyness%20and%20Joy%20in%20your%20life";
		String s4=s1+s3;		
		String twar = getURL()+"httpapi/send?username=carrygowtham@gmail.com&password=7667130668&sender_id=SMSIND&route=T&phonenumber="+phoneNumber+"&message=Test%20sms%20from%20ANYVARIABLE1.%20Thanks%20for%20choosing%20our%20service%20-%20ANYVARIABLE2%20-%20SMSC%20Platform";		            
		return twar;
	}

	private String getURL() {

		return "http://smsc.biz/";
	}
		
	

}
