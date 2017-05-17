package model;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Review;
/**
 * 
 * Static class that converts object to/from json
 *
 */
public final class JsonParser {
	
	private JsonParser() {}
/**
 * getting json and converting it to array list of reviews
 * @param json 
 * @return arraylist of reviews by format of id:string and text:string
 */
	public static ArrayList<Review> Json2Reviews(JSONArray array){
		ArrayList<Review> list = new ArrayList<Review>();
		for(int i = 0 ; i < array.length() ; i++){
		    try {
				list.add(new Review(array.getJSONObject(i).getString("id"), array.getJSONObject(i).getString("text")));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
/**
 * getting processed review which only holds tags of its categories and return json	
 * @param reviewedTag processed review without the text
 * @return json object of the tags
 */
	public static JSONObject Tags2Json(ArrayList<ReviewedTag> reviewedTags){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (ReviewedTag tag : reviewedTags){
			JSONObject object = new JSONObject();
			try {
				object.put("id", tag.getId());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			JSONArray array = new JSONArray();
			for(Entry<String, String> entry : tag.getTags().entrySet()) {
				JSONObject elemnet = new JSONObject();
			    String key = entry.getKey();
			    String value = entry.getValue();
			    try {
					elemnet.put(key, value);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			    array.put(elemnet);
			}
			try {
				object.put("tags", array);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(object);
		}
		try {
			json.put("taggedReviews", jsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static JSONArray jsonToArray(JSONObject jsonObject) {
		JSONArray jsonArray = new JSONArray();
		if (jsonObject.has("reviews")){
			JSONArray array;
			try {
				array = jsonObject.getJSONArray("reviews");
				for(int i = 0 ; i < array.length() ; i++){
					if(array.getJSONObject(i).has("id") && array.getJSONObject(i).has("text")){
						jsonArray.put(array.getJSONObject(i));
					}
				}	
			} catch (JSONException e) {
			}
						
		}
		return jsonArray;
	}
		
	
	public static boolean objIsJason(Object object) {
		try {
			@SuppressWarnings("unused")
			JSONObject json = (JSONObject)object;
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
