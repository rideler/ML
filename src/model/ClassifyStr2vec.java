package model;

import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.filters.Filter;

public abstract class ClassifyStr2vec implements Classify {
	
	protected ArrayList<String> synonyms;
	protected Classifier model;
	protected Filter filter;
	protected Instances header;
	protected Instances s2wHdr;
	

	public ClassifyStr2vec() {
		setSynonyms();
		setModel();
		setFilter();
		setInstance();
		setS2wHdr();
	}
	
	private void setFilter(){
		Object o = null;
		try {
			o = weka.core.SerializationHelper.read("filters/string_2_vector");
		} catch (Exception e) {
			e.printStackTrace();
		}
		filter = (Filter)o;
	}

	abstract void setSynonyms();
	abstract void setModel();
	abstract void setInstance();
	abstract String getName();
	abstract void setS2wHdr();
	
	@Override
	public String classifyAttribute(String text){
		if (synomContains(text)){
			Instances tmpInstances = new Instances(header);
			SparseInstance instance = new SparseInstance(tmpInstances.numAttributes());
			instance.setDataset(tmpInstances);
			instance.setValue(0, text);
			instance.setClassValue(1);
			tmpInstances.add(instance);
			
			try {
				filter.setInputFormat(header);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Instances filteredInstances = null;
			try {
				filteredInstances = Filter.useFilter(tmpInstances, filter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			double result = 2;
			try {
				result = model.classifyInstance(filteredInstances.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result == 2){
				return "error";
			}
			return header.classAttribute().value((int)result);
		}
		else
			return "0";
	}
	
	private boolean synomContains(String text){
		for (String str: synonyms){
			if (text.toLowerCase().contains(str)){
				return true;
			}
		}
		return false;
	}
	
}
