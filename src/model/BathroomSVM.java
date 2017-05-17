package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import weka.classifiers.Classifier;
import weka.core.Instances;



public class BathroomSVM extends ClassifyStr2vec {

	public BathroomSVM() {
		super();
	}

	@Override
	void setSynonyms() {
		this.synonyms = new ArrayList<String>(Arrays.asList("bath","shower","toilet","lavatory","sink","stool","bidet","flood","water","towels","urinal","boiler","soap","stream","pressure","robe","leak","flow","drip"));
	}

	@Override
	void setModel() {
		Object o = null;
		try {
			o = weka.core.SerializationHelper.read("mlModels/bathroom_model.model");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model = (Classifier)o;

	}

	@Override
	void setInstance() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("headers/bathroomHeader.arff"));
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

	@Override
	String getName() {
		return "bathroom";
	}

	@Override
	void setS2wHdr() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("headers/S2Wbathroom.arff"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			s2wHdr = new Instances(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 // setting class attribute
		s2wHdr.setClassIndex(0);		
	}

}
