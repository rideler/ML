/**
 * 
 */
package model;

import java.util.ArrayList;
import weka.core.Attribute;

/**
 * class that hold list of categories for the reviews
 * the atrs are in weka format
 */
public class Attributes {

	private ArrayList<Attribute> atrs;
	private ArrayList<String> vals;
	/**
	 * CTOR that build with preset atrs
	 */
	public Attributes() {
		setVals();
		setAtr();
	}
	public ArrayList<Attribute> getAtr() {
		return atrs;
	}
	private void setAtr() {
		this.atrs = new ArrayList<Attribute>();
		this.atrs.add(new Attribute("bathroom", this.vals));
		this.atrs.add(new Attribute("quiet", this.vals));
		this.atrs.add(new Attribute("roomACC", this.vals));
		this.atrs.add(new Attribute("roomSize", this.vals));
		this.atrs.add(new Attribute("service", this.vals));
		this.atrs.add(new Attribute("clean", this.vals));
		this.atrs.add(new Attribute("room", this.vals));
		this.atrs.add(new Attribute("food", this.vals));

	}
	public ArrayList<String> getVals() {
		return vals;
	}
	private void setVals() {
		this.vals = new ArrayList<String>();
		this.vals.add("1");
		this.vals.add("-1");
	}

}
