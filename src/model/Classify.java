package model;

import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.filters.Filter;

public abstract class Classify {
	
	protected  ArrayList<String> synonyms;
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
		Object o = weka.core.SerializationHelper.read("./filters/filter");
		filter = (Filter)o;
	}

	abstract void setSynonyms();
	abstract void setModel();
	abstract void setInstance();
	
	public double classifyAttribute(){
		Instances tmpInstances = new Instances(header);
		SparseInstance instance = new SparseInstance(tmpInstances.numAttributes());
		instance.setDataset(tmpInstances);
		instance.setValue(0, 1);
		instance.setClassValue(0);
		tmpInstances.add(instance);
		
		filter.setInputFormat(tmpInstances);
		Instances filteredInstances = Filter.useFilter(tmpInstances, filter);
		double result = model.classifyInstance(filteredInstances.get(0));
		return header.classAttribute().value((int)result);
	}
	
}
