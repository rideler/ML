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
	public static ArrayList<Review> Json2Reviews(JSONObject json){
		JSONArray array = null;
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			array = json.getJSONArray("reviews");
		} catch (JSONException e) {
			e.printStackTrace();
		}
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
	public static JSONObject Tags2Json(ReviewedTag reviewedTag){
		JSONObject object = new JSONObject();
		try {
			object.put("id", reviewedTag.getId());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONArray array = new JSONArray();
		for(Entry<String, String> entry : reviewedTag.getTags().entrySet()) {
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
		return object;
	}
}
