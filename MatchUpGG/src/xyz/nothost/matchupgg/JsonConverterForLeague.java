package xyz.nothost.matchupgg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverterForLeague {

	private ArrayList<String> json_data = new ArrayList<String>();
	private ArrayList<Object> selected_elements = new ArrayList<Object>();

	private Map<String, Object> result = null;
	private ArrayList<HashMap<String, String>> result_list;
	private HashMap<String, String> result_map;
	private ApiConnector apiConnector = new ApiConnector();
	private ObjectMapper mapper = new ObjectMapper();

//	public void setResult


	public static void main(String args[]){


		JsonConverterForLeague jsonConv = new JsonConverterForLeague();
		JsonConverterForLeague jsonConv2 = new JsonConverterForLeague();
		String url = "https://jp.api.pvp.net/api/lol/jp/v2.5/league/master?type=RANKED_SOLO_5x5&api_key=RGAPI-5793cc78-43fd-498e-b399-b037cdb99034";
		String key = "playerOrTeamId";
		String key2 = "playerOrTeamName";

		ArrayList<Object> encoded_data = jsonConv.getElementByKey(url, key);
		ArrayList<Object> encoded_data2 = jsonConv2.getElementByKey(url, key2);

		for (int i = 0; i < encoded_data.size(); i++){
			System.out.println(encoded_data2.get(i) + " : " + encoded_data.get(i));
		}
		key = "name";
		url = "https://jp.api.pvp.net/api/lol/jp/v1.2/champion?freeToPlay=True&api_key=RGAPI-5793cc78-43fd-498e-b399-b037cdb99034";
		encoded_data = jsonConv.getElementByKey(url, key);


	}
// union
/*
	public HashMap<Object, Object> toHashMap(String url){

		HashMap<Object, Object> all_data = new HashMap<Object, Object>();
		this.getJsonData(url);

		for(String nest1_key : result.keySet()){

//			System.out.println(nest1_key + " : " + result.get(nest1_key));
			all_data.put(nest1_key, result.get(nest1_key));
		}
		System.out.println();
		for(int i = 0; i < result_list.size(); i++){

			// nest3(Map)を抽出
			result_map = result_list.get(i);

			for(Object nest3_key : result_map.keySet()){

				all_data.put(nest3_key, result_map.get(nest3_key));
//				Object str = result_map.get(nest3_key);
//				System.out.println(nest3_key + " : " + str);
			}
			System.out.println("");
		}
		return all_data;
	}

*/
	public ArrayList<Object> getElementByKey(String url, String selected_key){

		this.getJsonData(url);

		for(Object nest1_key : result.keySet()){
			if (nest1_key == selected_key ){

				selected_elements.add(result.get(selected_key));
			}

		}

		for(int i = 0; i < result_list.size(); i++){

			// nest3(Map)を抽出
			result_map = result_list.get(i);

			for(Object nest3_key : result_map.keySet()){
				if(nest3_key == selected_key){

					selected_elements.add(result_map.get(selected_key));
				}
			}
		}
		return selected_elements;
	}



	private void getJsonData(String url){

		json_data = apiConnector.executeHttpGet(url);

		try {
			// nest1(Map)を抽出
			result = mapper.readValue(json_data.get(0), Map.class);
			// nest2(List)を抽出
			result_list = (ArrayList<HashMap<String, String>>) result.get("entries");

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
