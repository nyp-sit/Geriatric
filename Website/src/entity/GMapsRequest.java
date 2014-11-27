package entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;





public class GMapsRequest {

	

	
	
	public static String[] SendURL(String inputPostal) throws Exception{
	    
		URLConnection conn = null;
		URL url = null;


		url 		 = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=Singapore+"+inputPostal);
		String urlParameters = "";
		
		conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

		writer.write(urlParameters);
		writer.flush();

		
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    
		String output = "";
		
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			output += line.toString();
		   }

		writer.close();
		reader.close();

		output = output.replace(" ", "");
		output = output.replace("[", "");
		output = output.replace("]", "");
		output = output.replace("{", "");
		output = output.replace("}", "");
		output = output.replace("\"", "");
		
		
		System.out.println("\n");

		System.out.println("Sent 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Connect Timeout : " + conn.getConnectTimeout());
		System.out.println("Content Length  : " + conn.getContentLength());
		System.out.println("Content Type    : " + conn.getContentType());
		

		System.out.println("\n");
		System.out.println(output);
		

		
		
		
		
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");

		// Split the String "|"
	    String[] result = output.split("\\,");
	    for (int x=0; x<result.length; x++){
	         System.out.println(result[x]);
	    }
		
		/* if (string.contains("-")) {// Split it} 
		 * else { System.out.println("String " + string + " does not contain -");}
		 */
	
		

		/*
		result[0]  = (Postal Code) results:address_components:long_name:577777
		result[1]  = (Postal Code) short_name:577777
		result[2]  = (Useless) types:postal_code
		result[3]  = (Area Name) long_name:AngMoKio
		result[4]  = (Area Name) short_name:AngMoKio
		result[5]  = (Area Type) types:neighborhood
		result[6]  = (Useless) political
		result[7]  = (Country) long_name:Singapore
		result[8]  = (Country) short_name:Singapore
		result[9]  = types:locality
		result[10] = political
		result[11] = long_name:Singapore
		result[12] = short_name:SG
		result[13] = types:country
		result[14] = political
		result[15] = formatted_address:Singapore577777
		result[16] = geometry:location:lat:1.376327
		result[17] = lng:103.829388
		result[18] = location_type:APPROXIMATE
		result[19] = viewport:northeast:lat:1.377675980291502
		result[20] = lng:103.8307369802915
		result[21] = southwest:lat:1.374978019708498
		result[22] = lng:103.8280390197085
		result[23] = types:postal_code
		result[24] = status:OK
		*/
		
	    
	    /////////////////////////////////////////////////////////////////
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");	    
		System.out.println(result[16]);
		String result16 = result[16];
		StringTokenizer tokenizer = new StringTokenizer(result16, ":");
		int tokenValue = tokenizer.countTokens();
		
		for (int i=0; i<tokenValue - 1 ; i++){
			System.out.println(tokenizer.nextToken());
		}
		String latitude = tokenizer.nextToken();
		latitude.replaceAll("\\s+","");
		
	    /////////////////////////////////////////////////////////////////
		
		
		
		
	    /////////////////////////////////////////////////////////////////
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");	    
		System.out.println(result[17]);
		String result17 = result[17];
		StringTokenizer tokenizer2 = new StringTokenizer(result17, ":");
		int tokenValue2 = tokenizer2.countTokens();
		
		for (int i=0; i<tokenValue2 - 1 ; i++){
			System.out.println(tokenizer2.nextToken());
		}
		String longitude = tokenizer2.nextToken();
		longitude.replaceAll("\\s+","");
	    /////////////////////////////////////////////////////////////////
		
		String[] returner = new String[2];
		returner[0] = latitude;
		returner[1] = longitude;
		return returner;
		
	}
	
	
	
}
