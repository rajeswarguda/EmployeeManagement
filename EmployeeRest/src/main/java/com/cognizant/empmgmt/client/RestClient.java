package com.cognizant.empmgmt.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {
	
	private static final int NO_OF_ATTEMTS=3;
	
	public static void main(String[] args) throws Exception {
		
		String url = "http://localhost:8080/EmployeeRest-1.0/EmpService/emp/create";
		String requestXML = "<Employee><empId>1042</empId><empName>China</empName><joiningDate>2019-01-01</joiningDate><department>hr</department></Employee>";
		
		URL obj = new URL(url);
		int count =1;
		
		while(count <= NO_OF_ATTEMTS) {
			
			System.out.println("No of attempts : " + count);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			try {
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/xml");
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(requestXML);
				wr.flush();
				wr.close();
			}catch (Exception e) {
				System.out.println("Service is not available now...");
				count++;
				Thread.sleep(10000);
				continue;
			}
			
			int responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);
			
			if(responseCode >= 500) {
				count++;
				Thread.sleep(10000);
				continue;
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			
		}
		
	}

}
