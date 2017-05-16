import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import model.Bathroom;
import model.Classify;
import model.JsonParser;
import model.Review;
import model.ReviewedTag;

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
		
		
		try {
			JSONObject obj = new JSONObject("{\"reviews\":[{\"id\":\"198575\",\"text\":\"room clean nice bla shower hbla good\"},{\"id\":\"637548684\",\"text\":\"bkaahhbathdsjjjdjd\"}]}");
			ArrayList<Review> list = new ArrayList<Review>();
			list = JsonParser.Json2Reviews(obj);
			System.out.println("print of reviews:");
			Classify bth = new Bathroom();
			for (Review review : list) {
				System.out.println("************new review:************");
				System.out.println(review.getId());
				System.out.println(review.getText());
				System.out.println(bth.synomContains(review.getText()));
			}
			System.out.println("********");
			System.out.println(bth.classifyAttribute());
			System.out.println("********");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
		HashMap<String, String> tags = new HashMap <String, String>();
		tags.put("a","1");
		tags.put("b","-1");
		tags.put("c", "0");
		ReviewedTag newTag = new ReviewedTag("198575", tags);
		JSONObject json = JsonParser.Tags2Json(newTag);
		System.out.println("print of tag2json");
		System.out.println(json);
	}

}
