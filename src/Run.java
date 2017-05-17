import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.BathroomSVM;
import model.ClassifyStr2vec;
import model.JsonParser;
import model.Review;
import model.ReviewedTag;
import view.Gateway;

/**
 * 
 */

/**
 * @author Shay
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gateway view = new Gateway();
		view.error("bla bla");
		try {
			JSONObject obj = new JSONObject("{\"reviews\":[{\"id\":\"198575\",\"text\":\"room clean nice bla SHOWER hbla good\"},{\"id\":\"637548684\",\"text\":\"bkaahhbathdsjjjdjd\"}]}");
			ArrayList<Review> list = new ArrayList<Review>();
			System.out.println("***Jsob Object*****");
			JSONArray jsonArray = JsonParser.jsonToArray(obj);
			if (jsonArray.length() == 0){
				System.out.println("json not rewies format");
			}
			else{
				for(int i = 0 ; i < jsonArray.length() ; i++){
					System.out.println(jsonArray.getJSONObject(i));
				}
			}
			
			System.out.println("********");
			list = JsonParser.Json2Reviews(jsonArray);
			System.out.println("print of reviews:");
			ClassifyStr2vec bth = new BathroomSVM();
			for (Review review : list) {
				System.out.println("************new review:************");
				System.out.println(review.getId());
				System.out.println(review.getText());
				System.out.println("********");
				//System.out.println(bth.classifyAttribute(review.getText()));
				System.out.println("********");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
		/*HashMap<String, String> tags = new HashMap <String, String>();
		tags.put("a","1");
		tags.put("b","-1");
		tags.put("c", "0");
		ReviewedTag newTag = new ReviewedTag("198575", tags);
		JSONObject json = JsonParser.Tags2Json(newTag);
		System.out.println("print of tag2json");
		System.out.println(json);*/


	}

}
