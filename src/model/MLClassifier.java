package model;

import java.util.ArrayList;

import weka.core.Instances;
import weka.core.SparseInstance;
import weka.filters.Filter;
/**
 * MLClassifier is class that extends AbsClassifier and implement its abstract method.
 */
public class MLClassifier extends AbsClassifier {

	/**
	 * ctor for MLClassifier sends the parameters to the super class.
	 * @param attrName the name of the attribute. as string
	 * @param synonyms list of synonyms for the attribute
	 * @param modelPath path to the attribute model file as string
	 * @param filterPath path to the filter file as string
	 * @param headerPath path to the attribute header file as string
	 * @param filterdHdr path to the attribute header after filter file as string
	 */
	public MLClassifier(String attrName, ArrayList<String> synonyms, String modelPath, String filterPath,
			String headerPath, String modelHdrPath) {
		super(attrName, synonyms, modelPath, filterPath, headerPath, modelHdrPath);
	}

	@Override
	public String classifyAttribute(String text) {
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

}
