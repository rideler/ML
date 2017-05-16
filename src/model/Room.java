package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import weka.classifiers.Classifier;
import weka.core.Instances;

public class Room extends Classify {

	public Room() {
		super();
	}

	@Override
	void setSynonyms() {
		this.synonyms = new ArrayList<String>(Arrays.asList("room","clean","suite","accommodation","apartment","cabine","resident"));
	}

	@Override
	void setModel() {
		Object o = null;
		try {
			o = weka.core.SerializationHelper.read("../../mlModels/room_model.model");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model = (Classifier)o;

	}

	@Override
	void setInstance() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("../../headers/roomHeader.arff"));
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
