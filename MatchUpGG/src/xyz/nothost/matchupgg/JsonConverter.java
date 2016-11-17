package xyz.nothost.matchupgg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	private ArrayList<String> json_data = new ArrayList<String>();
	private ArrayList<Object> selected_elements = new ArrayList<Object>();

	private HashMap<String, Object> result_str_obj_map = null;
	private HashMap<String, Object> result_str_obj_map2;
	private HashMap<String, String> result_srt_str_map;
	private ArrayList<Object> result_obj_list;
	private ArrayList<HashMap<String,String>> result_map_list;
	private ApiConnector apiConnector = new ApiConnector();
	private ObjectMapper mapper = new ObjectMapper();

//	public void setResult


	public static void main(String args[]){


		JsonConverter jsonConv = new JsonConverter();
		JsonConverter jsonConv2 = new JsonConverter();
		JsonConverter jsonConv3 = new JsonConverter();
		JsonConverter jsonConv4 = new JsonConverter();

		ArrayList<Object> encoded_data;
		ArrayList<Object> encoded_data2;

		String url;
		String key;
		String key2;
/*
		// Leagueデータサンプル
		url = "https://jp.api.pvp.net/api/lol/jp/v2.5/league/master?type=RANKED_SOLO_5x5&api_key=RGAPI-5793cc78-43fd-498e-b399-b037cdb99034";
		key = "playerOrTeamId";
		key2 = "playerOrTeamName";

		encoded_data = jsonConv.getElementByKey(url, key);
		encoded_data2 = jsonConv2.getElementByKey(url, key2);

		for (int i = 0; i < encoded_data.size(); i++){
			System.out.println(encoded_data2.get(i) + " : " + encoded_data.get(i));
		}
*/
		key = "id";
		key2 = "name";
		url = "https://jp.api.pvp.net/api/lol/jp/v1.2/champion?freeToPlay=True&api_key=RGAPI-5793cc78-43fd-498e-b399-b037cdb99034";
//		encoded_data =  jsonConv3.getElementByKey(url, key);
//		encoded_data2 = jsonConv4.getElementByKey(url, key2);

//		for (int i = 0; i < encoded_data.size(); i++){
//			System.out.println(encoded_data2.get(i) + " : " + encoded_data.get(i));
//		}

		jsonConv.getJsonData(url);
	}

/*	public ArrayList<Object> getElementByKey(String url, String selected_key){

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
*/


	private void getJsonData(String url){

		json_data = apiConnector.executeHttpGet(url);

		try {
			// nest1(Map)を抽出
			result_str_obj_map = (HashMap<String, Object>) mapper.readValue(json_data.get(0), Map.class);

			// 大枠のMapを順に処理する
			for(Object nest1_key : result_str_obj_map.keySet()){

				// mapのelementの中身がMapかListかStringか
				Object obj = result_str_obj_map.get(nest1_key);
				HashMap<String, Object> nest2_map = null;
				ArrayList<Object> nest2_list = null;
				HashMap<String, Object> nest3_map = null;
				ArrayList<Object> nest3_list = null;
				try{
					// map処理
					nest2_map = (HashMap<String, Object>) obj;

					// 2つ目のnest(Map)を順に処理する
					for(Object nest2_key : nest2_map.keySet()){

						result_str_obj_map2.put(nest2_key.toString(), nest2_map.get(nest2_key));
						Object obj2 = result_str_obj_map2.get(nest2_key);

					}
				} catch(ClassCastException e) {

					try{
						// list処理
						nest2_list = (ArrayList<Object>) obj;

						// 2つ目のnest(List)を順に処理する
						for(int i = 0; i < nest2_list.size(); i++){
							result_obj_list.add(nest2_list.get(i));
						}
					} catch (ClassCastException e2){
						String str = (String) obj;
						System.out.println("なんもなかった");
					}
				}



			}

//			if(result_str_obj_map.get() == result_map_list.getClass()){
//			}else{
//				System.out.println(json_data.getClass() + "is not supported.");
//			}
			// nest2(List)を抽出
//			result_list = (ArrayList<HashMap<String, String>>) result.get("entries");

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
