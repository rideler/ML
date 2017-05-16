package model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Quiet extends Classify {

	public Quiet() {
		super();
	}

	@Override
	void setSynonyms() {
		// TODO Auto-generated method stub

	}

	@Override
	void setModel() {
		Object o = weka.core.SerializationHelper.read("./models/quiet.model");
		model = (Classifier)o;

	}

	@Override
	void setInstance() {
		BufferedReader reader = new BufferedReader(new FileReader("./headers/quietHeader.arff"));
		header = new Instances(reader);
		reader.close();
		 // setting class attribute
		header.setClassIndex(header.numAttributes() - 1);

	}

}
