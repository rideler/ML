/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * class that hold list of categories for the reviews
 * the atrs are in weka format
 */
public class Attributes {

	private ArrayList<Classify> atrs;
	/**
	 * CTOR that build with preset atrs
	 */
	public Attributes() {
		setAtr();
	}
	public ArrayList<Classify> getAtr() {
		return atrs;
	}
	private void setAtr() {
		this.atrs = new ArrayList<Classify>();
		this.atrs.add(new Bathroom());
		this.atrs.add(new Quiet());
		this.atrs.add(new RoomACC());
		this.atrs.add(new RoomSize());
		this.atrs.add(new Service());
		this.atrs.add(new Clean());
		this.atrs.add(new Room());
		this.atrs.add(new Food());
	}

	public ReviewedTag classify(Review rvw){
		HashMap <String, String> tags = new HashMap <String, String>();
		for(Classify cl: atrs){
			tags.put(cl.getName(), cl.classifyAttribute(rvw.getText()));
		}
		return(new ReviewedTag(rvw.getId(), tags));
	}

}
