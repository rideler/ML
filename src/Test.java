import java.io.BufferedReader;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.filters.Filter;

public class Test {

	public Test() throws Exception {
		// TODO Auto-generated constructor stub
	
		Object o = weka.core.SerializationHelper.read("mlModels/model.model");
		Classifier model = (Classifier)o;
		System.out.println(model);
	
		Object o1 = weka.core.SerializationHelper.read("filters/filter");
		Filter f = (Filter)o1;
		System.out.println(f);
		
		BufferedReader reader = new BufferedReader(new FileReader("headers/header.arff"));
		Instances header = new Instances(reader);
		reader.close();
		 // setting class attribute
		header.setClassIndex(header.numAttributes() - 1);
		
		Instances tmpInstances = new Instances(header);
		SparseInstance instance = new SparseInstance(tmpInstances.numAttributes());
		instance.setDataset(tmpInstances);
		instance.setValue(0, 1);
		instance.setClassValue(0);
		tmpInstances.add(instance);
		
		f.setInputFormat(tmpInstances);
		Instances filteredInstances = Filter.useFilter(tmpInstances, f);
		double result = model.classifyInstance(filteredInstances.get(0));
		System.out.println(header.classAttribute().value((int)result));
	
	}
	
	public static void main(String[] args) throws Exception {
		new Test();
	}

}
