/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * class that hold list of categories for the reviews
 */
public class Attributes {

	private ArrayList<IClassifier> atrs;
	
	/**
	 * CTOR that build with preset atrs
	 */
	public Attributes() {
		setAtr();
	}
	
	//getters and setters.
	public ArrayList<IClassifier> getAtr() {
		return atrs;
	}
	
	/**
	 * set the list of attributes in format of MLClassifier.
	 */
	private void setAtr() {
		this.atrs = new ArrayList<IClassifier>();
		this.atrs.add(new MLClassifier("bathroom", new ArrayList<String>(Arrays.asList("bath","shower","toilet","lavatory","sink","stool","bidet","flood","water","towels","urinal","boiler","soap","stream","pressure","robe","leak","flow","drip")), "mlModels/bathroom_model.model", "filters/string_2_vector", "headers/bathroomHeader.arff", "headers/S2Wbathroom.arff"));
		this.atrs.add(new MLClassifier("quiet", new ArrayList<String>(Arrays.asList("quite","noise","noisy","loud","sound","music","talk","street","train","cars","window","racket","commotion","shout","scream","yell","silent","mute")), "mlModels/quiet_model.model", "filters/string_2_vector", "headers/quietHeader.arff", "headers/S2Wquiet.arff"));
		this.atrs.add(new MLClassifier("roomACC", new ArrayList<String>(Arrays.asList("vault","remote","air","ac","tv","bed","comfort","work","broke","safe","internet","wifi","wi fi","wi-fi","closet","table","tea","coffee","mirror","fan","kettel","iron","hang","clock","sheet","blanket","slipper","pillow")), "mlModels/roomACC_model.model", "filters/string_2_vector", "headers/roomACCHeader.arff", "headers/S2WroomACC.arff"));
		this.atrs.add(new MLClassifier("roomSize", new ArrayList<String>(Arrays.asList("space","size","spread","small","big","huge","tiny","little","narrow","roomy","stuffy","spacious","cramped","enormous")), "mlModels/roomSize_model.model", "filters/string_2_vector", "headers/roomSizeHeader.arff", "headers/S2WroomSize.arff"));
		this.atrs.add(new MLClassifier("service", new ArrayList<String>(Arrays.asList("phone","help","service","reception","desk","answer","rude","clerk","bill","credit","customer","doorman","manager","employee","friend","inform","direction","check")), "mlModels/service_model.model", "filters/string_2_vector", "headers/serviceHeader.arff", "headers/S2Wservice.arff"));
		this.atrs.add(new MLClassifier("clean", new ArrayList<String>(Arrays.asList("clean","stain","dirt","spot","spark","filth","dust","soil","mud","mess","organize","straight","array","maid","housekeep")), "mlModels/clean_model.model", "filters/string_2_vector", "headers/cleanHeader.arff", "headers/S2Wclean.arff"));
		this.atrs.add(new MLClassifier("room", new ArrayList<String>(Arrays.asList("room","clean","suite","accommodation","apartment","cabine","resident")), "mlModels/room_model.model", "filters/string_2_vector", "headers/roomHeader.arff", "headers/S2Wroom.arff"));
		this.atrs.add(new MLClassifier("food", new ArrayList<String>(Arrays.asList("food","restaurant","bar","dinner","breakfast","brunch","lunch","snack","appetizer","salt","sweet","waitress","waiter","cook","chef","hostess","piccolo","dish","dining","eat","feast","Buffet","dine","egg")), "mlModels/food_model.model", "filters/string_2_vector", "headers/foodHeader.arff", "headers/S2Wfood.arff"));
	}

	/**
	 * classify review.
	 * @param rvw The review to classify as Review.
	 * @return the tagged Review as ReviewedTag.
	 */
	public ReviewedTag classify(Review rvw){
		HashMap <String, String> tags = new HashMap <String, String>();
		for(IClassifier cl: atrs){
			tags.put(cl.getAttrName(), cl.classifyAttribute(rvw.getText()));
		}
		return(new ReviewedTag(rvw.getId(), tags));
	}

}

