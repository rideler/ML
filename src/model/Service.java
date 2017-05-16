package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import weka.classifiers.Classifier;
import weka.core.Instances;

public class Service extends Classify {

	public Service() {
		super();
	}

	@Override
	void setSynonyms() {
		this.synonyms = new ArrayList<String>(Arrays.asList("phone","help","service","reception","desk","answer","rude","clerk","bill","credit","customer","doorman","manager","employee","friend","inform","direction","check"));
	}

	@Override
	void setModel() {
		Object o = null;
		try {
			o = weka.core.SerializationHelper.read("mlModels/service_model.model");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model = (Classifier)o;

	}

	@Override
	void setInstance() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("headers/serviceHeader.arff"));
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
		header.setClassIndex(header.numAttributes() - 1);

	}
}
