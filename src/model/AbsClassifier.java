package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.filters.Filter;

/**
 * AbsClassifier is abstract class that implements IClassifier and Initializing Classifier parameters.
 */
public abstract class AbsClassifier implements IClassifier {
	
	protected String attrName;
	protected ArrayList<String> synonyms;
	protected Classifier model;
	protected Filter filter;
	protected Instances header;
	protected Instances filterdHdr;
	

	/**
	 * ctor for AbsClassifier
	 * @param attrName the name of the attribute. as string
	 * @param synonyms list of synonyms for the attribute
	 * @param modelPath path to the attribute model file as string
	 * @param filterPath path to the filter file as string
	 * @param headerPath path to the attribute header file as string
	 * @param filterdHdr path to the attribute header after filter file as string
	 */
	public AbsClassifier(String attrName, ArrayList<String> synonyms, String modelPath, String filterPath, String headerPath, String filterdHdr) {
		setAttrName(attrName);
		setSynonyms(synonyms);
		setModel(modelPath);
		setFilter(filterPath);
		setHeader(headerPath);
		setFilterdHdr(filterdHdr);
	}
	
	// getters and setters
	@Override
	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public ArrayList<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(ArrayList<String> synonyms) {
		this.synonyms = synonyms;
	}

	public Classifier getModel() {
		return model;
	}

	public void setModel(String modelPath) {
		Object o = null;
		try {
			o = weka.core.SerializationHelper.read("modelPath");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.model = (Classifier)o;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(String filterPath) {
		Object o = null;
		try {
			o = weka.core.SerializationHelper.read(filterPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.filter = (Filter)o;
	}

	public Instances getHeader() {
		return header;
	}

	public void setHeader(String headerPath) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("headerPath"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			header = new Instances(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 // setting class attribute
		this.header.setClassIndex(header.numAttributes() - 1);
	}

	public Instances getFilterdHdr() {
		return filterdHdr;
	}

	public void setFilterdHdr(String filterdHdr) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("headers/S2Wbathroom.arff"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.filterdHdr = new Instances(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 // setting class attribute
		this.filterdHdr.setClassIndex(0);		
	}

	@Override
	public abstract String classifyAttribute(String text);
	
	/**
	 * check if one of the strings in the synonyms list appears in text.
	 * @param text String, the text we search words in. 
	 * @return true if the one of the strings in the synonyms list appears in the text, false otherwise.
	 */
	protected boolean synomContains(String text){
		for (String str: synonyms){
			if (text.toLowerCase().contains(str)){
				return true;
			}
		}
		return false;
	}
	
}
