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

	private ArrayList<ClassifyStr2vec> atrs;
	/**
	 * CTOR that build with preset atrs
	 */
	public Attributes() {
		setAtr();
	}
	public ArrayList<ClassifyStr2vec> getAtr() {
		return atrs;
	}
	private void setAtr() {
		this.atrs = new ArrayList<ClassifyStr2vec>();
		this.atrs.add(new BathroomSVM());
		this.atrs.add(new QuietSVM());
		this.atrs.add(new RoomACCSVM());
		this.atrs.add(new RoomSizeSVM());
		this.atrs.add(new ServiceSVM());
		this.atrs.add(new CleanSVM());
		this.atrs.add(new RoomSVM());
		this.atrs.add(new FoodSVM());
	}

	public ReviewedTag classify(Review rvw){
		HashMap <String, String> tags = new HashMap <String, String>();
		for(ClassifyStr2vec cl: atrs){
			tags.put(cl.getName(), cl.classifyAttribute(rvw.getText()));
		}
		return(new ReviewedTag(rvw.getId(), tags));
	}

}
