package model;

import java.util.ArrayList;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.MLcontroller;

/**
 * MLmodel is class that extend Observable
 */
public class MLmodel  extends Observable{

	private Attributes atrributes;
	private MLcontroller controller;
	

	/**
	 * ctor
	 */
	public MLmodel() {
		setAtrributes(new Attributes());
	}

	//getters and setters
	public MLcontroller getController() {
		return controller;
	}

	public void setController(MLcontroller controller) {
		this.controller = controller;
	}
	
	public Attributes getAtrributes() {
		return atrributes;
	}

	public void setAtrributes(Attributes atrributes) {
		this.atrributes = atrributes;
	}

	/**
	 * get not tagged reviews in format of JSON classify them and send back tagged reviews in JSON format.
	 * @param args the not tagged reviews in format of JSON.
	 */
	public void classifyReview(Object[] args) {
		Object[] ans = new Object[2];
		
		if (args.length<2) 
		{
			ans[0] = "error";
			ans[1] = "not enough data";
			setChanged();
			notifyObservers(ans);
		} 
		else if ((((String)args[0]).equals("classify")) && (((JSONArray)args[1]).length() != 0))
		{
			JSONArray jsonArray = (JSONArray)args[1];
			ArrayList<ReviewedTag> revsTag = new ArrayList<ReviewedTag>();
			ArrayList<Review> revs = JsonParser.Json2Reviews(jsonArray);
			for(Review rev : revs){
				ReviewedTag tag = atrributes.classify(rev);
				revsTag.add(tag);
			}
			JSONObject json = JsonParser.Tags2Json(revsTag);
			ans[0] = "error";
			ans[1] = json;
			setChanged();
			notifyObservers(ans);
			
		}
		else
		{
			ans[0] = "error";
			ans[1] = "wrong argumetns";
			setChanged();
			notifyObservers(ans);
		}
		
	}
	

}
