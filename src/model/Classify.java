package model;

import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.filters.Filter;

public abstract class Classify {
	
	protected ArrayList<String> synonyms;
	protected Classifier model;
	protected Filter filter;
	protected Instances header;
	

	public Classify() {
		setSynonyms();
		setModel();
		setFilter();
		setInstance();
	}
	
	private void setFilter(){
		Object o = null;
		try {
			o = weka.core.SerializationHelper.read("./filters/filter");
		} catch (Exception e) {
			e.printStackTrace();
		}
		filter = (Filter)o;
	}

	abstract void setSynonyms();
	abstract void setModel();
	abstract void setInstance();
	
	public String classifyAttribute(){
		Instances tmpInstances = new Instances(header);
		SparseInstance instance = new SparseInstance(tmpInstances.numAttributes());
		instance.setDataset(tmpInstances);
		instance.setValue(0, 1);
		instance.setClassValue(0);
		tmpInstances.add(instance);
		
		try {
			filter.setInputFormat(tmpInstances);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Instances filteredInstances = null;
		try {
			filteredInstances = Filter.useFilter(tmpInstances, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		double result = 0;
		try {
			result = model.classifyInstance(filteredInstances.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return header.classAttribute().value((int)result);
	}
	
}
