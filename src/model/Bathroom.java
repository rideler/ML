package model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Bathroom extends Classify {

	public Bathroom() {
		super();
	}

	@Override
	void setSynonyms() {
		// TODO Auto-generated method stub

	}

	@Override
	void setModel() {
		Object o = weka.core.SerializationHelper.read("./models/bathroom.model");
		model = (Classifier)o;

	}

	@Override
	void setInstance() {
		BufferedReader reader = new BufferedReader(new FileReader("./headers/bathroomHeader.arff"));
		header = new Instances(reader);
		reader.close();
		 // setting class attribute
		header.setClassIndex(header.numAttributes() - 1);

	}

}
