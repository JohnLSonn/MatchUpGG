package knothSampleFile.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ApiConnector {

	private String api_url = new String();
	private static ArrayList<String> response_data = new ArrayList<String>();

	public ArrayList<String> executeHttpGet(String url) {

		this.api_url = url;

		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			HttpGet getMethod = new HttpGet(this.api_url);

			try(CloseableHttpResponse response = httpClient.execute(getMethod)) {
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					HttpEntity entity = response.getEntity();
					response_data.add(EntityUtils.toString(entity, StandardCharsets.UTF_8));
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		return response_data;
	}
/*
	// サンプル用main関数
	public static void main(String args[]){

		ArrayList<String> sample_api_url = new ArrayList<String>();
		ArrayList<String> sample_response_data = new ArrayList<String>();
		sample_api_url.add("https://jp.api.pvp.net/api/lol/jp/v2.5/league/master?type=RANKED_SOLO_5x5&api_key=RGAPI-5793cc78-43fd-498e-b399-b037cdb99034");

		ApiConnector apiConnector = new ApiConnector();
		sample_response_data = apiConnector.executeHttpGet(sample_api_url.get(0));

		for(int i = 0; i < sample_response_data.size(); i++){
			System.out.println(sample_response_data.get(i));
		}

	}
*/
}
